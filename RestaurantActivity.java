package com.example.bytev2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestaurantActivity extends AppCompatActivity {
    Button btnNext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_restaurant);
        getRestaurant();
        btnNext = (Button) findViewById(R.id.btnConfirm);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public void getRestaurant() {
        int i = 4;
        String[] arrRestaurants = new String[i];
        arrRestaurants[0] = "Select here";
        for (int j = 1; j < i; j++) {
            arrRestaurants[j] = "Loop";
        }
        Spinner restaurant = (Spinner) findViewById(R.id.spnrRestaurants);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrRestaurants);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurant.setAdapter(adapter);
    }
}

