package com.example.bytev2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantActivity extends AppCompatActivity {
    Button btnNext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_restaurant);
        getRestaurant();
        btnNext = (Button)findViewById(R.id.btnConfirm);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("click");
                Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public void getRestaurant(){
        String[] arrRestaurants = new String[4];
        arrRestaurants[0]="Select here";
        arrRestaurants[1]="Pearson Durbanville";
        arrRestaurants[2]="Pearson Midrand";
        arrRestaurants[3]="Pearson Cape Town";
        Spinner restaurant = (Spinner)findViewById(R.id.spnrRestaurants);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrRestaurants);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurant.setAdapter(adapter);
    }
}
