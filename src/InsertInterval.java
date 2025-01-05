import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InsertInterval {
    public static void main(String[] args) {
        int[][] test = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        insert3(test, newInterval);
    }

    // Attempt 3 - in progress does not work
    // loop through the array
    // check if new interval is part of interval
    // if the last number is not part of it or the same as the next interval, then skip this when building an array
    private static int[][] insert3(int[][] intervals, int[] newInterval) {
        // case 0.1: if intervals are empty just return the new interval
        if (intervals.length == 0) return new int[][]{newInterval};
        // case 0.2: if new interval is empty just return intervals
        if (newInterval.length == 0) return intervals;


        List<Integer> result = new ArrayList<>();
        boolean firstIntervalSet = false;

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= newInterval[0] && intervals[i][0] <= newInterval[0]) {
                firstIntervalSet = true;
            }

            // case 1.1: Interval beginning is smaller than new interval beginning
            if (intervals[i][0] <= newInterval[0]) {
                result.add(intervals[i][0]);

            // case 1.2: New interval beginning is smaller than interval beginning
            } else if (intervals[i][0] >= newInterval[0]
                    || !firstIntervalSet && intervals[i][0] > newInterval[0] && intervals[i - 1][1] < newInterval[0]) {
                result.add(newInterval[0]);
                firstIntervalSet = true;
            }

            // case 2.1: If new interval ending is bigger than current ending but smaller than next beginning
            if (firstIntervalSet && intervals[i][1] < newInterval[1] && intervals[i + 1][0] < newInterval[1] && result.size() % 2 == 0) {
                result.add(newInterval[1]);
                i++;

            } else if (result.size() % 2 == 0) {
                // case 2.2: If interval ending is bigger than new interval ending
                result.add(intervals[i][1]);
            }
        }

        return null;
    }

    // Attempt 2 - does not work
    private static int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        if (newInterval.length == 0) return intervals;
        if (intervals[0][1] < newInterval[0] && intervals.length == 1) {
            int[][] result = new int[intervals.length + 1][2];
            result[0][0] = intervals[0][0];
            result[0][1] = intervals[0][1];
            result[1][0] = newInterval[0];
            result[1][1] = newInterval[1];
            return result;
        }

        int from = -1;
        int fromIndex = 0;
        int toIndex = 0;
        int to = -1;
        int skipped = -1;

        for (int i = 0; i < intervals.length; i++) {
            if (from != -1) {
                skipped++;
            }

            if (i == 0 && intervals[i][0] > newInterval[0]) {
                from = newInterval[0];
                fromIndex = i;
            } else if (intervals[i][1] >= newInterval[0] && from == -1) {
                from = intervals[i][0];
                fromIndex = i;
            }
            if (intervals[i][1] > newInterval[1] && newInterval[1] >= intervals[i][0]) {
                to = intervals[i][1];
                toIndex = i;
                break;
            } else if (intervals[i][1] > newInterval[1] && intervals[i][0] >= newInterval[1]) {

                to = newInterval[1];
                toIndex = i - 1;
                break;
            } else if (intervals[i][1] < newInterval[1] && intervals.length == 1) {
                to = newInterval[1];
                toIndex = i;
            }
        }

        if (skipped > 0) {
            skipped++;
        } else if (skipped == -1) {
            skipped = 0;
        }

        int[][] result = new int[intervals.length - skipped][2];

        // Build the array
        for (int i = 0; i < result.length; i++) {

            if (i == fromIndex && from != -1) {
                result[i][0] = from;
            }

            if (i + skipped == toIndex && from != -1) {
                result[i][1] = to;
            } else if (i > toIndex - skipped) {
                result[i][0] = intervals[i + skipped][0];
                result[i][1] = intervals[i + skipped][1];
            } else {
                result[i][0] = intervals[i][0];
                result[i][1] = intervals[i][1];
            }
        }
        System.out.println(Arrays.deepToString((result)));
        return result;
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
                if (intervals[i - intervalOverlap][0] < newInterval[0]) {
                    firstInterval = intervals[i - intervalOverlap][0];
                } else if (newInterval[0] < intervals[i - intervalOverlap + 1][0]) {
                    firstInterval = newInterval[0];
                }
                break;
            }
        }

        if (intervalOverlap == -1) {
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
