package com.example.bytev2;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuActivity extends AppCompatActivity {
    int iQty = 0;
    Button btnCheckout;
    int tableSize;
    String[] itemName;
    String[] itemPrice;
    String[] itemURL;
    String[] itemType;

    protected void onBeverageClick(View view){
        System.out.println("tiBeverage");
    }

    protected void onMealClick(){
        System.out.println("tiMeal");

    }

    protected void onOtherClick(){
        System.out.println("tiOther");

    }




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_menu);



        btnCheckout = (Button) findViewById(R.id.btnConfirm);
        getJSON("http://192.168.0.131/BytePHP/Menu.php");
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CheckoutActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }

    private void Dynamic() {
        for (int i = 0; i < tableSize; i++) {
            System.out.println(itemName[i]+" "+itemPrice[i]+" "+" "+itemType[i]+"\n");
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(200, 100, 10.0f);
            row.setLayoutParams(lp);

//            TabLayout tlMenu = (TabLayout) findViewById(R.id.tlMenu);
//            TabLayout.Tab tiBeverage = new TabLayout.Tab();
//            tiBeverage.setText("Beverage");
//            TabLayout.Tab tiMenu = new TabLayout.Tab();
//            TabLayout.Tab tiOther = new TabLayout.Tab();

            //TabItem tiOther = new TabItem(this);

            TableLayout tablelayoutMenu = (TableLayout) findViewById(R.id.tablelayoutMenu);
            TextView textviewItem = new TextView(this);
            TextView textviewPrice = new TextView(this);
            TextView textviewQty = new TextView(this);
            ImageButton btnAdd = new ImageButton(this);
            ImageButton btnSubtract = new ImageButton(this);
            ImageView ImageviewItem = new ImageView(this);

            LinearLayout linlayHori1 = new LinearLayout(this);
            LinearLayout linlayVert1 = new LinearLayout(this);
            LinearLayout linlayHori2 = new LinearLayout(this);
            LinearLayout linlayVert2 = new LinearLayout(this);

            textviewQty.setText(String.valueOf(iQty));
            textviewQty.setTextSize(24);
            textviewQty.setTextColor(Color.parseColor("#F23B5F"));
            textviewQty.setGravity(Gravity.CENTER);
            textviewQty.setId(i);

            textviewItem.setText(itemName[i]);
            textviewItem.setTextSize(30);
            textviewItem.setTextColor(Color.parseColor("#F23B5F"));
            textviewItem.setId(i);

            textviewPrice.setText("R" + itemPrice[i]);
            textviewPrice.setTextSize(24);
            textviewPrice.setTextColor(Color.parseColor("#F23B5F"));
            textviewPrice.setId(i);

            linlayHori1.setOrientation(LinearLayout.HORIZONTAL);
            linlayVert1.setOrientation(LinearLayout.VERTICAL);
            linlayHori2.setOrientation(LinearLayout.HORIZONTAL);
            linlayVert2.setOrientation(LinearLayout.VERTICAL);

            Bitmap bMapAdd = BitmapFactory.decodeResource(getResources(), R.drawable.add);
            Bitmap bMapScaleAdd = Bitmap.createScaledBitmap(bMapAdd, 100, 100, true);
            btnAdd.setImageBitmap(bMapScaleAdd);
            btnAdd.setBackgroundColor(Color.WHITE);
            btnAdd.setId(i);

            Bitmap bMapMinus = BitmapFactory.decodeResource(getResources(), R.drawable.minus);
            Bitmap bMapScaleMinus = Bitmap.createScaledBitmap(bMapMinus, 100, 100, true);
            btnSubtract.setImageBitmap(bMapScaleMinus);
            btnSubtract.setBackgroundColor(Color.WHITE);
            btnSubtract.setId(i);

            Bitmap bMapItem = BitmapFactory.decodeResource(getResources(), R.drawable.byte_white);
            Bitmap bMapScaleItem = Bitmap.createScaledBitmap(bMapItem, 600, 400, true);
            ImageviewItem.setImageBitmap(bMapScaleItem);

            row.addView(ImageviewItem);
            row.addView(linlayVert1);
            linlayVert1.addView(textviewItem);
            linlayVert1.addView(linlayHori1);
            linlayHori1.addView(textviewPrice);
            linlayHori1.addView(linlayVert2);
            linlayVert2.addView(textviewQty);
            linlayVert2.addView(linlayHori2);
            linlayHori2.addView(btnAdd);
            linlayHori2.addView(btnSubtract);
            tablelayoutMenu.addView(row, i);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    iQty = Integer.parseInt((String) textviewQty.getText());
                    iQty++;
                    textviewQty.setText(String.valueOf(iQty));
                }
            });

            btnSubtract.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    iQty = Integer.parseInt((String) textviewQty.getText());
                    if (iQty > 0) {
                        iQty--;
                        textviewQty.setText(String.valueOf(iQty));
                    }
                }
            });
        }
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
                    Dynamic();
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
        itemName = new String[jsonArray.length()];
        itemPrice = new String[jsonArray.length()];
        itemURL = new String[jsonArray.length()];
        itemType = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            itemName[i] = obj.getString("itemName");
            itemPrice[i] = obj.getString("itemPrice");
            itemURL[i] = obj.getString("imageURL");
            if(!itemPrice[i].contains(".")){
                itemPrice[i] = itemPrice[i]+".00";
            }
            itemType[i] = obj.getString("itemType");
        }
    }
}