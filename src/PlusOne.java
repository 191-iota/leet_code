import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {

        int[] testArray = {9,9,9,9,9,9};
        
        Instant now = Instant.now();
        plusOne4(testArray);
        Instant stop = Instant.now();
        System.out.println(Duration.between(now, stop));

        Instant now1 = Instant.now();
        plusOne4o(testArray);
        Instant stop1 = Instant.now();
        System.out.println(Duration.between(now1, stop1).toNanos());

    }
    
    // attempt 4 optimized - works
    public static int[] plusOne4o(int[] digits) {
        
        int incrementableIndex = -1;

        // first index got already checked
        for (int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] != 9) {
                incrementableIndex = i;
                break;
            }
        }

        if(incrementableIndex == -1) { 
            int[] incrementedArray = new int[digits.length + 1];
            incrementedArray[0] = 1;
            return incrementedArray;
        }

        int[] carryArray = new int[digits.length];
        carryArray[incrementableIndex] = ++digits[incrementableIndex];
        
        for (int i = incrementableIndex; i >= 0; i--) {
            if(i < digits.length) carryArray[i] = digits[i];
        }

        return carryArray;
    }

    public static int[] plusOne4(int[] digits) {
        
        if(digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }
        
        int incrementableIndex = -1;

        for (int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] != 9) {
                incrementableIndex = i;
                break;
            }
        }

        int[] carryArray;

        if(incrementableIndex == -1) { 
            carryArray = new int[digits.length + 1];
        } else {
            carryArray = new int[digits.length];

        }
        
        boolean incrementSet = false; 

        for (int i = 0; i < carryArray.length; i++) {
            if(incrementableIndex == -1 && !incrementSet) {
                carryArray[i] = 1;
                incrementSet = true;
            } else if(i == incrementableIndex && !incrementSet) {
                carryArray[i] = ++digits[i];
                incrementSet = true;
            } else if (i < digits.length && incrementSet && digits[i] == 9){
                carryArray[i] = 0;
            } else if (i < digits.length) {
                carryArray[i] = digits[i];
            }
        }
        return carryArray;
    }

    // attempt 3 - does not work
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
