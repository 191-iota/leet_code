import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    
    // attempt 1 - this was my first thought, first thoughts are rarely successful, does not work
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int currentSum = 0;
        int pointer = 0;
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // First inner list
        result.add(new ArrayList<>()); // Second inner list

        while (currentSum < target) {
            if (candidates[pointer] < target) {
                result.get(0).add(candidates[pointer]);
                currentSum += candidates[pointer];
                target -= candidates[pointer];
                pointer++;
            }
        }

        if (!result.get(0).isEmpty()) {
            result.get(1).add(result.get(0).size());
        }
        return result;
    }
}
