import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

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
