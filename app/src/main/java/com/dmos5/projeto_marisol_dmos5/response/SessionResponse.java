package com.dmos5.projeto_marisol_dmos5.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionResponse {

    @SerializedName("SessionID")
    @Expose
    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}

