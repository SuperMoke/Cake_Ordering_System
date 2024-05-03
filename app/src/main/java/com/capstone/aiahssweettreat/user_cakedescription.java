package com.capstone.aiahssweettreat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class user_cakedescription extends AppCompatActivity {

    ImageView cakeImage;
    TextView cakeName,cakePrice,cakeDescription,cakeCalorie,cakeSugar,specialInstruction;
    RadioGroup selectedsize;
    CheckBox frosting1,frosting2,frosting3,decoration1,decoration2,decoration3,filling1,filling2,filling3;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    private String cakeImageUrl , selectedSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cakedescription);

        // Initialize Views
        cakeImage = findViewById(R.id.ivcakeimage);
        cakeName = findViewById(R.id.tvcakename);
        cakePrice = findViewById(R.id.tvcakeprice);
        cakeDescription = findViewById(R.id.tvcakedescription);
        cakeCalorie = findViewById(R.id.tvcakecalorieaccount);
        cakeSugar = findViewById(R.id.tvcakesugarcount);
        selectedsize = findViewById(R.id.radioGroupSize);
        frosting1 = findViewById(R.id.frosting1);
        frosting2 = findViewById(R.id.frosting2);
        frosting3 = findViewById(R.id.frosting3);
        decoration1 = findViewById(R.id.decoration1);
        decoration2 = findViewById(R.id.decoration2);
        decoration3 = findViewById(R.id.decoration3);
        filling1 = findViewById(R.id.filling1);
        filling2 = findViewById(R.id.filling2);
        filling3 = findViewById(R.id.filling3);
        specialInstruction = findViewById(R.id.special_instruction);
        Button addToCartButton = findViewById(R.id.btn_add_to_cart);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        selectedsize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedSize = selectedRadioButton.getText().toString();
            }
        });

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
                                Picasso.get().load(cake.getCakeimage()).into(cakeImage);
                                cakeName.setText(cake.getCakename());
                                cakePrice.setText("₱" + cake.getCakeprice());
                                cakeDescription.setText(cake.getCakedescription());
                                cakeCalorie.setText(cake.getCakecaloriecount() + " calories");
                                cakeSugar.setText(cake.getCakesugarcount() + " grams of sugar");

                                // Set frosting to checkboxes
                                frosting1.setText(cake.getFrosting1());
                                frosting2.setText(cake.getFrosting2());
                                frosting3.setText(cake.getFrosting3());

                                // Set decorations to checkboxes
                                decoration1.setText(cake.getDecoration1());
                                decoration2.setText(cake.getDecoration2());
                                decoration3.setText(cake.getDecoration3());

                                // Set fillings to checkboxes
                                filling1.setText(cake.getFilling1());
                                filling2.setText(cake.getFilling2());
                                filling3.setText(cake.getFilling3());

                                cakeImageUrl = cake.getCakeimage();

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

        addToCartButton.setEnabled(false); // Disable the button initially

        // Add to cart button click listener
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cakeSize = selectedSize;
                StringBuilder selectedFrostings = new StringBuilder();
                StringBuilder selectedDecorations = new StringBuilder();
                StringBuilder selectedFillings = new StringBuilder();

                if (frosting1.isChecked()) {
                    selectedFrostings.append(frosting1.getText().toString()).append(", ");
                }
                if (frosting2.isChecked()) {
                    selectedFrostings.append(frosting2.getText().toString()).append(", ");
                }
                if (frosting3.isChecked()) {
                    selectedFrostings.append(frosting3.getText().toString()).append(", ");
                }

                if (decoration1.isChecked()) {
                    selectedDecorations.append(decoration1.getText().toString()).append(", ");
                }
                if (decoration2.isChecked()) {
                    selectedDecorations.append(decoration2.getText().toString()).append(", ");
                }
                if (decoration3.isChecked()) {
                    selectedDecorations.append(decoration3.getText().toString()).append(", ");
                }

                if (filling1.isChecked()) {
                    selectedFillings.append(filling1.getText().toString()).append(", ");
                }
                if (filling2.isChecked()) {
                    selectedFillings.append(filling2.getText().toString()).append(", ");
                }
                if (filling3.isChecked()) {
                    selectedFillings.append(filling3.getText().toString()).append(", ");
                }

                if (selectedFrostings.length() > 0) {
                    selectedFrostings.delete(selectedFrostings.length() - 2, selectedFrostings.length());
                }
                if (selectedDecorations.length() > 0) {
                    selectedDecorations.delete(selectedDecorations.length() - 2, selectedDecorations.length());
                }
                if (selectedFillings.length() > 0) {
                    selectedFillings.delete(selectedFillings.length() - 2, selectedFillings.length());
                }

                String frostingString = selectedFrostings.toString();
                String decorationString = selectedDecorations.toString();
                String fillingString = selectedFillings.toString();

                if (!cakeSize.isEmpty()) {
                    // Get user email
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        String userEmail = user.getEmail();
                        String selectedCakeName = cakeName.getText().toString();
                        String selectedCakePrice = cakePrice.getText().toString();
                        String specialInstructions = specialInstruction.getText().toString();
                        cakeaddtocartmodel cakeToAdd = new cakeaddtocartmodel(selectedCakeName, cakeImageUrl, selectedCakePrice, userEmail, cakeSize, frostingString,decorationString,fillingString, specialInstructions);

                        db.collection("cart")
                                .add(cakeToAdd)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("user_cakedescription", "Cake added to cart with ID: " + documentReference.getId());
                                        Toast.makeText(user_cakedescription.this, "Cake Added to Cart", Toast.LENGTH_SHORT).show();
                                        Intent intent;
                                        intent = new Intent(user_cakedescription.this, user_homepage.class);
                                        startActivity(intent);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("user_cakedescription", "Error adding cake to cart", e);
                                        Toast.makeText(user_cakedescription.this, "Failed to add cake to cart", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Intent intent;
                        intent = new Intent(user_cakedescription.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(user_cakedescription.this, "Please select a cake size", Toast.LENGTH_SHORT).show();
                }
            }
        });

        selectedsize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                addToCartButton.setEnabled(true); // Enable the button when a size is selected
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedSize = selectedRadioButton.getText().toString();
            }
        });

    }
}
