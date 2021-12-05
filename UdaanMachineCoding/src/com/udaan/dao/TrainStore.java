package com.udaan.dao;

import com.udaan.model.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TrainStore {

    AtomicLong counter = new AtomicLong(0);
    Map<Long, Train> trainMap = new HashMap<>();
    private static final TrainStore trainStore = new TrainStore();

    public synchronized static TrainStore getInstance()
    {
        return trainStore;
    }

    public long add(int noOfCoaches, int cabinsPerCoach)
    {
        Train train = new Train(counter.getAndAdd(1), noOfCoaches, cabinsPerCoach);
        trainMap.put(train.id, train);
        return train.id;
    }

    public Train getTrain(long id)
    {
        return trainMap.get(id);
    }
}
