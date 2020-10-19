package com.dmos5.projeto_marisol_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dmos5.projeto_marisol_dmos5.R;
import com.dmos5.projeto_marisol_dmos5.api.RetrofitService;
import com.dmos5.projeto_marisol_dmos5.constants.Constants;
import com.dmos5.projeto_marisol_dmos5.model.Login;
import com.dmos5.projeto_marisol_dmos5.model.Ticket;
import com.dmos5.projeto_marisol_dmos5.response.TicketResponse;
import com.dmos5.projeto_marisol_dmos5.response.TicketSearchResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TicketAdapter adapter;
    private ProgressBar ticketListProgress;
    private List<Ticket> mTicketList;

    private FloatingActionButton btnTicketAdd;
    List<String> listTicketsID;

    private Login login;
    private String username, password;

    private  SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();

        sharedpreferences = getSharedPreferences("PREFERENCE_USER",
                Context.MODE_PRIVATE);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_ticket_list);
        }

        if (sharedpreferences.contains(Constants.SHARED_USERNAME)) {
            username = sharedpreferences.getString(Constants.SHARED_USERNAME, "");
        }
        if (sharedpreferences.contains(Constants.SHARED_PASSWORD)) {
            password = sharedpreferences.getString(Constants.SHARED_PASSWORD, "");
        }

     // btnTicketAdd     = findViewById(R.id.fab_ticket_add);
        mRecyclerView = findViewById(R.id.recycler_list_tickets);
        mImageView    = findViewById(R.id.imgView_empty_list);
        ticketListProgress = findViewById(R.id.ticketListProgress);

        adapter = new TicketAdapter(mTicketList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        getTickets();

       // btnTicketAdd.setOnClickListener(this);
    }

    private void getTickets() {

        //login = new Login(Constants.USERNAME, Constants.PASSWORD);
        login = new Login(username, password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        final Call<TicketSearchResponse> request = service.getListTickeID(login.getUserLogin(), login.getPassword());
        request.enqueue(new Callback<TicketSearchResponse>() {

            @Override
            public void onResponse(Call<TicketSearchResponse> call, Response<TicketSearchResponse> response) {
                if(!response.isSuccessful()){
                    Log.i(Constants.TAG,"ERRO: Network Connection Invalid " + response.code());
                }else{
                    TicketSearchResponse ticketSearchResponse = response.body();

                    listTicketsID = ticketSearchResponse.getTicketID();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    RetrofitService service = retrofit.create(RetrofitService.class);

                    String ticketIDs = "";
                    int listSize = 0;
                    for(String ticketID: listTicketsID){
                        if(listSize != listTicketsID.size()) {
                            ticketIDs = ticketIDs + ticketID + ",";
                        }
                        else{
                            ticketIDs = ticketIDs + ticketID;
                        }
                        listSize++;
                    }

                    final Call<TicketResponse> request = service.getTicket(ticketIDs, login.getUserLogin(), login.getPassword());

                    request.enqueue(new Callback<TicketResponse>() {
                        @Override
                        public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                            if(!response.isSuccessful()){
                                Log.e(Constants.TAG,"ERRO: Network Connection Invalid " + response.code());
                            }
                            else {

                                ticketListProgress.setVisibility(View.GONE);

                                TicketResponse ticketResponse = response.body();
                                adapter.update(ticketResponse.getTicket(), mRecyclerView);
                            }
                        }
                        @Override
                        public void onFailure(Call<TicketResponse> call, Throwable t) {
                           Log.e(Constants.TAG,"ERRO: " + t.getMessage());
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<TicketSearchResponse> call, Throwable t) {
               Log.i(Constants.TAG, "ERRO: " + t.getMessage());
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {

    }
}