import java.util.HashMap;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] test = {};

    }


    // Attempt 1 (in progress)
    private static int[][] insert(int[][] intervals, int[] newInterval) {

        int intervalOverlap = 0;
        int firstInterval = 0;
        int secondInterval = 0;

        for (int i = 0; i < intervals.length - 1; i++) {

            if (intervals[i][0] >= newInterval[0] && intervals[i + 1][0] > newInterval[1]) {
                intervalOverlap++;
            }

            if (intervals[i][1] > newInterval[1]) {
                secondInterval = newInterval[1];
                break;
            }
        }
        // Build the array

        int[][] result = new int[intervals.length - intervalOverlap][2];

        for (int i = 0; i < result.length; i++) {

            result[i][0] = intervals[i][0];

            if (secondInterval > intervals[i][1]) {
                if(secondInterval < intervals[i + 1][1]) {
                    result[i][1] = secondInterval;
                }

            } else {
                result[i][1] = intervals[i][1];
            }

        }

        return result;
    }
}
