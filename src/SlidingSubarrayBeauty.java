import java.util.Arrays;

public class SlidingSubarrayBeauty {
    public static void main(String[] args) {
        int[] array =  {1,-1,-3,-2,3};

        getSubarrayBeauty(array, 3,2);
    }

    // Attempt 1
    // Get every possible array combination with the length k.
    // Filter out by checking whether the xth smallest integer is negative.

    public static int[] getSubarrayBeauty(int[] nums, int k, int x) {

        int resultIndex = 0;
        int[] temp = new int[k];
        int[] result = new int[nums.length - k + 1];
        int counter = 0;

        for (int i = 0; i < nums.length + 1; i++) {
            if (counter == k) {
                Arrays.sort(temp);
                result[resultIndex] = Math.min(temp[x - 1], 0);
                counter = 0;
                i -= k;
                resultIndex++;
            } else if(i != nums.length) {
                temp[counter] = nums[i];
                counter++;
            }
        }
        return result;
    }
}