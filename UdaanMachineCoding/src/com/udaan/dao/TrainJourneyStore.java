package com.udaan.dao;

import com.udaan.model.realtime.TrainJourney;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TrainJourneyStore {

    Map<String, TrainJourney> trainJourneyMap = new HashMap<>();
    private static final TrainJourneyStore trainJourneyStore = new TrainJourneyStore();

    public synchronized static TrainJourneyStore getInstance()
    {
        return trainJourneyStore;
    }

    public TrainJourney get(long trainId, LocalDate date)
    {
        String key = trainId + date.toString();
        if (!trainJourneyMap.containsKey(key)) {
            trainJourneyMap.put(key, new TrainJourney(TrainStore.getInstance().getTrain(trainId), date));
        }

        return trainJourneyMap.get(key);
    }
}
