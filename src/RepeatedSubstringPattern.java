import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class RepeatedSubstringPattern {
  public static void main(String[] args) {

    Instant now = Instant.now();
    repeatedSubstringPattern2o("aasaasaasaasaasaasaas");
    System.out.println(Duration.between(now, Instant.now()).toNanos());
  }

  private static boolean repeatedSubstringPattern2o(String s) {
    for (int i = 1; i <= s.length() / 2; i++) {
      if (s.length() % i == 0 && s.substring(0, i).repeat(s.length() / i).equals(s)) {
        return true;
      }
    }
    return false;
  }

  // attempt 2
  private static boolean repeatedSubstringPattern2(String s) {
    StringBuilder sb = new StringBuilder();

    for (short i = 2; i <= s.length() / 2; i++) {

      if (s.length() % i == 0) {
        while (sb.length() < s.length()) {
          sb.append(s, 0, i);
        }
      } else {
        continue;
      }

      if (s.contentEquals(sb)) {
        return true;
      }
      sb.setLength(0);
    }
    return false;
  }

  // attempt 1 - does not work
  private static boolean repeatedSubstringPattern(String s) {
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
