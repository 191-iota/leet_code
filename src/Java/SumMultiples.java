package Java;
public class SumMultiples {
  public static void main(String[] args) {}

  // Attempt 1 (works)
  public int sumOfMultiples(int n) {

    int sum = 0;

    for (int i = 3; i <= n; i++) {
      if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
        sum += i;
      }
    }
    return sum;
  }

  // Attempt 2 - recursive alternative (works)
  public int sumOfMultiples2(int n) {

    if (n < 3) {
      return 0;
    } else {
      if (n % 3 == 0 || n % 5 == 0 || n % 7 == 0) {
        return n + sumOfMultiples2(n - 1);
      } else {
        return sumOfMultiples2(n - 1);
      }
    }
  }
}
