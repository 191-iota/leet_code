import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        removeDuplicates2(nums);
        System.out.println( removeDuplicates2(nums));
    }

    // Attempt 2 (works)
    public static int removeDuplicates2(int[] nums) {
        short k = 1;
        short left = 0;
        short right = 1;

        while (nums.length > right && nums.length > left) {
            if(nums[left] == nums[right]) {
                right++;
            } else {
                nums[left + 1] = nums[right];
                left++;
                k++;
            }
        }
        return k;
    }

    // Attempt 1 (works)
    public static int removeDuplicates(int[] nums) {
        // Can be majorly optimized
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        Iterator<Integer> iterator = set.stream().sorted().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            nums[index++] = iterator.next();
        }

        return set.size();
    }
}
