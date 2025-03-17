package Java;
public class CalculateDelayedArrivalTime {

  public static void main(String[] args) {}

  // Attempt 1 (works)

  public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
    if (arrivalTime + delayedTime > 23) {
      return arrivalTime + delayedTime - 24;
    } else {
      return arrivalTime + delayedTime;
    }
  }

  // Attempt 1 - alternative (works)

  public int findDelayedArrivalTime2(int arrivalTime, int delayedTime) {
    return (arrivalTime + delayedTime >= 24)
        ? arrivalTime + delayedTime - 24
        : arrivalTime + delayedTime;
  }
}
