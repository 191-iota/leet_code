public class PlusOne {
    

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
