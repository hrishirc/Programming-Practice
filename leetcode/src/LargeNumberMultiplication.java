import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LargeNumberMultiplication {
    public static void main(String[] args) {
        int k;
        int[] arr = new int[]{1,2,3};
        TreeMap<Integer, Long> collect = Arrays.stream(arr)
                .mapToObj(i -> i)
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));

        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        set.stream().limit(3).reduce((a,b) -> a*b);
    }

    public String multiplyLargeNos(String a, String b)
    {
        List<List<Integer>> parts = new ArrayList<>();

        return null;
    }
}
