package com.dmos5.projeto_marisol_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.dmos5.projeto_marisol_dmos5.R;
import com.dmos5.projeto_marisol_dmos5.api.RetrofitService;
import com.dmos5.projeto_marisol_dmos5.constants.Constants;
import com.dmos5.projeto_marisol_dmos5.model.Login;
import com.dmos5.projeto_marisol_dmos5.response.TicketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketDetailsActivity extends AppCompatActivity {

    private TextView valueTicketCreated, valueTicketNumber, valueTicketState, valueTicketQueue, valueTicketPriority, valueTicketCustomer;
    private ProgressBar ticketInfoProgress;
    private LinearLayout lnLayout;

    private String ticketID, ticketNumber;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();

        if (extrasBundle != null) {
            ticketID = extrasBundle.getString("TicketID");
            ticketNumber = extrasBundle.getString("TicketNumber");
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Ticket#"+ticketNumber);
        }


        Log.i("RESPOSTAB",  ticketID
        );

        valueTicketCreated    = findViewById(R.id.valueTicketCreated);
        valueTicketNumber     = findViewById(R.id.valueTicketNumber);
        valueTicketState      = findViewById(R.id.valueTicketState);
        valueTicketQueue      = findViewById(R.id.valueTicketQueue);
        valueTicketPriority   = findViewById(R.id.valueTicketPriority);
        valueTicketCustomer   = findViewById(R.id.valueTicketCustomer);

        ticketInfoProgress = findViewById(R.id.ticketInfoProgress);
        lnLayout = findViewById(R.id.linLayoutInfo);

       // if(NetworkUtils.isNetworkConnected(TicketInfoActivity.this))
        getTicketInfo();

    }

    private void getTicketInfo() {

        login = new Login(Constants.USERNAME, Constants.PASSWORD);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        final Call<TicketResponse> request = service.getTicket(ticketID, login.getUserLogin(), login.getPassword());

        request.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                ticketInfoProgress.setVisibility(View.GONE);
                lnLayout.setVisibility(View.VISIBLE);

                if(!response.isSuccessful()){
                    // Log.e(TAG,"ERRO: Network Connection Invalid " + response.code());
                }else{
                    TicketResponse ticketResponse = response.body();
                    Log.i("RESPOSTAB", ticketResponse.toString());

                    valueTicketCreated.setText(ticketResponse.getTicket().get(0).getCreated());
                    valueTicketNumber.setText(ticketResponse.getTicket().get(0).getTicketNumber());
                    valueTicketState.setText(ticketResponse.getTicket().get(0).getState());
                    valueTicketPriority.setText(ticketResponse.getTicket().get(0).getPriority());
                    valueTicketQueue.setText(ticketResponse.getTicket().get(0).getQueue());
                    valueTicketCustomer.setText(String.valueOf(ticketResponse.getTicket().get(0).getCustomerID()));
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Log.i("RESPOSTAB", t.getMessage());
                //  Log.e(TAG,"ERRO: " + t.getMessage());
            }
        });

    }

}