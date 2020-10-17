package com.dmos5.projeto_marisol_dmos5.response;

import com.dmos5.projeto_marisol_dmos5.model.Ticket;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TicketResponse {

    @SerializedName("Ticket")
    @Expose
    private List<Ticket> ticket = null;

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "TicketResponse{" + "ticketID=" + ticket.get(0).getTicketNumber() + '}';
    }
}
