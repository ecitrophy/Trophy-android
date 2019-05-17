package com.edu.eci.ieti.trophy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCards extends RecyclerView.Adapter<AdapterCards.ViewHolderCards> {

    ArrayList<betCards> bets;

    public AdapterCards(ArrayList<betCards> bets) {
        this.bets = bets;
    }

    @NonNull
    @Override
    public ViewHolderCards onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_lobby, null, false);
        return new ViewHolderCards(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCards holder, int position) {
        holder.nameBet.setText(bets.get(position).getNameBet());
        holder.nameGame.setText(bets.get(position).getNameGame());
        holder.amountBettors.setText(bets.get(position).getAmountBettors());
        holder.bet.setText(bets.get(position).getBet());
        holder.image.setImageResource(bets.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return bets.size();
    }

    public class ViewHolderCards extends RecyclerView.ViewHolder {

        TextView nameBet, nameGame, amountBettors, bet;
        ImageView image;

        public ViewHolderCards(@NonNull View itemView) {
            super(itemView);
            nameBet = (TextView) itemView.findViewById(R.id.nameBetCard);
            nameGame = (TextView) itemView.findViewById(R.id.nameGameCard);
            amountBettors = (TextView) itemView.findViewById(R.id.amountBettorsCard);
            bet = (TextView) itemView.findViewById(R.id.amountCard);
            image = (ImageView) itemView.findViewById(R.id.imageCard);
        }
    }
}
