public class DivideTwoIntegers {
  public static void main(String[] args) {
    System.out.println(divide(-2147483648, -1));
  }
  
  // attempt 4 (optimized) - works
  public static int divide4o(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    } else if (dividend == Integer.MIN_VALUE && divisor == 1) {
        return Integer.MIN_VALUE;
    }
    
    boolean isNegative = (dividend < 0) ^ (divisor < 0);
    
    long absoluteDividend = (dividend == Integer.MIN_VALUE) ? (long) Integer.MAX_VALUE + 1: Math.abs(dividend);
    long absoluteDivisor = (divisor == Integer.MIN_VALUE) ? (long) Integer.MAX_VALUE + 1: Math.abs(divisor);
    
    int quotient = 0;
    
    while (absoluteDividend >= absoluteDivisor) {
        int powerOfTwo = 0;
    
        while ((absoluteDivisor << powerOfTwo) > 0 && (absoluteDivisor << powerOfTwo) <= absoluteDividend) {
            powerOfTwo++;
        }
    
        powerOfTwo--;
        quotient += (1 << powerOfTwo);
        absoluteDividend -= (absoluteDivisor << powerOfTwo);
    }
    
    return isNegative ? -quotient : quotient;
  }
  
  // attempt 3 - does not work
  public static int divide3(int dividend, int divisor) {
    boolean isNegative;

    if (dividend == 0) return 0;

    if (dividend < 0 && divisor < 0) {
      isNegative = false;
    } else isNegative = dividend < 0 || divisor < 0;

    int absoluteDividend = (dividend == Integer.MIN_VALUE) ? Integer.MAX_VALUE : Math.abs(dividend);
    int absoluteDivisor = (divisor == Integer.MIN_VALUE) ? Integer.MAX_VALUE : Math.abs(divisor);

    int quotient = 0;
    int powerOfTwo = 0;

    while (absoluteDividend >= (absoluteDivisor << powerOfTwo)) {
      if ((absoluteDivisor << powerOfTwo) < 0
          || (absoluteDivisor << powerOfTwo) > absoluteDividend) {
        break;
      }
      powerOfTwo++;
    }
    if (powerOfTwo > 0) {
      powerOfTwo--;
      quotient = (1 << powerOfTwo);
      absoluteDividend -= (absoluteDivisor << powerOfTwo);
    }

    while (absoluteDividend >= absoluteDivisor) {
      absoluteDividend -= absoluteDivisor;
      quotient++;
    }
    return isNegative ? -quotient : quotient;
  }

  // attempt 2 - does not work
  public static int divide2(int dividend, int divisor) {
    boolean isNegative;

    if (dividend == 0) {
      return 0;
    }

    if (dividend < 0 && divisor < 0) {
      isNegative = false;
    } else if (dividend < 0 || divisor < 0) {
      isNegative = true;
    } else {
      isNegative = false;
    }

    int absoluteDividend;
    int absoluteDivisor;
    boolean maxedDividend = false;

    if (dividend == Integer.MIN_VALUE) {
      absoluteDividend = Integer.MAX_VALUE;
      maxedDividend = true;
    } else {
      absoluteDividend = Math.abs(dividend);
    }

    if (divisor == Integer.MIN_VALUE) {
      absoluteDivisor = Integer.MAX_VALUE;
    } else {
      absoluteDivisor = Math.abs(divisor);
    }

    int quotient = 0;
    int powerOfTwo = 0;

    while (absoluteDividend >= (absoluteDivisor << powerOfTwo)) {
      powerOfTwo++;
    }
    quotient = powerOfTwo--;
    absoluteDividend -= (absoluteDivisor << powerOfTwo);

    while (absoluteDividend >= absoluteDivisor) {
      absoluteDividend -= absoluteDivisor;
      quotient++;
    }

    if (isNegative && !maxedDividend) {
      return -quotient;
    } else if (isNegative) {
      return -quotient - 1;
    } else {
      return quotient;
    }
  }

  // attempt 1 - does not work
  public static int divide(int dividend, int divisor) {
    boolean isNegative;

    if (dividend == 0) {
      return 0;
    }

    if (dividend < 0 && divisor < 0) {
      isNegative = false;
    } else if (dividend < 0 || divisor < 0) {
      isNegative = true;
    } else {
      isNegative = false;
    }

    int absoluteDividend;
    int absoluteDivisor = Math.abs(divisor);

    if (dividend == Integer.MIN_VALUE) {
      absoluteDividend = Integer.MAX_VALUE - 1;
    } else {
      absoluteDividend = Math.abs(dividend);
    }

    int quotient = 0;
    while (absoluteDividend >= absoluteDivisor) {
      absoluteDividend -= absoluteDivisor;
      quotient++;
    }

    if (isNegative) {
      return -quotient;
    } else {
      return quotient;
    }
  }
}
