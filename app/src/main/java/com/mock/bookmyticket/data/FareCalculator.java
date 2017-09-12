package com.mock.bookmyticket.data;

import com.mock.bookmyticket.data.model.Station;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class responsible for Fare Calculation Logic. This class uses Station data in matrix format
 * For input provided , it has faster running time than using adjacency List.
 * <p>
 * It is considered  that user has to Pay for every station including source station
 *
 *  Refer Statement  -  "INR 2.00 for every station other than interchange station."
 *  Here It is assumed that every station also includes source station.
 * <p>
 * For Adjacent list implementation see class FareCalculatorWithList using heap implementation.
 */
class FareCalculator {

    private static final int NORMAL_FARE = 2;
    private static final int INTERCHANGE_FARE = 5;
    private int noOfStations;
    private int[][] stationMatrix;
    private int[] fareMatrix;

    //Contructor To Initialize matrix
    public FareCalculator() {

/*
       Mock Station Data
        Station ID  	Station Name 	  Fare
        0	            Karol bagh	        2
        1            	Jhande wala     	2
        2	            RK Ashram    	    2
        3	            Rajiv Chawk     	5
        4	            Barakhamba	        2
        5           	Mandi house     	5
        6           	Okhla	            2
        7           	New Delhi          	2
        8            	Patel Chawk        	2
        9           	INA	                2
        */

        HashMap stations = DataManager.getInstance().getDummyStations();
        noOfStations = stations.size();
        stationMatrix = new int[noOfStations][noOfStations];
        fareMatrix = new int[noOfStations];

        for (int i = 0; i < stations.size(); i++) {
            Station station = (Station) stations.get(i);
            ArrayList<Integer> adjacentStations = station.adjacentStationIDs;

            for (int adjStation : adjacentStations) {
                stationMatrix[i][adjStation] =
                        (((Station) stations.get(adjStation)).isInterchangeable) ?
                                INTERCHANGE_FARE : NORMAL_FARE;
            }
            fareMatrix[i] = (station.isInterchangeable ? INTERCHANGE_FARE : NORMAL_FARE);
        }


    }


    /**
     * Method to  calculate Fare using Dijkstra's Algorithm.
     *
     * @param src  Source Station ID
     * @param dest Destination Station ID
     * @return fare between src and dest
     */
    int calculateFare(int src, int dest) {
        int minDist[] = new int[noOfStations]; // Array to hold shortest distance

        // shortestPathSet[i] will true if Station  i is included in path
        Boolean shortestPathSet[] = new Boolean[noOfStations];

        // init  all distances with default values
        for (int i = 0; i < noOfStations; i++) {
            minDist[i] = Integer.MAX_VALUE;
            shortestPathSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        minDist[src] = 0;

        // Find shortest path for all Stations
        for (int stationID = 0; stationID < noOfStations - 1; stationID++) {

            int u = calculateShortestDistance(minDist, shortestPathSet);
            if(u==dest)
                break;
            // Mark the station  visited as true
            shortestPathSet[u] = true;

            for (int v = 0; v < noOfStations; v++) {
                if (!shortestPathSet[v] && stationMatrix[u][v] != 0 &&
                        minDist[u] != Integer.MAX_VALUE &&
                        minDist[u] + stationMatrix[u][v] < minDist[v])
                    minDist[v] = minDist[u] + stationMatrix[u][v];
            }
        }

        return fareAdjustment(minDist, noOfStations, src, dest);
    }

   private int calculateShortestDistance(int dist[], Boolean shortestPathSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < noOfStations; v++)
            if (shortestPathSet[v]==false&& dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    /**
     * Function to make final adjustment in fare by adding source station fare
     * It is considered that user has to pay cost for source station also
     *
     * Do not use this method if source station cost is not to be included
     *
     * @param minDist Array to hold shortest distance
     * @param n       No of Stations
     * @param src     Source Station ID
     * @param dest    Destination Station ID
     * @return fare
     */
    private int fareAdjustment(int minDist[], int n, int src, int dest) {
        int fare = 0;
        for (int i = 0; i < n; i++) {
            fare = (minDist[i] + fareMatrix[src]);
            if (i == dest)
                return fare;
        }
        return fare;
    }

    /**
     * Main Method for individual Class test
     *
     * @param args default argument
     */
    /*public static void main(String[] args) {
        HashMap stations = DataManager.getInstance().getDummyStations();
        long startTime = System.currentTimeMillis();
        FareCalculator t = new FareCalculator();
        System.out.println("output->" + t.calculateFare(8, 7));
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("" + elapsedTime);
    }*/
}