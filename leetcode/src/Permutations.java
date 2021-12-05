import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutations {
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> permuteList = new ArrayList<>();

        boolean isStore = true;

        permute(nums, nums.length, permuteList, isStore);

        return permuteList;
    }

    public Map<Integer, List<List<Integer>>> permute(int[] nums, int permLen, List<List<Integer>> permuteList, boolean isStore)
    {
        Map<Integer, List<List<Integer>>> currentPermutes = new HashMap<>();

        if (permLen <= 0)
        {
            return currentPermutes;
        }

        Map<Integer, List<List<Integer>>> lowerLevelPermutes = permute(nums, permLen - 1, permuteList, false);

        for (int i = 0; i < nums.length; i++)
        {
            List<List<Integer>> iPermute = new ArrayList<>();
            currentPermutes.put(i, iPermute);
            List<List<Integer>> permutations = lowerLevelPermutes.get(i);
            if (permutations == null)
            {
                permutations = new ArrayList<>();
            }

            int _i = i;
            permutations.forEach(p ->
            {
                List<Integer> newPermute = new ArrayList<>(permLen);
                newPermute.addAll(p);
                newPermute.add(_i);

                if (isStore)
                    permuteList.add(newPermute);
                else
                    iPermute.add(newPermute);
            });
        }

        return null;
    }
}
