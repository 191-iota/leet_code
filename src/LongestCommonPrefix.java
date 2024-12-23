public class LongestCommonPrefix {
    public static void main(String[] args) {

    }


    // Attempt 1 - (in progress)
    public String longestCommonPrefix(String[] strs) {
        int commonPrefixLength = 0;

        while (true) {
            for (int i = 0; i < strs.length - 1; i++) {
                if (strs[i].charAt(commonPrefixLength) != strs[i + 1].charAt(commonPrefixLength)) {
                    return strs[i].substring(0, commonPrefixLength);
                }
                commonPrefixLength++;
            }
        }
        return "";
    }
}
