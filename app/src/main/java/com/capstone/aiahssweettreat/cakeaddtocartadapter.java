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

public class cakeaddtocartadapter extends FirestoreRecyclerAdapter<cakeaddtocartmodel, cakeaddtocartadapter.CakeViewHolder> {


    public cakeaddtocartadapter(@NonNull FirestoreRecyclerOptions<cakeaddtocartmodel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull CakeViewHolder holder, int position, @NonNull cakeaddtocartmodel model) {
        holder.cakeName.setText(model.getCakename());
        holder.cakePrice.setText(model.getCakeprice());
        String url =  model.getCakeimage();
        Picasso.get().load(url).into(holder.cakeImage);
        holder.cakeSize.setText("Cake Size: " + model.getCakesize());

        String decorations = model.getDecorations();
        if(decorations == null){
            decorations = "None";
        }
        holder.cakeDecoration.setText("Cake Decoration: " + decorations);

        String fillings = model.getFillings();
        if(fillings == null){
            fillings = "None";
        }
        holder.cakeFilling.setText("Cake Fillings: " + fillings);

        String frosting = model.getFrostings();
        if(frosting == null){
            frosting = "None";
        }
        holder.cakeFrosting.setText("Cake Frostings: " + frosting);

        String specialinstruction = model.getSpecialInstructions();
        if(specialinstruction == null){
            specialinstruction = "None";
        }
        holder.cakeInstruction.setText("Special Instruction: " + specialinstruction);
    }

    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cakeaddtocartcard, parent, false);
        return new CakeViewHolder(view);
    }

    static class CakeViewHolder extends RecyclerView.ViewHolder {
        TextView cakeName, cakePrice,cakeSize,cakeDecoration,cakeFilling,cakeFrosting,cakeInstruction;
        ImageView cakeImage;

        public CakeViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeName = itemView.findViewById(R.id.tvcakename);
            cakePrice = itemView.findViewById(R.id.tvcakeprice);
            cakeImage = itemView.findViewById(R.id.ivcakeimage);
            cakeSize = itemView.findViewById(R.id.tvcakesize);
            cakeDecoration = itemView.findViewById(R.id.tvdecoration);
            cakeFilling = itemView.findViewById(R.id.tvfilling);
            cakeFrosting = itemView.findViewById(R.id.tvfrosting);
            cakeInstruction = itemView.findViewById(R.id.tvinstruction);

        }
    }
}
