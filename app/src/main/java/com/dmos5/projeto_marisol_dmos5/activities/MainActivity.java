package com.dmos5.projeto_marisol_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dmos5.projeto_marisol_dmos5.R;
import com.dmos5.projeto_marisol_dmos5.api.RetrofitService;
import com.dmos5.projeto_marisol_dmos5.constants.Constants;
import com.dmos5.projeto_marisol_dmos5.model.Login;
import com.dmos5.projeto_marisol_dmos5.model.Ticket;
import com.dmos5.projeto_marisol_dmos5.response.TicketResponse;
import com.dmos5.projeto_marisol_dmos5.response.TicketSearchResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Ticket> mTicketList;
    ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TicketAdapter adapter;
    private ProgressBar ticketListProgress;

    private FloatingActionButton btnTicketAdd;
    List<String> listTicketsID;

    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Log.i("RESPOSTAAAA", "ENTROU NO LIST");
        login = new Login(Constants.USERNAME, Constants.PASSWORD);

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
                Log.i("RESPOSTAAAA","ERRO: Network Connection Invalid " + response.code());
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
                           // Log.e(TAG,"ERRO: Network Connection Invalid " + response.code());
                        }
                        else {

                            ticketListProgress.setVisibility(View.GONE);

                            TicketResponse ticketResponse = response.body();
                            adapter.update(ticketResponse.getTicket(), mRecyclerView);
                        }
                    }

                    @Override
                    public void onFailure(Call<TicketResponse> call, Throwable t) {
                      //  Log.e(TAG,"ERRO: " + t.getMessage());
                    }
                });

            }
        }

        @Override
        public void onFailure(Call<TicketSearchResponse> call, Throwable t) {
           Log.i("ERROR","ERRO: " + t.getMessage());
        }
    });


    }

    private void ticketSearch() throws JSONException {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.URL).addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("UserLogin", Constants.USERNAME);
        jsonParams.put("Password", Constants.PASSWORD);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());


        Call<List<String>> ticketIDs = service.ticketSearch(body);

        ticketIDs.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    Log.i("RESPOSTA", response.body().toString());
                    //List<Repository> repos = response.body();
                   // adapter.update(repos, mImageView, mRecyclerView);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //  Toast.makeText(MainActivity.this, R.string.request_falied, Toast.LENGTH_SHORT).show();

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