package com.dmos5.projeto_marisol_dmos5.controler;

import android.content.Context;

import com.dmos5.projeto_marisol_dmos5.helper.TicketHelper;
import com.dmos5.projeto_marisol_dmos5.model.Ticket;

public class TicketController {

    TicketHelper ticketHelper;

    public TicketController(Context ctx) {
        ticketHelper = new TicketHelper(ctx);
    }

    public void insert(Ticket ticket){
        ticketHelper.ticketInsert(ticket);
    }

    public Ticket getTicket(String ticketID){
        return ticketHelper.ticketGet(ticketID);
    }
}
