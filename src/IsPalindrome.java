public class IsPalindrome {

    public static void main(String[] args) {

    }

    // Attempt 1 (works)

    public static boolean isPalindrome(int x) {
        char[] splitString = Integer.toString(x).toCharArray();

        for (int i = 0; i < splitString.length / 2; i++) {
            if(splitString[i] != splitString[splitString.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }

}
