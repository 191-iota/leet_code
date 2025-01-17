import java.util.HashSet;
import java.util.Set;

public class RepeatedSubstringPattern {
  public static void main(String[] args) {}

  // attempt 1 - does not work
  public static boolean repeatedSubstringPattern(String s) {
    // this will be the repeated pattern
    StringBuilder sb = new StringBuilder();
    Set<Character> set = new HashSet<>();

    for (char c : s.toCharArray()) {
      if (!set.contains(c)) {
        sb.append(c);
        set.add(c);
      } else {
        break;
      }
    }

    if (s.length() % sb.length() != 0 || sb.length() == s.length()) {
      return false;
    }

    int prevIndex = 0;
    for (int i = sb.length(); i <= s.length(); i++) {
      if (!s.substring(prevIndex, i).contentEquals(sb)) {
        return false;
      }
      prevIndex = i;
      i += sb.length() - 1;
    }
    return true;
  }
}
