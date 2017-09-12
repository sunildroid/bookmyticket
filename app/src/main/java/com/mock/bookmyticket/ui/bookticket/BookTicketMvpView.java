

package com.mock.bookmyticket.ui.bookticket;

import com.mock.bookmyticket.data.model.Station;
import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.MvpView;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookTicketMvpView extends MvpView {


    void onStationFromSelected(ArrayList<Station> station);

    void onStationToSelected(ArrayList<Station> station);

    void displayStatus(int fare);

    void refreshStations(HashMap stations);

    Ticket getTicketViewModel();

    void setBookingStatus(boolean isBookingSuccess);

    void onBookingStart();
}
