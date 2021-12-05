package com.booking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeautifulSubarrays {

    public static void main(String[] args) {
        System.out.println(new BeautifulSubarrays().beautifulSubarrays(Arrays.asList(6,1156,738,299,220,1982,791),1));
    }

    public long beautifulSubarrays(List<Integer> arr, int numOdds) {
        // Write your code here
        List<Integer> oddNoIdxList = IntStream.range(0, arr.size())
                .filter(i -> arr.get(i) % 2 == 1)
                .boxed()
                .collect(Collectors.toList());

        if (numOdds > oddNoIdxList.size())
        {
            return 0;
        }

        int subArrayNo = 0;
        int oddNoNos = oddNoIdxList.size();
        for (int i = numOdds; i <= oddNoNos; i++)
        {
            int groupStartIdx = i == numOdds ? -1 : oddNoIdxList.get(i - numOdds - 1);
            int groupEndIdx = i == oddNoNos ? arr.size() : oddNoIdxList.get(i);

            subArrayNo += (oddNoIdxList.get(i - numOdds) - groupStartIdx);
            subArrayNo += (groupEndIdx - oddNoIdxList.get(i - 1));

            if (numOdds == 1)
            {
                subArrayNo -= 1;
            }
        }

        return subArrayNo;
    }
}
