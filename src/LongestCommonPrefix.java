public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] test = {"abab","aba",""};
        longestCommonPrefix3(test);
    }

    // Attempt 3 - (works)
    public static String longestCommonPrefix3(String[] strs) {

        int commonPrefixLength = 0;
        int shortestLength = strs[0].length();
        // evaluate the shortest length of a word which can be the longest prefix
        while (commonPrefixLength < shortestLength) {

            for (int i = 0; i < strs.length - 1; i++) {
                if (commonPrefixLength == 0 && shortestLength > strs[i + 1].length()) {
                    shortestLength = strs[i + 1].length();
                    if(shortestLength == 0){
                        return "";
                    }
                }

                if (strs[i].charAt(commonPrefixLength) != strs[i + 1].charAt(commonPrefixLength)) {
                    return strs[i].substring(0, commonPrefixLength);
                }
            }
            commonPrefixLength++;
        }

        return strs[0].substring(0, commonPrefixLength);
    }

    // Attempt 2 - (does not work)
    public static String longestCommonPrefix2(String[] strs) {

        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        int commonPrefixLength = 0;
        int shortestLength = strs[0].length();
        // evaluate the shortest length of a word which can be the longest prefix
        for (int i = 0; commonPrefixLength < shortestLength; i++) {
            if (i < strs.length && strs[i].length() < shortestLength) {
                shortestLength = strs[i].length();
                i--;
                continue;
            }

            for (int j = 0; j < strs.length - 1; j++) {
                if(strs[j + 1].isEmpty() || strs[j].charAt(commonPrefixLength) != strs[j + 1].charAt(commonPrefixLength)) {
                    return strs[j].substring(0, commonPrefixLength);
                }
            }
            commonPrefixLength++;
        }

        return strs[0].substring(0, commonPrefixLength);
    }


    // Attempt 1 - (works)
    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

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
