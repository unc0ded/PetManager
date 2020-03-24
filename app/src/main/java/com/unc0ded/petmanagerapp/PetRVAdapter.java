package com.unc0ded.petmanagerapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PetRVAdapter extends RecyclerView.Adapter<PetRVAdapter.PetVH> {

    private List<Pet> petList = new ArrayList<>();
    private Context context;

    public PetRVAdapter(List<Pet> petList, Context context) {
        this.petList = petList;
        this.context = context;
    }

    @NonNull
    @Override
    public PetVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PetVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PetVH holder, int position) {
        holder.populate(petList.get(position));
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    class PetVH extends RecyclerView.ViewHolder{

        TextView name,type,age;

        public PetVH(@NonNull View itemView) {
            super(itemView);
            attachID();
        }

        private void attachID(){
            age=itemView.findViewById(R.id.age_tv);
            type=itemView.findViewById(R.id.pet_type);
            name=itemView.findViewById(R.id.pet_name);
        }

        public void populate(Pet pet){
            name.setText(pet.getName());
            age.setText(""+pet.getAge());
            type.setText(pet.getType());
        }
    }
}


