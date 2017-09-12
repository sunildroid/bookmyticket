package com.mock.bookmyticket.data;

import com.mock.bookmyticket.data.local.LocalCache;
import com.mock.bookmyticket.data.model.Station;
import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.data.remote.RemoteNetworkClient;
import com.mock.bookmyticket.ui.Constants;

import java.util.HashMap;
import java.util.List;

/**
 *  Singleton  Class for data handling.
 *  It interact with network client , Local cache and FareCalculator class and provide data
 *  to Presenters
 */

public class DataManager {

    private static DataManager dataManager;
    private HashMap mapStations;
    private IDataProvider dataProvider;
    // Data Source for now is set to Local
    private Constants.DATA_SOURCE_TYPE dataSource = Constants.DATA_SOURCE_TYPE.SOURCE_TYPE_LOCAL;

    /**
     * To get Map of Test Stations with data provided in problem statement
     * @return Map of Stations
     */
    public HashMap getDummyStations() {
        return mapStations;
    }


    private DataManager() {

    /*
    Representation Of Ids
        0	Karol bagh
        1	Jhande wala
        2	RK Ashram,
        3	Rajiv Chawk
        4	Barakhamba
        5	Mandi house
        6	Okhla
        7	New Delhi
        8	Patel Chawk
        9	INA
        */


        //Initialize Dummy Stations for now

        Station stKarolBagh = new Station();
        stKarolBagh.isInterchangeable = false;
        stKarolBagh.stationName = "Karolbagh";
        stKarolBagh.stationID = 0;
        stKarolBagh.metroType = MetroType.BLUE_LINE;
        stKarolBagh.adjacentStationIDs.add(1);


        Station stJhandewalan = new Station();
        stJhandewalan.isInterchangeable = false;
        stJhandewalan.stationName = "Jhandewalan";
        stJhandewalan.stationID = 1;
        stJhandewalan.metroType = MetroType.BLUE_LINE;
        stJhandewalan.adjacentStationIDs.add(0);
        stJhandewalan.adjacentStationIDs.add(2);

        Station stRKAshram = new Station();
        stRKAshram.isInterchangeable = false;
        stRKAshram.stationName = "RK Ashram";
        stRKAshram.stationID = 2;
        stRKAshram.metroType = MetroType.BLUE_LINE;
        stRKAshram.adjacentStationIDs.add(1);
        stRKAshram.adjacentStationIDs.add(3);

        Station stRajeevChowk = new Station();
        stRajeevChowk.isInterchangeable = true;
        stRajeevChowk.stationName = "RajeevChowk";
        stRajeevChowk.stationID = 3;
        stRajeevChowk.metroType = MetroType.MULTI_COLOR_LINE;
        stRajeevChowk.adjacentStationIDs.add(2);
        stRajeevChowk.adjacentStationIDs.add(4);
        stRajeevChowk.adjacentStationIDs.add(7);
        stRajeevChowk.adjacentStationIDs.add(8);


        Station stNewDelhi = new Station();
        stNewDelhi.isInterchangeable = false;
        stNewDelhi.stationName = "New Delhi";
        stNewDelhi.stationID = 7;
        stNewDelhi.metroType = MetroType.YELLOW_LINE;
        stNewDelhi.adjacentStationIDs.add(3);

        Station stPatelChowk = new Station();
        stPatelChowk.isInterchangeable = false;
        stPatelChowk.stationName = "PatelChowk";
        stPatelChowk.stationID = 8;
        stPatelChowk.metroType = MetroType.YELLOW_LINE;
        stPatelChowk.adjacentStationIDs.add(3);
        stPatelChowk.adjacentStationIDs.add(9);

        Station stINA = new Station();
        stINA.isInterchangeable = false;
        stINA.stationName = "INA";
        stINA.stationID = 9;
        stINA.metroType = MetroType.YELLOW_LINE;
        stINA.adjacentStationIDs.add(8);


        Station stBarakhamba = new Station();
        stBarakhamba.isInterchangeable = false;
        stBarakhamba.stationName = "Barakhamba";
        stBarakhamba.stationID = 4;
        stBarakhamba.metroType = MetroType.BLUE_LINE;
        stBarakhamba.adjacentStationIDs.add(3);
        stBarakhamba.adjacentStationIDs.add(5);

        Station stMandiHouse = new Station();
        stMandiHouse.isInterchangeable = true;
        stMandiHouse.stationName = "Mandi House";
        stMandiHouse.stationID = 5;
        stMandiHouse.metroType = MetroType.MULTI_COLOR_LINE;
        stMandiHouse.adjacentStationIDs.add(4);
        stMandiHouse.adjacentStationIDs.add(6);


        Station stOkhla = new Station();
        stOkhla.isInterchangeable = false;
        stOkhla.stationName = "Okhla";
        stOkhla.stationID = 6;
        stOkhla.metroType = MetroType.GREEN_LINE;
        stOkhla.adjacentStationIDs.add(5);


        mapStations = new HashMap();
        mapStations.put(0, stKarolBagh);
        mapStations.put(1, stJhandewalan);
        mapStations.put(2, stRKAshram);
        mapStations.put(3, stRajeevChowk);
        mapStations.put(4, stBarakhamba);
        mapStations.put(5, stMandiHouse);
        mapStations.put(6, stOkhla);
        mapStations.put(7, stNewDelhi);
        mapStations.put(8, stPatelChowk);
        mapStations.put(9, stINA);

    }

    /**
     * public method to resturn Singleton instance of DataManager
     * @return instance of DataManager
     */
    public static DataManager getInstance() {
        if (dataManager == null) {
            synchronized (DataManager.class) {
                if (dataManager == null)
                    dataManager = new DataManager();
            }
        }
        return dataManager;
    }

    /**
     * Method to set data type  in DataProvider
     * @param dataSource set Data source type
     */
    public void setDataSource(Constants.DATA_SOURCE_TYPE dataSource) {
        this.dataSource = dataSource;
        if (dataSource == Constants.DATA_SOURCE_TYPE.SOURCE_TYPE_LOCAL) {
            dataProvider = new LocalCache();
        } else if (dataSource == Constants.DATA_SOURCE_TYPE.SOURCE_TYPE_REMOTE_SERVER) {
            dataProvider = new RemoteNetworkClient();
        }
    }

    /**
     *  Save Booked ticket in Data Provider
     * @param ticket booked ticket details
     * @return
     */
    public boolean bookTicket(Ticket ticket) {
        if (null != dataProvider) {
            return dataProvider.bookTicket(ticket);
        }
        return false;
    }

    /**
     * Method to provide booking history to Presenter
     * @return List<Ticket> list of Tickets
     */
    public List<Ticket> getBookingHistory() {
        if (null != dataProvider) {
            return dataProvider.getBookedTickets();
        }
        return null;
    }

    /**
     *  Method to provide calculated Fare  to Presenters
     * @param source id for Source Station
     * @param destination id for Destination
     * @return
     */
    public int calculateFare(int source, int destination) {
        FareCalculator t = FareCalculator.getInstance();
        return t.calculateFare(source, destination);
    }
}
