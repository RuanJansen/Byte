package com.example.bytev2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    int iQty = 0;
    Button btnCheckout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_menu);
        btnCheckout = (Button) findViewById(R.id.btnConfirm);
        Dynamic();



        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CheckoutActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }








    private void Dynamic(){
        for (int i = 0; i <9; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(200,100, 10.0f);
            row.setLayoutParams(lp);

            TableLayout tablelayoutMenu = (TableLayout) findViewById(R.id.tablelayoutMenu);
            TextView textviewItem = new TextView(this);
            TextView textviewPrice = new TextView(this);
            TextView textviewQty = new TextView(this);
            ImageButton btnAdd = new ImageButton(this);
            ImageButton btnSubtract = new ImageButton(this);
            ImageView ImageviewItem = new ImageView(this);

            LinearLayout linlayHori1 = new LinearLayout(this);
            LinearLayout linlayVert1= new LinearLayout(this);
            LinearLayout linlayHori2 = new LinearLayout(this);
            LinearLayout linlayVert2 = new LinearLayout(this);

            textviewQty.setText(String.valueOf(iQty));
            //textviewQty.setText("0");
            textviewQty.setTextSize(24);
            textviewQty.setTextColor(Color.parseColor("#F23B5F"));
            textviewQty.setGravity(Gravity.CENTER);
            textviewQty.setId(i);

            textviewItem.setText("yummy food");
            textviewItem.setTextSize(30);
            textviewItem.setTextColor(Color.parseColor("#F23B5F"));
            textviewItem.setId(i);

            textviewPrice.setText("R"+"100.00");
            textviewPrice.setTextSize(24);
            textviewPrice.setTextColor(Color.parseColor("#F23B5F"));
            textviewPrice.setId(i);

            linlayHori1.setOrientation(LinearLayout.HORIZONTAL);
            linlayVert1.setOrientation(LinearLayout.VERTICAL);
            linlayHori2.setOrientation(LinearLayout.HORIZONTAL);
            linlayVert2.setOrientation(LinearLayout.VERTICAL);

            Bitmap bMapAdd = BitmapFactory.decodeResource(getResources(),R.drawable.add);
            Bitmap bMapScaleAdd = Bitmap.createScaledBitmap(bMapAdd, 100, 100, true);
            btnAdd.setImageBitmap(bMapScaleAdd);
            btnAdd.setBackgroundColor(Color.WHITE);
            btnAdd.setId(i);

            Bitmap bMapMinus = BitmapFactory.decodeResource(getResources(),R.drawable.minus);
            Bitmap bMapScaleMinus = Bitmap.createScaledBitmap(bMapMinus, 100, 100, true);
            btnSubtract.setImageBitmap(bMapScaleMinus);
            btnSubtract.setBackgroundColor(Color.WHITE);
            btnSubtract.setId(i);

            Bitmap bMapItem = BitmapFactory.decodeResource(getResources(),R.drawable.burg_825x510);
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
            tablelayoutMenu.addView(row,i);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    iQty=Integer.parseInt((String) textviewQty.getText());
                    iQty++;
                    textviewQty.setText(String.valueOf(iQty));
                }
            });

            btnSubtract.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    iQty=Integer.parseInt((String) textviewQty.getText());
                    if(iQty>0){
                        iQty--;
                        textviewQty.setText(String.valueOf(iQty));
                    }
                }
            });
        }
    }
}
