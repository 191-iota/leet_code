import java.sql.Array;
import java.util.*;

public class LongestSubStringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "pwwkew";
        lengthOfLongestSubstring(s);
    }



    // Attempt 2 - (doesn't work)
    public static int lengthOfLongestSubstring2(String s) {

        char[] chars = s.toCharArray();

        List<Character> uniqueChars = new ArrayList<>();
        int offset = 0;

        if(chars.length == 0) {
            return 0;
        }

        for (int i = 0; i < chars.length; i++) {
            if (uniqueChars.contains(chars[i]) && i != 1) {
                int charIndex = uniqueChars.indexOf(chars[i]);

                offset += i - offset;

                for (int j = charIndex; j > offset && j >= 0; j--) {
                    uniqueChars.remove(chars[j]);
                }

            } else if (uniqueChars.contains(chars[i])) {

                uniqueChars.remove(Character.valueOf(chars[i]));

            } else {
                uniqueChars.add(chars[i]);
            }
        }

        return uniqueChars.size();
    }

    // Attempt 1 - (doesn't work)
    public static int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();

        Set<Character> uniqueChars = new HashSet<>();
        List<Integer> uniqueOccurrences = new ArrayList<>();

        if(chars.length == 0) {
            return 0;
        }

        for (char aChar : chars) {
            if (uniqueChars.contains(aChar)) {
                uniqueOccurrences.add(uniqueChars.size());
                uniqueChars.remove(aChar);
                uniqueChars.add(aChar);
            } else {
                uniqueChars.add(aChar);
            }
        }

        uniqueOccurrences.add(uniqueChars.size());

        return Collections.max(uniqueOccurrences);
    }
}