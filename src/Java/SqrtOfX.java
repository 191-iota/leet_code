package Java;
public class SqrtOfX {

  public static void main(String[] args) {
    System.out.println(sqrtX4(49));
  }

  // attempt 4 - works
  private static int sqrtX4(int x) {
    if(x == 0) return 0;

    int left = 1, right = x;
    int answer = 0;
    while (left < right) {
        int mid = (left + right + 1) / 2;
        if (mid > x / mid) {
            answer = mid;
            right = mid - 1;
        } else {
            left = mid;
        }
    }

    return answer;
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
