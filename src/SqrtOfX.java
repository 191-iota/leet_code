public class SqrtOfX {

  public static void main(String[] args) {
    System.out.println(sqrtX(49));
  }

  // attempt 2 - works but slow
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
  
  // attempt 1 - works but slow
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
