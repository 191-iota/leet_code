import java.util.*;


public class InsertInterval {
    public static void main(String[] args) {
        int[][] test = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        insert3(test, newInterval);
    }

    public static int[][] insert7c(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][] {newInterval};

        int lo = 0, hi = intervals.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (intervals[mid][1] < newInterval[0]) lo = mid + 1;
            else hi = mid - 1;
        }
        int left = lo;

        lo = 0; hi = intervals.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (intervals[mid][0] <= newInterval[1]) lo = mid + 1;
            else hi = mid - 1;
        }
        int right = hi;

        if (left == intervals.length) {
            int[][] ans = Arrays.copyOf(intervals, intervals.length + 1);
            ans[intervals.length] = newInterval;
            return ans;
        }
        if (right < 0) {
            int[][] ans = new int[intervals.length + 1][];
            ans[0] = newInterval;
            System.arraycopy(intervals, 0, ans, 1, intervals.length);
            return ans;
        }

        newInterval[0] = Math.min(newInterval[0], intervals[left][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[right][1]);

        int newSize = intervals.length - (right - left);
        int[][] result = new int[newSize][2];
        System.arraycopy(intervals, 0, result, 0, left);
        result[left] = newInterval;
        System.arraycopy(intervals, right + 1, result, left + 1, intervals.length - right - 1);
        return result;
    }

    // attempt 7 - arraylist
    private static int[][] insert7b(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        short i = 0;

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }
        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(new int[]{newInterval[0], newInterval[1]});

        while (i < intervals.length) {
            result.add(intervals[i++]);
        }
        return result.toArray(new int[result.size()][]);
    }

    // attempt 7 - without arraylist
    private static int[][] insert7a(int[][] intervals, int[] newInterval) {
        int[][] result = new int[intervals.length + 1][2];
        short i = 0, j = 0;

        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            result[j++] = intervals[i++];
        }

        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result[j++] = new int[]{newInterval[0], newInterval[1]};

        while (i < intervals.length) {
            result[j++] = intervals[i++];
        }

        if (j < result.length) {
            int[][] trimmedResult = new int[j][];
            System.arraycopy(result, 0, trimmedResult, 0, j);
            return trimmedResult;
        }
        return result;
    }

    // attempt 7 - works
    private static int[][] insert7(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        short i = 0;

        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    // Attempt 6 - does not work
    private static int[][] insert6(int[][] intervals, int[] newInterval) {
        // some edge cases
        if (intervals.length == 0) return new int[][]{newInterval};
        if (newInterval.length == 0) return intervals;
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            int[][] result = new int[intervals.length + 1][1];
            for (int i = 0; i < intervals.length; i++) {
                result[i] = intervals[i];
            }
            result[result.length - 1] = newInterval;
            return result;
        } else if (newInterval[1] < intervals[0][0]) {
            int[][] result = new int[intervals.length + 1][1];
            result[0] = newInterval;
            for (int i = 1; i < result.length; i++) {
                result[i] = intervals[i - 1];
            }
            result[1] = intervals[0];
            return result;
        }

        HashMap<Integer, int[]> map = new HashMap<>();
        int mergeLength = 0;

        for (int i = 0; i < intervals.length; i++) {
            // by default it should add it to the map
            map.put(intervals[i][0], new int[]{intervals[i][0], intervals[i][1]});

            if (newInterval[0] < intervals[i][0] && mergeLength == 0) {
                map.replace(intervals[i][0], new int[]{newInterval[0], intervals[i][1]});
            }

            if (intervals[i][1] < newInterval[1]) {
                map.replace(intervals[i][0], new int[]{});// idk really, intervals[i][1]});mergeLength++;
            }
        }
        return null;
    }

    // Attempt 5 - in progress
    private static int[][] insert5(int[][] intervals, int[] newInterval) {
        // some edge cases
        if (intervals.length == 0) return new int[][]{newInterval};
        if (newInterval.length == 0) return intervals;
        if(newInterval[0] > intervals[intervals.length - 1][1]) {
            int[][] result = new int[intervals.length + 1][1];
            for(int i = 0; i < intervals.length; i++) {
                result[i] = intervals[i];
            }
            result[result.length - 1] = newInterval;
            return result;
        } else if(newInterval[1] < intervals[0][0]) {
            int[][] result = new int[intervals.length + 1][1];
            result[0] = newInterval;
            for(int i = 1; i < result.length; i++) {
                result[i] = intervals[i - 1];
            }
            result[1] = intervals[0];
            return result;
        }

        int prevBiggest = -1;
        int nextSmallest = -1;
        int toMerge = 0;

        if(intervals.length > 1) {
            nextSmallest = intervals[1][0];
        }

        boolean checkMerge = false;

        HashMap<Integer, Integer> resultList = new HashMap<>();

        for (int i = 0; i < intervals.length; i++) {

            if (!checkMerge && newInterval[0] >= prevBiggest && newInterval[0] <= intervals[i][0]) {
                resultList.put(newInterval[0], null);
                toMerge = newInterval[0];
                checkMerge = true;
            } else if(!checkMerge && newInterval[0] >= intervals[i][0] && newInterval[0] <= intervals[i][1]) {
                resultList.put(intervals[i][0], null);
                toMerge = intervals[i][0];
                checkMerge = true;
            } else if(!checkMerge){
                resultList.put(intervals[i][0], intervals[i][1]);
                continue;
            }

            if(nextSmallest < newInterval[1] && newInterval[1] < intervals[i][1]) {
                resultList.put(toMerge, newInterval[1]);
                i--;
                checkMerge = false;
            } else if(newInterval[1] <= nextSmallest && newInterval[1] >= intervals[i][1] || i == intervals.length - 1 && newInterval[1] > intervals[i][1]) {
                resultList.put(toMerge, newInterval[1]);
                checkMerge = false;
            } else if(newInterval[1] <= nextSmallest && newInterval[1] <= intervals[i][1] || i == intervals.length - 1 && newInterval[1] < intervals[i][1]) {
                resultList.put(toMerge, intervals[i][1]);
                checkMerge = false;
            }

            if(intervals.length < i + 1) {
                nextSmallest = intervals[i + 1][0];
            }

            prevBiggest = intervals[i][1];

        }

        int[][] result = new int[resultList.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : resultList.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }
        return result;
    }
    // Attempt 4 - in progress does not work
    private static int[][] insert4(int[][] intervals, int[] newInterval) {
        // some edge cases
        if (intervals.length == 0) return new int[][]{newInterval};
        if (newInterval.length == 0) return intervals;

        TreeMap<Integer, Integer> result = new TreeMap<>();
        if (intervals[0][0] > newInterval[0]) {
            result.put(newInterval[0], intervals[0][1]);
        } else {
            result.put(intervals[0][0], intervals[0][1]);
        }

        for (int i = 1; i < intervals.length; i++) {

            // if newIntervals has not been inserted yet
            if (result.lowerEntry(intervals[i][0]) != null
                    && result.lowerEntry(intervals[i][0]).getValue() < newInterval[0]
                    && intervals[i][0] > newInterval[0] && !result.containsKey(newInterval[0])) {

                result.put(newInterval[0], null);
            } else {
                result.put(intervals[i][0], null);
            }

        }

        return null;
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
