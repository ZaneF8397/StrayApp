package com.example.strayapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.strayapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AnimalReportNext extends AppCompatActivity {
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, AnimalReport.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_report_next);

        Intent prior = getIntent();
        TextView title = findViewById(R.id.reportTitle);
        String whatIsThis = prior.getStringExtra("what");
        if(whatIsThis.equals("Pet")){title.setText(R.string.petTitle);}
        else if(whatIsThis.equals("Threat")){title.setText(R.string.threatTitle);}
        else{title.setText(R.string.wildTitle);}




        EditText street = findViewById(R.id.streetBox);
        EditText city = findViewById(R.id.cityBox);
        EditText contact = findViewById(R.id.contactBox);
        Button submit = findViewById(R.id.submitbutton);
        Button home = findViewById(R.id.homebutton);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        home.setOnClickListener(e -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


        submit.setOnClickListener(view -> {
            String streettext = street.getText().toString();
            String citytext = city.getText().toString();
            String contacttext = contact.getText().toString();
            if(streettext.length() != 0 && citytext.length() != 0 && contacttext.length() != 0) {
                Map<String, Object> animal = new HashMap<>();
                if (prior.getStringExtra("URI") != null) {
                    Uri image = Uri.parse(prior.getStringExtra("URI"));
                    Bitmap bitmap;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                        bitmap = Bitmap.createScaledBitmap(bitmap,150,150,false);
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                        System.out.println("Get byte count: " + bitmap.getByteCount());
                        byte[] arr = outputStream.toByteArray();
                        System.out.println("Array length: " + arr.length);
                        String encodedString = Base64.encodeToString(arr, Base64.DEFAULT);
                        System.out.println("Encoded string length: " + encodedString.length());
                        animal.put("Encoded", encodedString);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                Date current = Calendar.getInstance().getTime();
                animal.put("Date", current.toString());
                animal.put("Description", prior.getStringExtra("description"));
                animal.put("City", citytext.toUpperCase());
                animal.put("Name", prior.getStringExtra("color") + " " + prior.getStringExtra("type"));
                animal.put("Street", streettext);
                animal.put("Contact", contacttext);
                db.collection(whatIsThis.toUpperCase()).add(animal);
            }
            else{
                Snackbar error = Snackbar.make(view, "Please fill all fields!", 2500);
                error.show();
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}