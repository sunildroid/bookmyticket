package com.mock.bookmyticket.ui.history;

import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.MvpView;


public interface TicketHistoryView extends MvpView {

    void shareTicket(Ticket selectedTicket);
}
