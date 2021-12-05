package com.udaan.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    public  long id;
    public List<Coach> coachList = new ArrayList<>();

    public Train(long id, int noOfCoaches, int cabinsPerCoach) {
        this.id = id;
        for (int i = 0; i < noOfCoaches; i++)
        {
            coachList.add(new Coach(cabinsPerCoach));
        }
    }
}
