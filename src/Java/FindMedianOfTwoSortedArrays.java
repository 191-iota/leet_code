package Java;
import java.util.ArrayList;
import java.util.List;

public class FindMedianOfTwoSortedArrays {

  public static void main(String[] args) {
    int[] nums1 = {1, 2};
    int[] nums12 = {3, 4};
    findMedianSortedArray3(nums1, nums12);
  }

  // Attempt 5 - (works)
  public static double findMedianSortedArray5(int[] nums1, int[] nums2) {

    short firstPointer = 0;
    short secondPointer = 0;

    int currentMedian = 0;
    int lastMedian = 0;

    while (firstPointer + secondPointer < (nums1.length + nums2.length) / 2 + 1) {
      if (secondPointer >= nums2.length
          || (firstPointer < nums1.length && nums1[firstPointer] < nums2[secondPointer])) {
        lastMedian = currentMedian;
        currentMedian = nums1[firstPointer];
        firstPointer++;
      } else {
        lastMedian = currentMedian;
        currentMedian = nums2[secondPointer];
        secondPointer++;
      }
    }

    if ((nums1.length + nums2.length) % 2 != 0) {
      return currentMedian;
    } else {
      return (currentMedian + lastMedian) / 2.0;
    }
  }

  // Attempt 4 - (does not work)
  public static double findMedianSortedArray4(int[] nums1, int[] nums2) {

    short firstPointer = 0;
    short secondPointer = 0;

    while (firstPointer < nums1.length
        && secondPointer < nums2.length
        && firstPointer + secondPointer < (nums1.length + nums2.length) / 2) {
      if (nums1[firstPointer] >= nums2[secondPointer]) {
        secondPointer++;
      } else if (nums1[firstPointer] < nums2[secondPointer]) {
        firstPointer++;
      }
    }

    if (firstPointer < nums1.length) {
      while (firstPointer < nums1.length
          && firstPointer + secondPointer < (nums1.length + nums2.length) / 2) {
        firstPointer++;
      }

    } else {
      while (secondPointer < nums2.length
          && firstPointer + secondPointer < (nums1.length + nums2.length) / 2) {
        secondPointer++;
      }
    }

    if ((nums1.length + nums2.length) % 2 != 0) {
      if (firstPointer == (nums1.length + nums2.length) / 2 + 1 && firstPointer > 0) {
        return nums1[firstPointer - 1];
      } else if (firstPointer == (nums1.length + nums2.length) / 2 + 1 && firstPointer == 0) {
        return nums1[firstPointer];
      } else if (firstPointer + secondPointer == (nums1.length + nums2.length) / 2
          && secondPointer == 0) {
        return nums2[secondPointer];
      } else {
        return nums2[secondPointer - 1];
      }
    } else {
      if (firstPointer == 0 && secondPointer > 0) {
        return (double) (nums1[firstPointer] + nums2[secondPointer - 1]) / 2;
      } else if (firstPointer > 0 && secondPointer == 0) {
        return (double) (nums1[firstPointer - 1] + nums2[secondPointer]) / 2;
      }
      return (double) (nums1[firstPointer - 1] + nums2[secondPointer - 1]) / 2;
    }
  }

  // Attempt 3 - (does not work)
  public static double findMedianSortedArray3(int[] nums1, int[] nums2) {
    // This can be optimized
    int[] result = new int[nums1.length + nums2.length / 2];

    short resultIndex = 0;
    short firstPointer = 0;
    short secondPointer = 0;

    while (firstPointer < nums1.length
        && secondPointer < nums2.length
        && resultIndex < result.length) {
      if (nums1[firstPointer] >= nums2[secondPointer]) {
        result[resultIndex] = nums2[secondPointer];
        secondPointer++;
      } else if (nums1[firstPointer] < nums2[secondPointer]) {
        result[resultIndex] = nums1[firstPointer];
        firstPointer++;
      }
      resultIndex++;
    }

    if (firstPointer < nums1.length) {
      while (firstPointer < nums1.length && firstPointer + secondPointer < result.length) {
        result[resultIndex] = nums1[firstPointer];
        firstPointer++;
        resultIndex++;
      }

    } else {
      while (secondPointer < nums2.length && firstPointer + secondPointer < result.length) {
        result[resultIndex] = nums2[secondPointer];
        secondPointer++;
        resultIndex++;
      }
    }

    if (nums1.length + nums2.length % 2 == 0) {
      return (result[result.length - 1] + result[result.length - 2]) / 2.0;
    } else {
      return result[result.length - 1];
    }
  }

  // Attempt 2 - (works)
  public static double findMedianSortedArray2(int[] nums1, int[] nums2) {
    // This can be optimized
    int[] result = new int[nums1.length + nums2.length];

    short resultIndex = 0;
    short firstPointer = 0;
    short secondPointer = 0;

    while (firstPointer < nums1.length && secondPointer < nums2.length) {
      if (nums1[firstPointer] >= nums2[secondPointer]) {
        result[resultIndex] = nums2[secondPointer];
        secondPointer++;
      } else if (nums1[firstPointer] < nums2[secondPointer]) {
        result[resultIndex] = nums1[firstPointer];
        firstPointer++;
      }
      resultIndex++;
    }

    if (firstPointer < nums1.length) {
      while (firstPointer < nums1.length) {
        result[resultIndex] = nums1[firstPointer];
        firstPointer++;
        resultIndex++;
      }
    } else {
      while (secondPointer < nums2.length) {
        result[resultIndex] = nums2[secondPointer];
        secondPointer++;
        resultIndex++;
      }
    }

    if (result.length % 2 == 0) {
      return (result[result.length / 2] + result[result.length / 2 - 1]) / 2.0;
    } else {
      return result[result.length / 2];
    }
  }

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
