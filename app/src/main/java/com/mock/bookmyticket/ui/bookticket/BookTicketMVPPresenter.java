package com.mock.bookmyticket.ui.bookticket;

import com.mock.bookmyticket.data.model.Station;
import com.mock.bookmyticket.ui.base.MvpPresenter;

/**
 * Created by sunilkuntal on 9/10/2017.
 */

public interface BookTicketMVPPresenter extends MvpPresenter {

    void onToStationSelected(int position, Station stationTo);

    void onFromStationSelected(int position, Station stationFrom);

    void calculateFare();

    void showTicket();

    void loadStations();

}
