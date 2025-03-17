package Java;
public class IsPalindrome {

  public static void main(String[] args) {}

  // Attempt 1 (works)

  public static boolean isPalindrome(int x) {
    char[] splitString = Integer.toString(x).toCharArray();

    for (int i = 0; i < splitString.length / 2; i++) {
      if (splitString[i] != splitString[splitString.length - 1 - i]) {
        return false;
      }
    }

    return true;
  }

  // Attempt 1 - alternative

  public static boolean isPalindrome2(int x) {
    if (x < 0) {
      return false;
    }

    StringBuilder sb = new StringBuilder();

    while (x > 0) {
      sb.append((char) ((x % 10) + '0')); // Ensure ASCII conversion, append last digit of number
      x /= 10; // remove the last digit of number which just got added
    }

    for (int i = 0; i < sb.length() / 2; i++) {
      if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }
}
