package com.udaan.model.realtime;

import com.udaan.model.Train;

import java.time.LocalDate;

public class TrainJourney {
    Train train;
    LocalDate date;

    public TrainJourney(Train train, LocalDate date) {
        this.train = train;
        this.date = date;
    }
}
