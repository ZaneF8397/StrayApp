package com.example.strayapp.UI;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.strayapp.Animal;
import com.example.strayapp.AnimalAdapter;
import com.example.strayapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String selected;
    RecyclerView recyclerView;
    ArrayList<Animal> animalArrayList;
    FirebaseFirestore db;

    AnimalAdapter animalAdapter;

    @Override
    public void onBackPressed(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        db = FirebaseFirestore.getInstance();
        animalArrayList = new ArrayList<Animal>();
        animalAdapter = new AnimalAdapter(MainActivity.this,animalArrayList);

        recyclerView.setAdapter(animalAdapter);

        EditText city = findViewById(R.id.cityName);
        TextView nothing = findViewById(R.id.noSearch);
        nothing.setVisibility(View.VISIBLE);
        Spinner spinner = findViewById(R.id.spinner);
        Button button = findViewById(R.id.reportpagebutton);

        nothing.setText(R.string.welcome);

        Button search = findViewById(R.id.search);
        ArrayList<String> spinnerContent = new ArrayList<>();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerContent);


        spinnerContent.add("Pet");
        spinnerContent.add("Threat");
        spinnerContent.add("Wild");
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);




        button.setOnClickListener(view -> openReportMenu());


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selected = "";
            }
        });


        search.setOnClickListener(view -> {

            animalArrayList.clear();
            String cityText = city.getText().toString().toUpperCase();
            if(selected.length() != 0 && cityText.length() != 0){
                db.collection(selected.toUpperCase())
                        .whereEqualTo("City", cityText)
                        .orderBy("Date", Query.Direction.DESCENDING)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        animalArrayList.add(document.toObject(Animal.class));
                                    }
                                    if(animalArrayList.size() != 0){
                                        nothing.setVisibility(View.INVISIBLE);
                                    }
                                    else{
                                        nothing.setText(R.string.naught);
                                        nothing.setVisibility(View.VISIBLE);
                                    }
                                    animalAdapter.notifyDataSetChanged();
                                }
                            }
                        });
            }
            else{
                Snackbar error = Snackbar.make(view, "Please fill all fields!", 2500);
                error.show();
            }
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            city.clearFocus();
            recyclerView.scrollToPosition(0);
        });

    }

    public void openReportMenu() {
        Intent intent = new Intent(this, ReportMenu.class);
        startActivity(intent);
    }

}