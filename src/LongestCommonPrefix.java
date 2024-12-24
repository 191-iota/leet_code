public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] test = {"ab", "a"};
        longestCommonPrefix(test);
    }


    // Attempt 1 - (in progress)
    public static String longestCommonPrefix(String[] strs) {

        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];

        int commonPrefixLength = 0;
        int shortestLength = strs[0].length();
        // evaluate the shortest length of a word which can be the longest prefix
        for (String str : strs) {
            if (str.length() < shortestLength) {
                shortestLength = str.length();
            }
        }

        while (commonPrefixLength < shortestLength) {
            for (int i = 0; i < strs.length - 1; i++) {
                if (strs[i].charAt(commonPrefixLength) != strs[i + 1].charAt(commonPrefixLength)) {
                    return strs[i].substring(0, commonPrefixLength);
                }
            }
            commonPrefixLength++;
        }

        return strs[0].substring(0, commonPrefixLength);
    }
}
