package com.edu.eci.ieti.trophy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BetDetails extends AppCompatActivity {

    ArrayList<Bettor> bettors;
    RecyclerView recycler;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_details);

        bettors = new ArrayList<>();
        recycler = findViewById(R.id.recycler_details_bet);
        recycler.setLayoutManager(new LinearLayoutManager(this));
//        fillBettors();
        try {
            fillBettors();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AdapterBettors adapter = new AdapterBettors(bettors);
        recycler.setAdapter(adapter);
    }

    private void fillBettors() throws IOException, InterruptedException {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String response = null;
                try {
                    response = HttpConnection.getUrlData("https://gentle-wave-71675.herokuapp.com/apimatch/matcheslist");
                    System.out.println("response: " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
                Intent intent = getIntent();
                System.out.println("intent: " + intent.getStringExtra("pos"));
                JsonObject match = ((JsonObject) jsonParser.parse(jsonArray.get(Integer.parseInt(intent.getStringExtra("pos"))).toString()));
                JsonArray active_bettors = (JsonArray) match.get("bettors");
                for (int i = 0; i < active_bettors.size(); i++) {
//                    JsonObject match = ((JsonObject) jsonParser.parse(active_bettors.get(i).toString()));
                    JsonObject bettor = (JsonObject) jsonParser.parse(active_bettors.get(i).toString());
                    JsonObject bets = (JsonObject) bettor.get("bets");
                    JsonObject defaultJ = (JsonObject) bets.get("default");
                    System.out.println("default1: " + bettor.get("userName").getAsString());
                    System.out.println("default2: " + defaultJ.get("bet").getAsString());
                    System.out.println("default3: " + defaultJ.get("player").getAsString());
                    bettors.add(new Bettor(bettor.get("userName").getAsString(), defaultJ.get("bet").getAsString(), defaultJ.get("player").getAsString(), R.drawable.profile_image));
                }
                TextView title = findViewById(R.id.bet_name_title);
                title.setText(match.get("name").getAsString());
                title = findViewById(R.id.bet_game_title);
                title.setText(match.get("game").getAsString());
                JsonObject creator = (JsonObject) match.get("creator");
                title = findViewById(R.id.nickname_bettor);
                title.setText(creator.get("userName").getAsString());
                title = findViewById(R.id.minimum_bet);
                title.setText("Minimum Bet: " + match.get("minimumBet").getAsString());
                title = findViewById(R.id.id_match);
                title.setText(match.get("id").getAsString());
                JsonObject bets = (JsonObject) creator.get("bets");
                JsonObject defaultJ = (JsonObject) bets.get("default");
                title = findViewById(R.id.bet_player);
                title.setText(defaultJ.get("player").getAsString());
                title = findViewById(R.id.bet);
                title.setText("Bet:" + defaultJ.get("bet").getAsString());


            }
        });
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

//    private void fillBettors() {
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//        bettors.add(new Bettor("Juan", "Apostador", R.drawable.profile_image));
//    }
}
