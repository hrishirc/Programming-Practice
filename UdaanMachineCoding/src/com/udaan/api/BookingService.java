package com.udaan.api;

import com.udaan.api.util.BookingPreference;
import com.udaan.dao.TrainStore;
import com.udaan.dao.UserStore;
import com.udaan.model.Berth;
import com.udaan.model.Train;

import java.util.List;
import java.util.stream.Collectors;

import static com.udaan.model.BerthType.LOWER;

public class BookingService {

    private static final BookingService bookingService = new BookingService();
    public synchronized static BookingService getInstance()
    {
        return bookingService;
    }

    public List<Berth> bookTickets(long userId, long trainId, int noOfTickets, BookingPreference preference)
    {
        boolean userExist = UserStore.getInstance().isUserExist(userId);
        if (!userExist)
        {
            throw new RuntimeException("User not found!");
        }

        Train train = TrainStore.getInstance().getTrain(trainId);
        if (train == null)
        {
            throw new RuntimeException("Train not found!");
        }

        switch (preference)
        {

            case LOWER_BERTH -> {
                List<Berth> availableBerths = train.coachList.stream().map(c -> c.cabins).flatMap(c -> c.stream())
                        .map(c -> c.berthConfiguration.berthConfig.get(LOWER))
                        .map(m -> m.values())
                        .flatMap(l -> l.stream())
                        .flatMap(l -> l.stream())
                        .filter(b -> b.userIdOccupied == -1)
                        .limit(noOfTickets)
                        .collect(Collectors.toList());
            }
            case TOGETHER -> {
            }
        }
    }

}
