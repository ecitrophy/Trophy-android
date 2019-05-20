package com.edu.eci.ieti.trophy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBettors extends RecyclerView.Adapter<AdapterBettors.ViewHolderBettors> {

    ArrayList<Bettor> bettors;

    public AdapterBettors(ArrayList<Bettor> bettors) {
        this.bettors = bettors;
    }

    @NonNull
    @Override
    public ViewHolderBettors onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_bettors, null, false);
        return new ViewHolderBettors(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBettors holder, int position) {
        holder.name.setText(bettors.get(position).getName());
        holder.description.setText(bettors.get(position).getInfo());
        holder.image.setImageResource(bettors.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return bettors.size();
    }

    public class ViewHolderBettors extends RecyclerView.ViewHolder {

        TextView name, description;
        ImageView image;

        public ViewHolderBettors(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.idNombre);
            description = (TextView) itemView.findViewById(R.id.idInfo);
            image = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
