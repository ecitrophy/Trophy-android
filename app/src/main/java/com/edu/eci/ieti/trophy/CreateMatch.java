package com.edu.eci.ieti.trophy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);

        Spinner spinnerGames = findViewById(R.id.spinner_games);
        ArrayAdapter<String> adapterSpinnerGames = new ArrayAdapter<>(CreateMatch.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nameGames));
        adapterSpinnerGames.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGames.setAdapter(adapterSpinnerGames);
    }

    public void lobbyUpdated(View view) {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
        finish();
    }
}
