public class JumpGameII {
    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println(jump(nums));
    }
    
    // attempt 1 - does not work
    public static int jump(int[] nums) {
        if(nums.length == 1){
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
