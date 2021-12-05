import java.util.*;
import java.util.stream.Collectors;

public class TwoOutOfThree {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> hitCountForInteger = new HashMap<>();

        UpdateHitCount(nums1, hitCountForInteger);
        UpdateHitCount(nums2, hitCountForInteger);
        UpdateHitCount(nums3, hitCountForInteger);

        return hitCountForInteger.keySet().stream().filter(k -> hitCountForInteger.get(k) >= 2).collect(Collectors.toList());
    }

    private void UpdateHitCount(int[] nums1, Map<Integer, Integer> hitCountForInteger) {
        Arrays.stream(nums1)
                .distinct()
                .forEach(i ->
                {
                    if (hitCountForInteger.containsKey(i))
                    {
                        Integer hitCount = hitCountForInteger.get(i);
                        hitCountForInteger.put(i, ++hitCount);
                    }
                    else
                    {
                        hitCountForInteger.put(i, 1);
                    }
                });
    }

}
