import util.NumbersUtil;

import java.util.*;

public class SumOfAllFactors {

    NumbersUtil numbersUtil = new NumbersUtil();

    /*
     * Complete the 'maxSubsetSum' function below.
     *
     * The function is expected to return a LONG_INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY k as parameter.
     */
    public static void main(String[] args) {
        List<Integer> intList = List.of(27);
        System.out.println(new SumOfAllFactors().maxSubsetSum(intList));
    }

    public List<Long> maxSubsetSum(List<Integer> k) {
        // Write your code here

        List<Long> factorsSum = new ArrayList<>();
        k.forEach(i ->
        {
            Set<Integer> factors = numbersUtil.getAllFactors(i);
            factorsSum.add(factors.stream().distinct().mapToLong(j -> (long) j).sum());
        });

        return factorsSum;
    }

}