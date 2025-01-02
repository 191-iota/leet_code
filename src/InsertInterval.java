import java.util.HashMap;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] test = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        insert(test, newInterval);

    }

    // Attempt 1 (in progress)
    private static int[][] insert(int[][] intervals, int[] newInterval) {

        int intervalOverlap = 0;
        int secondInterval = 0;
        int firstInterval = 0;
        int[][] result = new int[intervals.length - intervalOverlap][2];

        for (int i = 0; i < intervals.length - 1; i++) {

            if (intervals[i][0] > newInterval[0] && intervals[i][1] < newInterval[1]) {
                intervalOverlap++;
            } else {
                secondInterval = intervals[i][1];
                if(intervals[i - intervalOverlap][0] < newInterval[0]) {
                    firstInterval = intervals[i - intervalOverlap][0];
                } else if (newInterval[0] < intervals[i - intervalOverlap + 1][0]) {
                    firstInterval = newInterval[0];
                }
                break;
            }
        }

        if(intervalOverlap == -1) {
            intervalOverlap = 0;
        }
        int offset = 0;

        for (int i = 0; i < result.length - intervalOverlap; i++) {

            if (secondInterval >= intervals[i][1] && firstInterval <= intervals[i][0]) {
                if (firstInterval != result[i][0]) {
                    result[i][0] = firstInterval;
                    offset++;
                    i--;
                } else {
                    result[i][1] = secondInterval;
                }

            } else {
                result[i][0] = intervals[i + offset][0];
                result[i][1] = intervals[i][1];
            }
        }
        return result;
    }
}
