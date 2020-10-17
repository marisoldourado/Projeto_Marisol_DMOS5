package com.dmos5.projeto_marisol_dmos5.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TicketSearchResponse implements Serializable {

    @SerializedName("TicketID")
    @Expose
    private List<String> ticketID;

    public List<String> getTicketID() {
        return ticketID;
    }

    public void setTicketID(List<String> ticketID) {
        this.ticketID = ticketID;
    }

    @Override
    public String toString() {
        return "TicketSearchResponse{" + "ticketID=" + ticketID + '}';
    }
}
