package com.mock.bookmyticket.data.model;

import com.mock.bookmyticket.data.MetroType;

import java.util.ArrayList;

/**
 * Entity Class for Station Data
 */

public class Station {

    public int stationID;
    public String stationName;
    public boolean isInterchangeable;
    public MetroType metroType;
    public ArrayList<Integer> adjacentStationIDs;

    public Station() {
        adjacentStationIDs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return stationName;
    }
}
