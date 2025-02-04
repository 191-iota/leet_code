import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

  // attempt 3 - recursionnnnn - works
  public static List<List<Integer>> combinationSum3(int[] candidates, int target) {
    List<Integer> current = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    backtrack(result, current, candidates, 0, target);

    return result;
  }

  private static void backtrack(
      List<List<Integer>> result, List<Integer> current, int[] candidates, int start, int target) {

    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      if (candidates[i] > target) {
        continue;
      }

      current.add(candidates[i]);
      backtrack(result, current, candidates, i, target - candidates[i]);
      current.remove(current.size() - 1);
    }
  }

  // attempt 2 - does not work - probably should use recursion
  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    int currentSum = 0;
    int pointer = 0;

    List<List<Integer>> resultList = new ArrayList<>();
    List<Integer> currentList = new ArrayList<>();

    for (int i = 0; i < candidates.length; i++) {
      for (int j = 0; j < candidates.length; j++) {
        for (int k = 0; k < candidates.length; k++) {
          for (int l = 0; l < candidates.length; l++) {

            if (currentSum + candidates[l] < target) {
              currentList.add(candidates[l]);

            } else if (currentSum + candidates[l] > target) {

              currentList.removeLast();
              break;

            } else {
              resultList.add(currentList);
            }
          }
        }
      }
    }
    return null;
  }

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
