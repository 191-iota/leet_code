public class JumpGameII {

  public static void main(String[] args) {
    int[] nums = {2, 3, 0, 1, 4};
    System.out.println(jump3(nums));
  }

  public static int jump3(int[] nums) {
    int index = 0;
    short jumps = 0;
    while (index < nums.length - 1) {
      int highIndex = index + 1;

      for (int i = index + 1; i < index + nums[index] + 1 && i < nums.length; i++) {
        // determine largest jump
        if ((i + nums[i] > highIndex + nums[highIndex] || i == nums.length - 1)) {
          highIndex = i;
        }
      }
      index = highIndex;
      jumps++;
    }
    return jumps;
  }

  // attempt 2 - does not work
  public static int jump2(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }

    int index = 0;
    int jumps = 0;
    while (index < nums.length - 1) {
      int highestJumpIndex = 0;

      for (int i = index + 1; i < index + nums[index] + 1 && i < nums.length; i++) {
        // determine largest jump
        if (i == nums.length - 1) {
          return ++jumps;

        } else if (i + nums[i] > highestJumpIndex) {
          highestJumpIndex = i;
        }
      }
      index = highestJumpIndex;
      jumps++;
    }
    return jumps;
  }

  // attempt 1 - does not work
  public static int jump(int[] nums) {
    if (nums.length == 1) {
      return 1;
    }

    int index = 0;
    int jumps = 0;
    while (index < nums.length - 1) {
      int highestJumpIndex = 0;

      for (int i = index; i < index + nums[index]; i++) {
        // determine largest jump
        if ((nums[i] + index) >= nums.length) {
          return ++jumps;
        } else if (nums[i] > nums[highestJumpIndex] && (nums[i] + index) < nums.length) {
          highestJumpIndex = i + index;
          break;
        }
      }
      index += highestJumpIndex;
      jumps++;
    }
    return jumps;
  }
}
