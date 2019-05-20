package com.edu.eci.ieti.trophy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LobbyFragment extends Fragment {

    ArrayList<BetCards> bets;
    RecyclerView recycler;
//    View view;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lobby_layout, container, false);
        bets = new ArrayList<>();
//        fillBets();
        try {
            fillBets();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recycler = view.findViewById(R.id.recycler_content);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterCards adapter = new AdapterCards(bets);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterCards.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), BetDetails.class);
                intent.putExtra("pos", String.valueOf(position));
                startActivity(intent);
            }
        });
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });
//        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View viewc) {
//                Intent intent = new Intent(LobbyActivity.class, CreateMatch.class);
//                startActivity(intent);
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });
        return view;
    }

    private void startNewActivity() {
        Intent intent = new Intent(getActivity(), CreateMatch.class);
        startActivity(intent);
    }

    private void fillBets() throws IOException, InterruptedException {
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
                System.out.println("size: " + jsonArray.size());
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject match = ((JsonObject) jsonParser.parse(jsonArray.get(i).toString()));
                    String bettors = String.valueOf(match.get("bettors").getAsJsonArray().size());
                    System.out.println("if: " + match.get("state").getAsString().equals("OPEN") + match.get("state").getAsString());
                    if ((match.get("state").getAsString().equals("OPEN")) || (match.get("state").getAsString().equals("INGAME"))) {
                        bets.add(new BetCards(match.get("name").getAsString(), match.get("game").getAsString(), bettors, match.get("minimumBet").getAsString(), R.drawable.league_of_legends_1024x576));
                    }
                }
            }
        });
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

//    private void fillBets() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://gentle-wave-71675.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
//        Call<List<MatchList>> call = jsonPlaceHolderAPI.getMatches();
//        call.enqueue(new Callback<List<MatchList>>() {
//            @Override
//            public void onResponse(Call<List<MatchList>> call, Response<List<MatchList>> response) {
//                List<MatchList> matchesList = response.body();
//                System.out.println("hola " + matchesList.get(0).getName());
//                for (int i = 0; i < matchesList.size(); i++) {
//                    bets.add(new BetCards(matchesList.get(i).getName(), matchesList.get(i).getGame(), "1/10", matchesList.get(i).getMinimumBet(), R.drawable.league_of_legends_1024x576));
//                }
////                recycler = view.findViewById(R.id.recycler_content);
////                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
////                AdapterCards adapter = new AdapterCards(bets);
////                recycler.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<MatchList>> call, Throwable t) {
//                System.out.println("Error: " + t.getMessage());
//            }
//        });
//    }

//    private void fillBets() {
//        bets.add(new BetCards("Tournament", "League of Legends", "10/20", "$500", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament1", "League of Legends", "11/20", "$5000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament2", "League of Legends", "12/20", "$50000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament3", "League of Legends", "13/20", "$500000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament4", "League of Legends", "14/20", "$5000000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament5", "League of Legends", "15/20", "$50000000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament6", "League of Legends", "16/20", "$500000000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament7", "League of Legends", "17/20", "$5000000000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament8", "League of Legends", "18/20", "$50000000000", R.drawable.league_of_legends_1024x576));
//        bets.add(new BetCards("Tournament9", "League of Legends", "19/20", "$500000000000", R.drawable.league_of_legends_1024x576));
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Lobby");
    }

}
