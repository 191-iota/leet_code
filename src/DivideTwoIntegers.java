public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648,-1));
    }


    // attempt 1 - does not work
    public static int divide(int dividend, int divisor) {
        boolean isNegative;

        if(dividend == 0) {
            return 0;
        }

        if(dividend < 0 && divisor < 0) {
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
        while(absoluteDividend >= absoluteDivisor) {
            absoluteDividend -= absoluteDivisor;
            quotient++;
        }


        if(isNegative) {
            return -quotient;
        } else {
            return quotient;
        }
    }
 
}
