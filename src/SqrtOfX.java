import java.time.Duration;
import java.time.Instant;

public class SqrtOfX {

  public static void main(String[] args) {
    Instant start1 = Instant.now();
    sqrtX5(198765421);
    Instant stop1 = Instant.now();
    System.out.println(Duration.between(start1, stop1).toNanos());
    Instant start2 = Instant.now();
    sqrtX6(198765421);
    Instant stop2 = Instant.now();
    System.out.println(Duration.between(start2, stop2).toNanos());
  }

  // attempt 5 - binary search advanced
  private static int sqrtX6(int x) {
    if (x == 0) return 0;

    int left = 1;
    int right = x;

    int ans = 1;

    while (left <= right) {
      if (right == x / right) return right;
      if (left == x / left) return left;

      int mid = (left + right) / 2;

      if (mid <= x / mid) {
        ans = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return ans;
  }

  // attempt 5 - binary search
  private static int sqrtX5(int x) {
    if (x == 0) return 0;

    int left = 1;
    int right = x;
    int ans = left;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (mid <= x / mid) {
        ans = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return ans;
  }

  // attempt 4 - does not work
  private static int sqrtX4(int x) {
    if (x == 0) return 0;
    int i = x;

    while (i > x / i) {
      i /= 2;
    }

    int left = i, right = x;

    while (left < right) {
      int mid = (left + right + 1) / 2;
      if (mid > x / mid) {
        right = mid - 1;
      } else {
        left = mid;
      }
    }

    return i - 1;
  }

  // attempt 3 - works but still slow - 6 ms
  private static int sqrtX3(int x) {
    int i = 1;
    int multiplier = 1;

    if (x > 100) {
      // Exponential growth phase
      while ((long) i * i <= x) {
        multiplier *= 2;
        i *= multiplier;
      }

      // Exponential step-down phase
      while (multiplier > 1) {
        multiplier /= 2;
        i /= 2;
        if ((long) i * i <= x) break;
      }

      // Linear search correction
      while ((long) i * i < x) i++;
    } else {
      while (i * i < x) i++;
    }

    return (i * i == x) ? i : i - 1;
  }

  // attempt 2 - works but slow - 28 ms
  private static int sqrtX(int x) {
    int i = 1;
    int multiplier = 1;

    // Exponential growth phase
    while ((long) i * i <= x) {
      i += multiplier;
      if (multiplier * 2 < Integer.MAX_VALUE) {
        multiplier *= 2;
      }
    }

    // Exponential step-down phase
    while (multiplier > 0) {
      if ((long) i * i > x) {
        i -= multiplier;
      }
      multiplier /= 2;
    }

    while ((long) i * i < x) {
      i++;
    }

    return (long) i * i > x ? i - 1 : i;
  }

  // attempt 1 - works but slow - 32 ms
  // constraints: 0 <= x <= 2^31 - 1
  private static int sqrtX1(int x) {
    return sqrtXHelper(1, x);
  }

  private static int sqrtXHelper(int i, int x) {
    if ((long) i * i > x) {
      return i - 1;
    }
    return sqrtXHelper(i++, x);
  }
}
