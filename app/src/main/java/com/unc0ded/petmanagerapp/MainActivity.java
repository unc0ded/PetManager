package com.unc0ded.petmanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference petReference = FirebaseDatabase.getInstance().getReference().child("pets");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachID();

        petReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Pet> petList = new ArrayList<>();
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    petList.add(shot.getValue(Pet.class));
                    Log.i("PET NAME",shot.getValue(Pet.class).getName());
                }

                recyclerView.setAdapter(new PetRVAdapter(petList,getApplicationContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void attachID() {
        recyclerView = findViewById(R.id.pets_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
