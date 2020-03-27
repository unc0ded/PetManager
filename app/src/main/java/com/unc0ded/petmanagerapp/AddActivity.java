package com.unc0ded.petmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    Button add;
    EditText name,age,type;
    int arraySize;
    DatabaseReference petReference= FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("pets");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        attachID();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPet(name.getText().toString(),Integer.parseInt(age.getText().toString()),type.getText().toString());
            }
        });
    }

    private void addPet(String name, int age, String type) {
        Pet pet= new Pet(name,type,age);
        petReference.child("pet"+arraySize).setValue(pet);
        finish();
    }

    private void attachID() {
        add=findViewById(R.id.add_btn);
        name=findViewById(R.id.new_pet_name);
        age=findViewById(R.id.new_pet_age);
        type=findViewById(R.id.new_pet_type);
        arraySize=getIntent().getIntExtra("size",1);
    }
}
