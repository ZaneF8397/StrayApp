package com.example.strayapp.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.strayapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AnimalReport extends AppCompatActivity {
    Intent nextIntent;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, ReportMenu.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_report);

        EditText color = findViewById(R.id.colortype);
        EditText desc = findViewById(R.id.descriptiontype);
        EditText type = findViewById(R.id.animaltype);
        TextView title = findViewById(R.id.reportTitle);
        Button homebutton = findViewById(R.id.homebutton);
        Button next = findViewById(R.id.nextbutton);
        Switch image = findViewById(R.id.switch1);


        Intent intent = getIntent();
        String whatIsThis = intent.getStringExtra("what");
        if(whatIsThis.equals("Pet")){title.setText(R.string.petTitle);}
        else if(whatIsThis.equals("Threat")){title.setText(R.string.threatTitle);}
        else{title.setText(R.string.wildTitle);}




        homebutton.setOnClickListener(view -> openMainActivity());


        next.setOnClickListener(view -> {

            nextIntent = new Intent(this, AnimalReportNext.class);

            String colortext = color.getText().toString();
            String desctext = desc.getText().toString();
            String typetext = type.getText().toString();


            if(colortext.length() == 0 || desctext.length() == 0 || typetext.length() == 0){
                Snackbar error = Snackbar.make(view, "Please fill all fields!", 2500);
                error.show();
            }
            else{
                nextIntent.putExtra("color", colortext);
                nextIntent.putExtra("description", desctext);
                nextIntent.putExtra("type", typetext);
                nextIntent.putExtra("what", whatIsThis);
                if(image.isChecked()){
                    Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(openGallery, 3);
                }
                else{
                    startActivity(nextIntent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri image = data.getData();
            nextIntent.putExtra("URI", image.toString());
            startActivity(nextIntent);
        }
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}