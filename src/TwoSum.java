import java.util.*;

public class TwoSum {
  public static void main(String[] args) {
    int[] array = {3, 2, 4};

    twoSum5(array, 6);
  }

  // I missed the point of two sum I didn't realize that there can only be two pairs
  // Essentially, in my previous attempts I tried to solve "Xsum"

  // This works

  public static int[] twoSum7(int[] nums, int target) {

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];

      if (map.containsKey(complement)) {
        return new int[] {map.get(complement), i};
      }

      map.put(nums[i], i);
    }

    throw new RuntimeException("No such solution");
  }

  // Attempt 6 (doesn't work)
  // I might need to entirely change my approach since testcases are bulletproof

  public static int[] twoSum6(int[] nums, int target) {

    HashMap<Integer, Integer> map = new HashMap<>();

    // Initial indices are put into hashmap
    for (int i = 0; i < nums.length; i++) {
      map.put(i, nums[i]);
    }

    // The indices and values are now sorted by their value
    Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
    map.entrySet().stream()
        .sorted((e1, e2) -> Integer.compare(Math.abs(e2.getValue()), Math.abs(e1.getValue())))
        .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

    int currentSum = 0;
    List<Integer> list = new ArrayList<>();

    for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
      if (Math.abs(currentSum) + Math.abs(entry.getValue()) <= Math.abs(target)
              && entry.getValue() != target
          || target == 0 && Math.abs(entry.getValue()) == 0
          || currentSum == 0
              && Math.abs(entry.getValue()) <= Math.abs(target)
              && entry.getValue() != target) {

        currentSum += entry.getValue();
        list.add(entry.getKey());
      }
    }

    if (list.isEmpty() && target == 0) {
      Map<Integer, Integer> seen = new HashMap<>();

      for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
        int value = entry.getValue();
        int complement = -value;

        if (seen.containsKey(complement)) {
          list.add(entry.getKey());
          list.add(seen.get(complement));
        } else {
          seen.put(value, entry.getKey());
        }
      }
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  // Assignment: Array indices of the shortest amount of array values summing up to target
  // Tools: Hashmap for storing indices and counting values, loops and if checks
  // Difficulties: Minus numbers, zero numbers, getting the least amount of values, zero target
  // Solutions: Minus numbers => absolute values, zero numbers => loop through everything,
  // least amount of values => check for bigger sums <= target, zero target => find number that has
  // the equivalent absolute value and negative expression

  // Im trying to keep it below O(n^2) complexity

  // Still doesn't work...

  public static int[] twoSum5(int[] nums, int target) {

    HashMap<Integer, Integer> map = new HashMap<>();

    // Initial indices are put into hashmap
    for (int i = 0; i < nums.length; i++) {
      map.put(i, nums[i]);
    }

    // The indices and values are now sorted by their value
    Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
    map.entrySet().stream()
        .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
        .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

    int currentSum = 0;
    List<Integer> list = new ArrayList<>();

    for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
      if (Math.abs(currentSum) + Math.abs(entry.getValue()) <= Math.abs(target)
          || target == 0 && Math.abs(entry.getValue()) == 0
          || currentSum == 0 && Math.abs(entry.getValue()) <= Math.abs(target)) {
        currentSum += entry.getValue();
        list.add(entry.getKey());
      }
    }
    return list.stream().mapToInt(i -> i).toArray();
  }

  // This is getting way too complicated. Lets start off new
  // Attempt 4 (doesn't work)
  public static int[] twoSum4(int[] nums, int target) {

    Map<Integer, Integer> map = new HashMap<>();

    int currentSum = 0;

    for (int i = 0; i < nums.length; i++) {
      if (Math.abs(currentSum) == Math.abs(target) && target != 0) {
        return map.keySet().stream().mapToInt(Integer::intValue).toArray();
      }
      if (Math.abs(currentSum) + Math.abs(nums[i]) <= Math.abs(target) || target == 0 && i == 0) {
        // is there a bigger number which fits
        for (int j = i + 1; j < nums.length; j++) {
          if (Math.abs(nums[i]) < Math.abs(nums[j])
              && Math.abs(currentSum) + Math.abs(nums[j]) <= Math.abs(target)) {
            map.put(j, nums[j]);
            currentSum += nums[j];
          }
        }
        if (Math.abs(currentSum) + Math.abs(nums[i]) <= Math.abs(target)) {
          currentSum += nums[i];
          map.put(i, nums[i]);
        }

      } else {
        int targetValue = Math.abs(currentSum) + Math.abs(nums[i]) - Math.abs(target);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if (Math.abs(entry.getValue()) == targetValue) {
            map.remove(entry.getKey());
            map.put(i, nums[i]);
            break;
          } else if (target == 0 && Math.abs(currentSum + nums[i]) == target) {
            map.put(i, nums[i]);
            break;
          }
        }
      }
    }
    return map.keySet().stream().mapToInt(Integer::intValue).toArray();
  }

  // Attempt 3 (doesn't work)
  public static int[] twoSum3(int[] nums, int target) {

    Map<Integer, Integer> map = new HashMap<>();

    int currentSum = 0;

    outer:
    for (int i = 0; i < nums.length; i++) {
      if (Math.abs(currentSum) + Math.abs(nums[i]) <= Math.abs(target) || target == 0 && i == 0) {
        // is there a bigger number which fits
        for (int j = i + 1; j < nums.length; j++) {
          if (nums[i] < nums[j] && currentSum + nums[j] <= target) {
            map.put(j, nums[j]);
            currentSum += nums[j];
          }
        }

        currentSum += nums[i];
        map.put(i, nums[i]);

      } else {
        int targetValue = Math.abs(currentSum) + Math.abs(nums[i]) - Math.abs(target);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if (Math.abs(entry.getValue()) == targetValue) {
            map.remove(entry.getKey());
            map.put(i, nums[i]);
            break;
          } else if (target == 0 && Math.abs(currentSum + nums[i]) == target) {
            map.put(i, nums[i]);
            break;
          }
        }
      }
    }
    return map.keySet().stream().mapToInt(Integer::intValue).toArray();
  }

  // Attempt 2 (doesn't work)
  public static int[] twoSum2(int[] nums, int target) {

    Map<Integer, Integer> map = new HashMap<>();

    int currentSum = 0;

    for (int i = 0; i < nums.length; i++) {
      if (Math.abs(currentSum) + Math.abs(nums[i]) <= Math.abs(target) || target == 0 && i == 0) {
        currentSum += nums[i];
        map.put(i, nums[i]);

      } else {
        int targetValue = Math.abs(currentSum) + Math.abs(nums[i]) - Math.abs(target);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if (Math.abs(entry.getValue()) == targetValue) {
            map.remove(entry.getKey());
            map.put(i, nums[i]);
            break;
          } else if (target == 0 && Math.abs(currentSum + nums[i]) == target) {
            map.put(i, nums[i]);
            break;
          }
        }
      }
    }
    return map.keySet().stream().mapToInt(Integer::intValue).toArray();
  }

  // Attempt 1 (doesn't work)

  public static int[] twoSum(int[] nums, int target) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (!map.containsKey(i) && target - nums[i] > 0) {
        map.put(i, nums[i]);
      }
    }
    return map.keySet().stream().mapToInt(Integer::intValue).toArray();
  }
}
