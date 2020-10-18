package com.dmos5.projeto_marisol_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dmos5.projeto_marisol_dmos5.R;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText inputUser, inputPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        btnLogin      = findViewById(R.id.btn_login);
        inputUser     = findViewById(R.id.edt_username);
        inputPassword = findViewById(R.id.edt_passwd);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            // chama a listagem de tickets
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Username", inputUser.getText());
            intent.putExtra("Password",  inputPassword.getText());
            startActivity(intent);
        }
    }
}