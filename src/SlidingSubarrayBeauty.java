import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingSubarrayBeauty {
    public static void main(String[] args) {
        int[] array = {1, -1, -3, -2, 3};

    }

    // Attempt 3 (doesn't work)

    public static int[] getSubarrayBeauty3(int[] nums, int k, int x) {

        int resultIndex = 0;
        int[] temp = new int[k];
        Deque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (deque.size() == k) {
                temp = deque.stream().mapToInt(Integer::valueOf).toArray();
                Arrays.sort(temp);

                result[resultIndex] = Math.min(temp[x - 1], 0);
                resultIndex++;
                deque.pollFirst();
                deque.add(nums[i]);
                continue;
            }
            deque.add(nums[i]);
        }
        temp = deque.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(temp);

        result[resultIndex] = Math.min(temp[x - 1], 0);
        return result;
    }

    // Attempt 2 (doesn't work)

    public static int[] getSubarrayBeauty2(int[] nums, int k, int x) {

        int resultIndex = 0;
        int[] temp = new int[k];
        int[] result = new int[nums.length - k + 1];
        int counter = 0;

        for (int i = 0; i < nums.length + 1; i++) {

            if (counter == k) {

                int left = 0;
                int right = temp.length - 1;

                while (left <= right) {

                    int pivot = temp[right];
                    int storeIndex = left;
                    boolean hasNegative = true;

                    for (int j = left; j < right; j++) {
                        if (temp[j] < pivot) {
                            int temp1 = temp[storeIndex];
                            temp[storeIndex] = temp[j];
                            temp[j] = temp1;
                            storeIndex++;
                        }
                    }

                    int temp1 = temp[storeIndex];
                    temp[storeIndex] = temp[right];
                    temp[right] = temp1;

                    if (storeIndex == x - 1) {
                        if (temp[storeIndex] > 0) {
                            hasNegative = false;
                        }

                        counter = 0;

                        if (hasNegative) {
                            result[resultIndex] = temp[storeIndex];
                        } else {
                            result[resultIndex] = 0;
                        }

                        i -= k;
                        resultIndex++;
                        break;

                    } else if (storeIndex < x - 1) {
                        left = storeIndex + 1;
                    } else {
                        right = storeIndex - 1;
                    }
                }

            } else if (i != nums.length) {
                temp[counter] = nums[i];
                counter++;
            }
        }
        return result;
    }


    // Attempt 1 (doesn't work)
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
            } else if (i != nums.length) {
                temp[counter] = nums[i];
                counter++;
            }
        }
        return result;
    }
}