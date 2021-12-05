package com.udaan.model;

public class Berth {
    public BerthType berthType;
    public BerthLocation berthLocation;

    public long userIdOccupied = -1;

    public Berth(BerthType berthType, BerthLocation berthLocation) {
        this.berthType = berthType;
        this.berthLocation = berthLocation;
    }
}
