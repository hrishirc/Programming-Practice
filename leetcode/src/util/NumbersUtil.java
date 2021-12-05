package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumbersUtil {

    public Set<Integer> getAllFactors(Integer i) {
        List<Integer> baseFactors = getFactors(i);

        return getCompositeFactors(baseFactors);
    }

    public List<Integer> getFactors(Integer i) {
        List<Integer> factorList = new ArrayList<>();
        for (int j = 2; j <= i; j++) {
            if (i % j == 0) {
                factorList.add(j);
                factorList.addAll(getFactors(i / j));
                break;
            }
        }

        factorList.add(1);

        return factorList;
    }

    public Set<Integer> getCompositeFactors(List<Integer> factors) {
        Set<Integer> combinations = new HashSet<>();
        List<Integer> remainingFactors = new ArrayList<>(factors);

        Integer factor = remainingFactors.get(0);
        if (factor != 1) {
            remainingFactors.remove(factor);
            if (!remainingFactors.isEmpty()) {
                Set<Integer> createdCombinations = getCompositeFactors(remainingFactors);
                combinations.addAll(createdCombinations);

                createdCombinations.stream().map(com -> com * factor).forEach(combinations::add);
            }
        }

        combinations.add(factor);

        return combinations;
    }
}
