import java.util.*;

public class LongestSubStringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "au";
        lengthOfLongestSubstring(s);
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