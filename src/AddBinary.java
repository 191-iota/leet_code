import java.math.BigInteger;

public class AddBinary {

    public static void main(String[] args) {
        
    }

    public static String addBinary4(String a, String b) {
        StringBuilder result = new StringBuilder();
    
        int carry = 0;
        int pointer = 0;
        int minLength = Math.min(a.length(), b.length());
    
        while (pointer < minLength) {
            int currentSum = carry;
            currentSum += a.charAt(a.length() - 1 - pointer) - '0';
            currentSum += b.charAt(b.length() - 1 - pointer) - '0';
    
            result.append(currentSum % 2);
            carry = currentSum / 2;
            pointer++;
        }
    
        String larger = a.length() > b.length() ? a : b;
    
        while (pointer < larger.length()) {
            int currentSum = carry;
            currentSum += larger.charAt(larger.length() - 1 - pointer) - '0';
            result.append(currentSum % 2);
            carry = currentSum / 2;
            pointer++;
        }
    
        if(carry > 0) {
            result.append(carry);
        }
    
        result.reverse();
    
        return result.toString();
    }
    
    // attempt 3 - optimized
    public static String addBinary3(String a, String b) {
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int maxLength = Math.max(a.length(), b.length());

        for (int i = maxLength - 1; i >= 0; i--) {
            int currentSum = carry;
            int aIndex = i - (maxLength - a.length());
            int bIndex = i - (maxLength - b.length());

            if (aIndex >= 0) {
                currentSum += a.charAt(aIndex) - '0';
            }
            if (bIndex >= 0) {
                currentSum += b.charAt(bIndex) - '0';
            }
            result.append(currentSum % 2);

            carry = currentSum / 2;
        }

        if(carry > 0) {
            result.append(carry);
        }

        result.reverse();

        return result.toString();
    }

    // attempt 2 - in works but is also slow
    public static String addBinary2(String a, String b) {
        StringBuilder result = new StringBuilder();

        String padding;
        // add padding to make them the same length
        if (a.length() > b.length()) {
            padding = " ".repeat(a.length() -  b.length());
            b = padding + b;
        } else {
            padding = " ".repeat(b.length() -  a.length());
            a = padding + a;
        }

        int carry = 0;
        // both are the same size anyways
        for (int i = a.length() - 1; i >= 0; i--) {
            int currentSum = carry;

            if (Character.isDigit(a.charAt(i))) {
                currentSum += Integer.parseInt(String.valueOf(a.charAt(i)));

                if (Character.isDigit(b.charAt(i))) {
                    currentSum += Integer.parseInt(String.valueOf(b.charAt(i)));
                }

                result.append(currentSum % 2);
                carry = currentSum / 2;
            }
        }
        if(carry != 0) {
            result.append(carry);
        }
        result.reverse();

        return result.toString();
    }   

    // attempt 1 - works but it is slow 
    public static String addBinary(String a, String b) {
        BigInteger bigA = new BigInteger(a, 2);
        BigInteger bigB = new BigInteger(b, 2);

        BigInteger sum = bigA.add(bigB);

        return sum.toString(2);
    }
}