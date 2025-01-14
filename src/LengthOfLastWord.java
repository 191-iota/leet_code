
public class LengthOfLastWord {
  public static void main(String[] args) {
    lengthOfLastWord("   fly me   to   the moon  ");
  }

  // Attempt 2 (works)
  // This one just does its job.
  public static int lengthOfLastWord2(String s) {
    char[] chars = s.toCharArray();
    short lastWordChars = 0;

    for (int i = chars.length - 1; i >= 0; i--) {
      if (chars[i] == ' ' && lastWordChars != 0) {
        return lastWordChars;
      } else if (chars[i] != ' ') {
        lastWordChars++;
      }
    }
    return lastWordChars;
  }

  // Attempt 1 (works)
  // I have the feeling that splitting a String for this task is overkill.
  public static int lengthOfLastWord(String s) {
    String[] lastWords = s.split(" ");
    return lastWords[lastWords.length - 1].length();
  }
}
