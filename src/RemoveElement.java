public class RemoveElement {
  public static void main(String[] args) {}

  public int removeElement(int[] nums, int val) {

    int k = 0;
    int left = 0;
    int right = nums.length - 1;

    // I'm using the in-place algorithm as suggested from the task
    while (left <= right) {

      if (nums[left] == val) {
        nums[left] = nums[right];
        right--;
        k++;
      } else {
        left++;
      }
    }
    return nums.length - k;
  }
}
