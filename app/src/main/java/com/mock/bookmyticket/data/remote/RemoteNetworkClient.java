package com.mock.bookmyticket.data.remote;

import com.mock.bookmyticket.data.IDataProvider;
import com.mock.bookmyticket.data.model.Ticket;

import java.util.List;

/**
 *  Network Client to fetch data from web service
 *
 */
public class RemoteNetworkClient implements IDataProvider {
    @Override
    public List<Ticket> getBookedTickets() {
        return null;
    }

    @Override
    public boolean bookTicket(Ticket ticket) {
        return false;
    }
}
