package com.dmos5.projeto_marisol_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dmos5.projeto_marisol_dmos5.R;
import com.dmos5.projeto_marisol_dmos5.api.RetrofitService;
import com.dmos5.projeto_marisol_dmos5.constants.Constants;
import com.dmos5.projeto_marisol_dmos5.model.Login;
import com.dmos5.projeto_marisol_dmos5.response.SessionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText inputUser, inputPassword;
    private Button btnLogin;

    private SharedPreferences sharedpreferences;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        sharedpreferences = getSharedPreferences("PREFERENCE_USER", Context.MODE_PRIVATE);

        btnLogin      = findViewById(R.id.btn_login);
        inputUser     = findViewById(R.id.edt_username);
        inputPassword = findViewById(R.id.edt_passwd);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){

            if( !inputUser.getText().toString().equals("") && !inputPassword.getText().toString().equals("") ){
                verifyAuth(inputUser.getText().toString(), inputPassword.getText().toString() );
            }
            else {
                Toast.makeText(this, getString(R.string.fields_validate), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void verifyAuth (final String username, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        login = new Login(username, password);

        final Call<SessionResponse> requestSession = service.createSession(login.getUserLogin(), login.getPassword());

        requestSession.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                if (!response.isSuccessful()) {
                    Log.e(Constants.TAG,"ERRO: Network Connection Invalid " + response.code());
                }
                else {
                    SessionResponse sessionResponse = response.body();

                    if(sessionResponse != null){
                        isSession(sessionResponse.getSessionID(), login);
                    }
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {
                Log.e(Constants.TAG,"ERRO: " + t.getMessage());
            }
        });
    }

    private void isSession(String sessionID, Login login) {

        if (sessionID != null) {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Constants.SHARED_USERNAME, login.getUserLogin());
            editor.putString(Constants.SHARED_PASSWORD, login.getPassword());
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("SessionID", sessionID);

            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.fail_auth), Toast.LENGTH_LONG).show();
        }
    }
}