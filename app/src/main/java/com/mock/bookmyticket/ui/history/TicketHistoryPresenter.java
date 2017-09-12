package com.mock.bookmyticket.ui.history;

import com.mock.bookmyticket.data.DataManager;
import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.MvpView;

import java.util.List;


public class TicketHistoryPresenter implements HistoryMVPPresenter {

    TicketHistoryView ticketHistoryView;

    TicketHistoryPresenter(TicketHistoryView view) {
        this.ticketHistoryView = view;
    }

    @Override
    public void shareTicket(Ticket ticket) {
        ticketHistoryView.shareTicket(ticket);
    }

    public List<Ticket> getBookedTicketHistory() {
        return DataManager.getInstance().getBookingHistory();
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
}
