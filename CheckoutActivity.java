package com.example.bytev2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class CheckoutActivity extends AppCompatActivity {
    Button btnPay;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_checkout);
        init();
        btnPay = (Button)findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PaymentActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
    public void init() {
        TableLayout tablelayoutOrder = (TableLayout) findViewById(R.id.tablelayoutOrder);
        TextView textviewOrderNum = new TextView(this);
        TableRow row1 = new TableRow(this);
        TableRow.LayoutParams lp1 = new TableRow.LayoutParams(200,100, 10.0f);
        row1.setLayoutParams(lp1);
        for (int i = 0; i < 10; i++) {
            TableRow row2 = new TableRow(this);
            TableRow.LayoutParams lp2 = new TableRow.LayoutParams(200,100, 10.0f);
            row2.setLayoutParams(lp2);
            TextView textviewItem = new TextView(this);
            TextView textviewPrice = new TextView(this);

            textviewItem.setText("Yummy TUM!\t\t\t\t\t\t\t\t\t\t\t\t\tR200.00");
            textviewItem.setTextSize(30);
            textviewItem.setTextColor(Color.parseColor("#F23B5F"));
            textviewItem.setGravity(Gravity.RIGHT);

            row2.addView(textviewItem);
            row2.addView(textviewPrice);
            tablelayoutOrder.addView(row2, i);
        }
        textviewOrderNum.setText("Order No."+"1");
        textviewOrderNum.setTextSize(30);
        textviewOrderNum.setTextColor(Color.parseColor("#F23B5F"));
        textviewOrderNum.setGravity(Gravity.CENTER);
        row1.addView(textviewOrderNum);
        tablelayoutOrder.addView(row1, 0);
    }
}

