package com.example.strayapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.strayapp.R;

public class ReportMenu extends AppCompatActivity {

    String selected;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportmenu);

        Button homebutton = findViewById(R.id.homebutton);
        homebutton.setOnClickListener(view -> openMainActivity());

        Button about = findViewById(R.id.aboutButton);
        about.setOnClickListener(view -> {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        });

        Button petbutton = findViewById(R.id.petbutton);
        petbutton.setOnClickListener(e -> {
            selected = "Pet";
            Intent intent = new Intent(this, AnimalReport.class);
            intent.putExtra("what", selected);
            startActivity(intent);
        });
        Button threatbutton = findViewById(R.id.threatbuttonGOAT);
        threatbutton.setOnClickListener(e ->{
            selected = "Threat";
            Intent intent = new Intent(this, AnimalReport.class);
            intent.putExtra("what", selected);
            startActivity(intent);
        });
        Button wildbutton = findViewById(R.id.wildbutton);
        wildbutton.setOnClickListener(e ->{
            selected = "Wild";
            Intent intent = new Intent(this, AnimalReport.class);
            intent.putExtra("what", selected);
            startActivity(intent);
        });

    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_reportmenu);
    }

}