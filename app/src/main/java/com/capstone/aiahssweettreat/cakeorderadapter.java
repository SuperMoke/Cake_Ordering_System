package com.capstone.aiahssweettreat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class cakeorderadapter extends FirestoreRecyclerAdapter<cakeordermodel, cakeorderadapter.CakeViewHolder> {


    public cakeorderadapter(@NonNull FirestoreRecyclerOptions<cakeordermodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CakeViewHolder holder, int position, @NonNull cakeordermodel model) {
        holder.useremail.setText("Customer Name: "+ model.getUseremail());
        holder.cakeName.setText("Cake Name: "+model.getCakename());
        holder.cakeSize.setText("Cake Size: " + model.getSize());
        holder.cakeDecoration.setText("Cake Decoration: " + model.getDecoration());
        holder.cakeFilling.setText("Cake Fillings: " + model.getFillings());
        holder.cakeFrosting.setText("Cake Frostings: " + model.getFrosting());
        holder.specialInstruction.setText("Special Instruction: " + model.getSpecialInstruction());
        holder.cakePrice.setText("Cake Decoration: " + model.getPrice());
    }

    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cakeordercards, parent, false);
        return new CakeViewHolder(view);
    }

    static class CakeViewHolder extends RecyclerView.ViewHolder {
        TextView useremail,cakeName,cakeSize,cakeDecoration,cakeFrosting,cakeFilling,specialInstruction,cakePrice;


        public CakeViewHolder(@NonNull View itemView) {
            super(itemView);
            useremail = itemView.findViewById(R.id.tvusername);
            cakeName = itemView.findViewById(R.id.tvcakename);
            cakeSize = itemView.findViewById(R.id.tvcakesize);
            cakeDecoration = itemView.findViewById(R.id.tvcakedecoration);
            cakeFrosting = itemView.findViewById(R.id.tvcakefrosting);
            cakeFilling = itemView.findViewById(R.id.tvcakefilling);
            specialInstruction = itemView.findViewById(R.id.tvspecialinstruction);
            cakePrice = itemView.findViewById(R.id.tvcakeprice);
        }
    }
}
