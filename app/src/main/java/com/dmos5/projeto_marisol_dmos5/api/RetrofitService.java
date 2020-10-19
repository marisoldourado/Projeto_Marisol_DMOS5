package com.dmos5.projeto_marisol_dmos5.api;

import com.dmos5.projeto_marisol_dmos5.response.SessionResponse;
import com.dmos5.projeto_marisol_dmos5.response.TicketResponse;
import com.dmos5.projeto_marisol_dmos5.response.TicketSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    String BASE_URL = "http://147.135.68.213:8030/otrs/nph-genericinterface.pl/Webservice/GenericTicketConnectorREST/";

    @GET("Ticket?")
    Call<TicketSearchResponse> getListTickeID(@Query("UserLogin") String userLogin, @Query("Password") String password);

    @GET("Ticket/{TicketID}?")
    Call<TicketResponse> getTicket(@Path("TicketID") String ticketIDs, @Query("UserLogin") String userLogin, @Query("Password") String password);

    @POST("Session?")
    Call<SessionResponse> createSession(@Query("UserLogin") String userLogin, @Query("Password") String password);
}
