package com.example.strayapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strayapp.UI.MainActivity;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder>{

    Context context;
    ArrayList<Animal> animalArrayList;


    public AnimalAdapter(Context context, ArrayList<Animal> animalArrayList) {
        this.context = context;
        this.animalArrayList = animalArrayList;
    }

    @NonNull
    @Override
    public AnimalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Animal animal = animalArrayList.get(position);
        holder.name.setText(animal.Name);
        holder.street.setText(animal.Street);
        holder.date.setText(animal.Date);
        holder.description.setText(animal.Description);
        holder.contact.setText(animal.Contact);
        System.out.println("Encoded: " + animal.Encoded);
        if(animal.Encoded != null) {
            byte[] decodedString = Base64.decode(animal.Encoded, Base64.DEFAULT);
            System.out.println(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length).getWidth() + " <- width height -> " + BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length).getHeight());
            holder.picture.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
        }
        else{
            holder.picture.setImageResource(R.drawable.imagenone);
        }
    }

    @Override
    public int getItemCount(){
        return animalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,street,date,description,contact;
        ImageView picture;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            name = itemView.findViewById(R.id.NAMECHANGE);
            street = itemView.findViewById(R.id.STREETCHANGE);
            date = itemView.findViewById(R.id.DATECHANGE);
            description = itemView.findViewById(R.id.DESCRIPTIONCHANGE);
            contact = itemView.findViewById(R.id.CONTACTCHANGE);

        }
    }
}
