package com.edu.eci.ieti.trophy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void lobby(View view) {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
        MainActivity.main.finish();
        finish();
    }
}
