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
    int tableSize=0;
    String[] restName;
    String[] restLocation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_restaurant);

        getJSON("http://192.168.0.131/BytePHP/Restaurants.php");
        //getRestaurant();
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
        //tableSize = 5;
        String[] arrRestaurants = new String[tableSize];
        //arrRestaurants[0] = "Select here";
        for (int j = 0; j < tableSize; j++) {
            arrRestaurants[j] = restName[j]+"-"+restLocation[j];
            //arrRestaurants[j] ="LOOP";
        }
        Spinner restaurant = (Spinner) findViewById(R.id.spnrRestaurants);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrRestaurants);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurant.setAdapter(adapter);
    }

    private void getJSON(final String urlWebservice) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    System.out.println(s);
                    loadIntoListView(s);
                    getRestaurant();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebservice);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = br.readLine()) != null) {
                        System.out.println(json);
                        sb.append(json + "/n");
                    }
                    return sb.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        System.out.println(jsonArray.length());
        tableSize = jsonArray.length();
        restName = new String[jsonArray.length()];
        restLocation = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            restName[i] = obj.getString("restName");
            restLocation[i] = obj.getString("restLocation");
        }
    }
}

