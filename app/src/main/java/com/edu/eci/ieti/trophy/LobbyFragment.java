package com.edu.eci.ieti.trophy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LobbyFragment extends Fragment {

    ArrayList<betCards> bets;
    RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lobby_layout, container, false);
        bets = new ArrayList<>();
        recycler = view.findViewById(R.id.recycler_content);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        fillBets();
        AdapterCards adapter = new AdapterCards(bets);
        recycler.setAdapter(adapter);
        return view;
    }

    private void fillBets() {
        bets.add(new betCards("Tournament", "League of Legends", "10/20", "$500", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament1", "League of Legends", "11/20", "$5000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament2", "League of Legends", "12/20", "$50000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament3", "League of Legends", "13/20", "$500000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament4", "League of Legends", "14/20", "$5000000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament5", "League of Legends", "15/20", "$50000000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament6", "League of Legends", "16/20", "$500000000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament7", "League of Legends", "17/20", "$5000000000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament8", "League of Legends", "18/20", "$50000000000", R.drawable.league_of_legends_1024x576));
        bets.add(new betCards("Tournament9", "League of Legends", "19/20", "$500000000000", R.drawable.league_of_legends_1024x576));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Lobby");
    }

}
