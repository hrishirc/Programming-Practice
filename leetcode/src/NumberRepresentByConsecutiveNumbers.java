import kotlin.Pair;
import util.NumbersUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NumberRepresentByConsecutiveNumbers {

    NumbersUtil numbersUtil = new NumbersUtil();

    public static void main(String[] args) {
        Long consecutiveNumberRepresentations = new NumberRepresentByConsecutiveNumbers().get(5);
        System.out.println(consecutiveNumberRepresentations);
        List<Integer>[][] resultList = new List[1][2];
    }

    public Long get(Integer input) {
        Set<Integer> allFactors = numbersUtil.getAllFactors(input);

        Set<Double> allPossibleOptions = allFactors.stream()
                .filter(i -> !Arrays.asList(1,input).contains(i))
                .map(i -> (double) i)
                .collect(Collectors.toSet());

        allFactors.stream()
                .filter(i -> i != 1)
                .map(i -> (double) i / 2)
                .forEach(allPossibleOptions::add);

        allPossibleOptions.stream()
                .map(o -> new Pair<>(o, input / o))
                .filter(p ->
                        p.component1() % 1 != 0
                                ? p.component2() % 2 == 0
                                : p.component2() % 2 == 1)
                .filter(p -> 2*p.component1() > p.component2())
                .forEach(System.out::println);

        return allPossibleOptions.stream()
                .map(o -> new Pair<>(o, input / o))
                .filter(p ->
                        p.component1() % 1 != 0
                                ? p.component2() % 2 == 0
                                : p.component2() % 2 == 1)
                .filter(p -> 2*p.component1() > p.component2())
                .count();
    }
}