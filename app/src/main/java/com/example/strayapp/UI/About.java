package com.example.strayapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;



import com.example.strayapp.R;

public class About extends AppCompatActivity {
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button home = findViewById(R.id.homebutton);
        home.setOnClickListener(view -> openMainActivity());

        TextView link = findViewById(R.id.linkbox);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}