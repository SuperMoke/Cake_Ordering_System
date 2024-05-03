package com.capstone.aiahssweettreat;

import android.content.Context;
import android.content.Intent;
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

public class cakeadapter extends FirestoreRecyclerAdapter<cakemodel, cakeadapter.CakeViewHolder> {

    private Context mContext;


    public cakeadapter(@NonNull FirestoreRecyclerOptions<cakemodel> options,Context context) {
        super(options);
        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CakeViewHolder holder, int position, @NonNull cakemodel model) {
        holder.cakeName.setText(model.getCakename());
        holder.cakePrice.setText("₱ " + model.getCakeprice());
        String url =  model.getCakeimage();
        Picasso.get().load(url).into(holder.cakeImage);

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cakeName = model.getCakename();
                Intent intent = new Intent(mContext, user_cakedescription.class);
                intent.putExtra("cakeName", cakeName);
                mContext.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cakecards, parent, false);
        return new CakeViewHolder(view);
    }

    static class CakeViewHolder extends RecyclerView.ViewHolder {
        TextView cakeName, cakePrice;
        ImageView cakeImage;
        Button btnDetails;

        public CakeViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeName = itemView.findViewById(R.id.tvcakename);
            cakePrice = itemView.findViewById(R.id.tvcakeprice);
            cakeImage = itemView.findViewById(R.id.ivcakeimage);
            btnDetails = itemView.findViewById(R.id.btnmoredetails);
        }
    }
}
