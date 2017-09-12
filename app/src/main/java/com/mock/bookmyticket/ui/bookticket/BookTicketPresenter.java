package com.mock.bookmyticket.ui.bookticket;

import com.mock.bookmyticket.data.DataManager;
import com.mock.bookmyticket.data.model.Station;
import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by sunilkuntal on 9/10/2017.
 */

public class BookTicketPresenter implements BookTicketMVPPresenter {

    BookTicketMvpView bookTicketMvpView;
    DataManager dataManager;
    private boolean status;
    private int idStationTo;
    private int idStationFrom;

    BookTicketPresenter(BookTicketMvpView bookTicketMvpView) {
        this.bookTicketMvpView = bookTicketMvpView;
        dataManager = DataManager.getInstance();
    }

    @Override
    public void onToStationSelected(int position, Station station) {
        this.idStationTo = station.stationID;
        ArrayList<Station> listTo = new ArrayList<Station>(dataManager.getDummyStations().values());
        listTo.remove(position);
        bookTicketMvpView.onStationToSelected(listTo);
    }

    @Override
    public void onFromStationSelected(int position, Station station) {
        this.idStationFrom = station.stationID;
        ArrayList<Station> listTo = new ArrayList<Station>(dataManager.getDummyStations().values());
        listTo.remove(position);
        bookTicketMvpView.onStationFromSelected(listTo);
    }

    @Override
    public void calculateFare() {

    }

    @Override
    public void showTicket() {

    }

    @Override
    public void loadStations() {
        bookTicketMvpView.refreshStations(dataManager.getDummyStations());
    }

    @Override
    public void onAttach(MvpView mvpView) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void handleApiError(Error error) {

    }


    void bookTicketClicked() {
        Ticket bookedTicket = bookTicketMvpView.getTicketViewModel();
        if (null != bookedTicket) {
            bookTicketMvpView.showLoading();
            int fare = dataManager.calculateFare(idStationFrom, idStationTo);
            bookedTicket.setPurchaseCost(fare);
            status = DataManager.getInstance().bookTicket(bookedTicket);
            bookTicketMvpView.onBookingStart();
            bookTicketMvpView.hideKeyboard();
            bookTicketMvpView.displayStatus(fare);
        } else {
            bookTicketMvpView.onError("Please fill required fields");
        }
    }

    void onTimeLapsed() {
        bookTicketMvpView.setBookingStatus(status);
        bookTicketMvpView.hideLoading();
    }
}
