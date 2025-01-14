public class RomanToInteger {
  public static void main(String[] args) {}

  // Attempt 1 (works)
  // I implemented the "exceptions" first where subtractions of roman nums are possible
  // Then watering down to

  private static int romanToInt(String s) {
    int sum = 0;
    char[] chars = s.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      switch (chars[i]) {
        case 'I':
          if (i < chars.length - 1 && chars[i + 1] == 'V') {
            sum += 4;
            i++;
          } else if (i <= chars.length - 1 && chars[i + 1] == 'X') {
            sum += 9;
            i++;
          } else {
            sum += 1;
          }
          break;

        case 'X':
          if (i <= chars.length - 1 && chars[i + 1] == 'L') {
            sum += 40;
            i++;
          } else if (i <= chars.length - 1 && chars[i + 1] == 'C') {
            sum += 90;
            i++;
          } else {
            sum += 10;
          }
          break;

        case 'C':
          if (i <= chars.length - 1 && chars[i + 1] == 'D') {
            sum += 400;
            i++;
          } else if (i <= chars.length - 1 && chars[i + 1] == 'M') {
            sum += 900;
            i++;
          } else {
            sum += 100;
          }
          break;

        case 'V':
          sum += 5;
          break;
        case 'L':
          sum += 50;
          break;
        case 'D':
          sum += 500;
          break;
        case 'M':
          sum += 1000;
          break;

        default:
          break;
      }
    }
    return sum;
  }
}
