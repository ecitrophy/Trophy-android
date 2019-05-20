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

    ArrayList<BetCards> bets;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public AdapterCards(ArrayList<BetCards> bets) {
        this.bets = bets;
    }

    @NonNull
    @Override
    public ViewHolderCards onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_lobby, null, false);
        return new ViewHolderCards(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCards holder, int position) {
        holder.nameBet.setText(bets.get(position).getName());
        holder.nameGame.setText(bets.get(position).getGame());
        holder.amountBettors.setText(bets.get(position).getAmountBettors());
        holder.bet.setText(bets.get(position).getMinimumBet());
        holder.image.setImageResource(bets.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return bets.size();
    }

    public class ViewHolderCards extends RecyclerView.ViewHolder {

        TextView nameBet, nameGame, amountBettors, bet;
        ImageView image;

        public ViewHolderCards(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            nameBet = (TextView) itemView.findViewById(R.id.nickname_bettor);
            nameGame = (TextView) itemView.findViewById(R.id.minimum_bet);
            amountBettors = (TextView) itemView.findViewById(R.id.amountBettorsCard);
            bet = (TextView) itemView.findViewById(R.id.amountCard);
            image = (ImageView) itemView.findViewById(R.id.imageCard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(position);
                        }
                    }
                }
            });
        }
    }
}
