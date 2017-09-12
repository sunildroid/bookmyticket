package com.mock.bookmyticket.data.local;

import com.mock.bookmyticket.data.IDataProvider;
import com.mock.bookmyticket.data.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage Local Cache within the app
 */
public class LocalCache implements IDataProvider {
    private List<Ticket> bookedTicketList;

    @Override
    public List<Ticket> getBookedTickets() {
        return bookedTicketList;
    }

    /**
     *
     * @param ticket - booked ticket details
     * @return boolean flag if ticket is booked or not
     */
    @Override
    public boolean bookTicket(Ticket ticket) {
        if (bookedTicketList == null) {
            bookedTicketList = new ArrayList<>();
        }
        // for local cache set item position in list as ticket id
        ticket.setTicketId(String.valueOf(bookedTicketList.size()));
        return bookedTicketList.add(ticket);
    }
}
