package com.example.bytev2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class CheckoutActivity extends AppCompatActivity {
    public static String globalHour = null;
    public static String globalMinute = null;
    Button btnPay;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_checkout);
        btnPay = (Button) findViewById(R.id.btnPay);
        EditText edtHour;
        edtHour = (EditText) findViewById(R.id.edtHour);
        EditText edtMinute;
        edtMinute = (EditText) findViewById(R.id.edtMinute);

        System.out.println(globalHour + ":" + globalMinute);
        init();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckInputBlank()){
                    globalMinute = String.valueOf(edtMinute);
                    if (CheckTimeInput()) {
                        globalHour = String.valueOf(edtHour);
                        Intent myIntent = new Intent(view.getContext(), PaymentActivity.class);
                        startActivityForResult(myIntent, 0);
                    }
                }else{
                    AlertDialog.Builder builderDialog = new AlertDialog.Builder(CheckoutActivity.this);
                    builderDialog.setMessage("Enter a collection time!").setCancelable(false).setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            System.out.println("AlertDialog message close");
                        }
                    });
                    AlertDialog alertDialog = builderDialog.create();
                    alertDialog.show();
                }

            }
        });
    }
    private boolean CheckInputBlank(){
        String blank = "";
        boolean check1 = false, check2 = false;

        EditText edtHour;
        edtHour = (EditText) findViewById(R.id.edtHour);
        EditText edtMinute;
        edtMinute = (EditText) findViewById(R.id.edtMinute);

        String hour= String.valueOf(edtHour.getText());
        String minute = String.valueOf(edtMinute.getText());

        if(hour.equals(blank)){
            //check1 = true;
            System.out.println("blank1");
            return false;
        }
        if(minute.equals(blank)){
            //check2 = true;
            System.out.println("blank2");
            return false;
        }
//        if(check1 || check2){
//            return false;
//        }
        System.out.println("not blank");
        return true;
    }

    private boolean CheckTimeInput() {
        EditText edtHour;
        edtHour = (EditText) findViewById(R.id.edtHour);
        EditText edtMinute;
        edtMinute = (EditText) findViewById(R.id.edtMinute);
        int ihour, iminute;
        ihour = Integer.parseInt(String.valueOf(edtHour.getText()));
        iminute = Integer.parseInt(String.valueOf(edtMinute.getText()));
        boolean check1 = false, check2 = false;
        if(ihour<=23){
            check1 = true;
        }
        if(iminute<=59){
            check2 = true;
        }
        if((check1) && (check2)){
            return true;
        }
        return false;
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

