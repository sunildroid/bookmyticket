package com.mock.bookmyticket.data;

import com.mock.bookmyticket.data.model.Ticket;

import java.util.List;


public interface IDataProvider {

    List<Ticket> getBookedTickets();

    boolean bookTicket(Ticket ticket);
}
