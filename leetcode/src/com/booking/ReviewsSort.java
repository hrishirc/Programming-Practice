package com.booking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReviewsSort {
    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {
        // Write your code here

        String[] positiveKeywordsList = positiveKeywords.split(" ");
        String[] negativeKeywordsList = positiveKeywords.split(" ");

        Map<Integer, Long> reviewsForHotels = IntStream.range(0, hotelIds.size())
                .boxed()
                .collect(Collectors.groupingBy(hotelIds::get,
                        Collectors.summingLong(i -> getWeight(reviews.get(i), positiveKeywordsList, negativeKeywordsList))));

        return reviewsForHotels.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static int getWeight(String review, String[] positiveKeywordsList, String[] negativeKeywordsList)
    {
        int weight = 0;

        weight += 3*Arrays.stream(positiveKeywordsList)
                .map(keyword -> review.split(keyword, -1).length - 1)
                .mapToLong(l -> l).sum();

        weight -= (Long) Arrays.stream(negativeKeywordsList)
                .map(keyword -> review.split(keyword, -1).length - 1)
                .mapToLong(l -> l).sum();

        return weight;
    }
}
