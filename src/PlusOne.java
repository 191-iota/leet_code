import java.math.BigInteger;

public class PlusOne {
    

    // attempt 2 - does not work
    public static int[] plusOne3(int[] digits) {

        if(digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }

        int carry = 0;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                carry++;
            }
        }

        int[] carryArray = new int[digits.length + 1];

        for (int i = 0; i < carryArray.length; i++) {
            if (i < carryArray.length - 1 - carry) {
                digits[i] = carryArray[i];
            } else if (i == carryArray.length - 1 - carry) {
                carryArray[i] = 1;
            } else {
                carryArray[i] = 0;
            }
        }

        return carryArray;
    }

    // attempt 2 - works - this is very slow
    public static int[] plusOne2(int[] digits) {
        StringBuilder strBuilder = new StringBuilder();

        for (int digit : digits) {
            strBuilder.append(digit);
        }

        BigInteger bigInteger = new BigInteger(strBuilder.toString());

        bigInteger = bigInteger.add(BigInteger.valueOf(1));

        String bigIntegerString = bigInteger.toString();
        int[] incrementedDigits = new int[bigIntegerString.length()];

        for (int i = 0; i < bigInteger.toString().length(); i++) {
            incrementedDigits[i] = Character.getNumericValue(bigIntegerString.charAt(i));
        }

        return incrementedDigits;
    }

    public static int[] plusOne(int[] digits) {
        StringBuilder strBuilder = new StringBuilder();
    
        for (int digit : digits) {
            strBuilder.append(digit);
        }
    
        int incremented = Integer.parseInt(strBuilder.toString());
        incremented++;
    
        String incrementedString = Integer.toString(incremented);
        int[] incrementedDigits = new int[incrementedString.length()];
    
        for (int i = 0; i < incrementedString.length(); i++) {
            incrementedDigits[i] = Character.getNumericValue(incrementedString.charAt(i));
        }
    
        return incrementedDigits;
    }
}
