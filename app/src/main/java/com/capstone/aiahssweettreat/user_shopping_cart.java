package com.capstone.aiahssweettreat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class user_shopping_cart extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private cakeaddtocartadapter adapter;
    private FirebaseAuth mAuth;

    private Button Checkout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shopping_cart);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        Checkout = findViewById(R.id.btncheckout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Query query = firebaseFirestore.collection("cart")
                    .whereEqualTo("useremail", currentUser.getEmail());
            FirestoreRecyclerOptions<cakeaddtocartmodel> options = new FirestoreRecyclerOptions.Builder<cakeaddtocartmodel>()
                    .setQuery(query, cakeaddtocartmodel.class)
                    .build();
            adapter = new cakeaddtocartadapter(options);
            recyclerView.setAdapter(adapter);

            Checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    processCheckout(currentUser.getEmail());
                }
            });
        } else {
            Intent intent = new Intent(user_shopping_cart.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    private void processCheckout(String userEmail) {
        // Iterate through each item in the RecyclerView and add order details to Firestore
        for (int i = 0; i < adapter.getItemCount(); i++) {
            cakeaddtocartmodel item = adapter.getItem(i);
            if (item != null) {
                String cakeName = item.getCakename();
                String cakeSize = item.getCakesize();
                String cakeDecoration = item.getDecorations();
                String cakeFillings = item.getFillings();
                String cakeFrostings = item.getFrostings();
                String specialInstruction = item.getSpecialInstructions();
                String cakePrice = item.getCakeprice();
                addOrderToFirestore(cakeName, userEmail,cakeSize,cakeDecoration,cakeFillings,cakeFrostings,specialInstruction,cakePrice);
            }
        }
    }


    private void addOrderToFirestore(String cakeName, String userEmail, String cakeSize,String cakeDecoration,String cakeFillings, String cakeFrostings,String specialInstruction,String cakePrice) {
        // Add order details to Firestore in the "order" collection
        cakeordermodel order = new cakeordermodel(cakeName, userEmail,cakeSize,cakeDecoration,cakeFillings,cakeFrostings,specialInstruction,cakePrice);
        firebaseFirestore.collection("orders")
                .add(order)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        deleteCartItems(userEmail);
                        Toast.makeText(user_shopping_cart.this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("user_shopping_cart", "Error adding order to Firestore: " + e.getMessage());
                    }
                });
    }

    private void deleteCartItems(String userEmail) {
        // Query cart items based on user email
        firebaseFirestore.collection("cart")
                .whereEqualTo("useremail", userEmail)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Iterate through each cart item and delete it
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            document.getReference().delete();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("user_shopping_cart", "Error deleting cart items: " + e.getMessage());
                    }
                });
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}