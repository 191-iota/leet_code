import java.util.ArrayList;
import java.util.List;

public class FindMedianOfTwoSortedArrays {

    // Attempt 1 - (works)
    // Recall of the median: the absolute middle
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // This can be optimized
        List<Integer> mergedList = new ArrayList<>();

        for (int number : nums1) {
            mergedList.add(number);
        }
        for (int number : nums2) {
            mergedList.add(number);
        }

        mergedList.sort(Integer::compareTo);
        // Take the middle of the list length and return either the whole number or
        // if it is uneven return back the average of the two respective numbers
        if (mergedList.size() % 2 == 0) {
            int firstElement = mergedList.get(mergedList.size() / 2);
            int secondElement = mergedList.get(mergedList.size() / 2 - 1);
            return (firstElement + secondElement) / 2.0;

        } else {
            return (double) mergedList.get(mergedList.size() / 2);
        }
    }
}
