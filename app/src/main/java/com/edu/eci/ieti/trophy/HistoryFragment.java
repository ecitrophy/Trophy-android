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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HistoryFragment extends Fragment {

    ArrayList<BetCards> bets;
    RecyclerView recycler;
    //    View view;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_layout, container, false);
        bets = new ArrayList<>();
//        fillBets();
        try {
            fillBets();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recycler = view.findViewById(R.id.recycler_content_h);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterCards adapter = new AdapterCards(bets);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterCards.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
//                Intent intent = new Intent(getActivity(), BetDetails.class);
//                intent.putExtra("pos", String.valueOf(position));
//                startActivity(intent);
            }
        });
        return view;
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
                    if (match.get("state").getAsString().equals("CLOSE")) {
                        bets.add(new BetCards(match.get("name").getAsString(), match.get("game").getAsString(), bettors, match.get("minimumBet").getAsString(), R.drawable.league_of_legends_1024x576));
                    }
                }
            }
        });
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("History");
    }
}
