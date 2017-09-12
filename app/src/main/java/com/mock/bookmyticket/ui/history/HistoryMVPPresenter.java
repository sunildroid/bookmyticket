

package com.mock.bookmyticket.ui.history;

import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.MvpPresenter;

import java.util.List;


public interface HistoryMVPPresenter extends MvpPresenter {

    void shareTicket(Ticket ticket);

    List<Ticket> getBookedTicketHistory();

}
