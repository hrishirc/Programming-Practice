package com.udaan.model;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    public List<Cabin> cabins = new ArrayList<>();

    public Coach(int cabinsPerCoach) {
        for (int i = 0; i < cabinsPerCoach; i++)
        {
            cabins.add(new Cabin());
        }
    }
}
