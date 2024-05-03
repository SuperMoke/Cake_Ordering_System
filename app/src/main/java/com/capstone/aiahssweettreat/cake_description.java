package com.capstone.aiahssweettreat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class cake_description extends AppCompatActivity {

    ImageView ivCakeImage;
    TextView tvCakeName, tvCakePrice, tvCakeDescription, tvCakeCalorieCount, tvCakeSugarCount;
    Button btnAddtoCart;
    private String cakeImageUrl;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_description);

        ivCakeImage = findViewById(R.id.ivcakeimage);
        tvCakeName = findViewById(R.id.tvcakename);
        tvCakePrice = findViewById(R.id.tvcakeprice);
        tvCakeDescription = findViewById(R.id.tvcakedescription);
        tvCakeCalorieCount = findViewById(R.id.tvcakecalorieaccount);
        tvCakeSugarCount = findViewById(R.id.tvcakesugarcount);
        btnAddtoCart = findViewById(R.id.btnaddtocart);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String cakename = getIntent().getStringExtra("cakeName");
        db.collection("cakes")
                .whereEqualTo("cakename", cakename)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            QueryDocumentSnapshot documentSnapshot = (QueryDocumentSnapshot) queryDocumentSnapshots.getDocuments().get(0);
                            cakemodel cake = documentSnapshot.toObject(cakemodel.class);
                            if (cake != null) {
                                cakeImageUrl = cake.getCakeimage();
                                Picasso.get().load(cakeImageUrl).into(ivCakeImage);
                                tvCakeName.setText(cake.getCakename());
                                tvCakePrice.setText("₱" + cake.getCakeprice());
                                tvCakeDescription.setText(cake.getCakedescription());
                                tvCakeCalorieCount.setText("Approximately " + cake.getCakecaloriecount() + " calories per slice.");
                                tvCakeSugarCount.setText("About " + cake.getCakesugarcount() + " grams of sugar per slice.");
                                getIntent().removeExtra("cakeName");
                            }
                        } else {
                            Log.d("user_cakedescription", "No such document");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("user_cakedescription", "Error getting documents.", e);
                    }
                });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null){
                    Intent intent = new Intent(cake_description.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        });
    }
}
