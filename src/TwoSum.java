import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] array =  {-1,-2,-3,-4,-5};

        twoSum(array, -8);
    }

    // Attempt 3 (doesn't work)
    public static int[] twoSum3(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        int currentSum = 0;

        outer: for (int i = 0; i < nums.length; i++) {
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
                    } else if(target == 0 && Math.abs(currentSum + nums[i]) == target) {
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
                    } else if(target == 0 && Math.abs(currentSum + nums[i]) == target) {
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
