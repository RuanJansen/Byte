package com.example.bytev2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
public class PaymentActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ImageView imgCash = (ImageView) findViewById(R.id.imgCash);
//        imgCash.setBackgroundColor(Color.WHITE);
        setContentView(R.layout.byte_payment);
        Button btnConfirm;
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        RadioButton rbPaypal, rbVisa, rbCash;
        btnConfirm.setEnabled(false);
        rbPaypal = (RadioButton) findViewById(R.id.rbPaypal);
        rbVisa = (RadioButton) findViewById(R.id.rbVisa);
        rbCash = (RadioButton) findViewById(R.id.rbCash);
        CheckBox cbSave;
        cbSave = (CheckBox) findViewById(R.id.cbSave);

        ClearPaypal();
        ClearVisa();
        ClearCash();

        rbPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearVisa();
                ClearCash();

                EditText edtEmail;
                edtEmail = (EditText) findViewById(R.id.edtEmail);
                edtEmail.setEnabled(true);
                btnConfirm.setEnabled(true);
            }
        });

        rbVisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearPaypal();
                ClearCash();

                EditText edtCardNum;
                edtCardNum = (EditText) findViewById(R.id.edtCardNum);
                edtCardNum.setEnabled(true);

                EditText edtCVV;
                edtCVV = (EditText) findViewById(R.id.edtCVV);
                edtCVV.setEnabled(true);

                EditText edtDate;
                edtDate = (EditText) findViewById(R.id.edtDate);
                edtDate.setEnabled(true);

                btnConfirm.setEnabled(true);
            }
        });

        rbCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearPaypal();
                ClearVisa();
                btnConfirm.setEnabled(true);

                RadioButton rbCash;
                rbCash = (RadioButton) findViewById(R.id.rbCash);
                rbCash.setChecked(true);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbPaypal.isChecked()) {
                    if(checkPaypal()){
                        if(cbSave.isChecked()){
                            savePaymentDetails();
                            System.out.println("Saved");
                        }
                        Intent myIntent = new Intent(view.getContext(), NotificationActivity.class);
                        startActivityForResult(myIntent, 0);
                    }else{
                        System.out.println("blank fields Paypal");
                    }
                }

                if (rbVisa.isChecked()) {
                    if(checkVisa()){
                        if(cbSave.isChecked()){
                            savePaymentDetails();
                            System.out.println("Saved");
                        }
                        Intent myIntent = new Intent(view.getContext(), NotificationActivity.class);
                        startActivityForResult(myIntent, 0);
                    }else{
                        System.out.println("blank fields Visa");
                    }
                }

            }

            private boolean checkVisa() {
                String blank = "";
                boolean check1=true,check2=true,check3=true;
                EditText edtCardNum;
                edtCardNum = (EditText) findViewById(R.id.edtCardNum);
                EditText edtCVV;
                edtCVV = (EditText) findViewById(R.id.edtCVV);
                EditText edtDate;
                edtDate = (EditText) findViewById(R.id.edtDate);
                String CardNum= String.valueOf(edtCardNum.getText());
                String CVV= String.valueOf(edtCVV.getText());
                String Date= String.valueOf(edtDate.getText());
                System.out.println(CardNum.equals(blank));
                if (CardNum.equals(blank)) {
                    check1 = false;
                }
                if (CVV.equals(blank)) {
                    check2 = false;
                }
                if (Date.equals(blank)) {
                    check3 = false;
                }
                if((check1) && (check2) && (check3)){
                    return true;
                }
                return false;
            }
        });
    }
    private boolean checkPaypal() {
        String blank = "";
        EditText edtEmail;
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        String Email= String.valueOf(edtEmail.getText());
        if (Email.equals(blank)) {
            System.out.println("Check paypal");
            return false;
        }
        return true;
    }

    private void savePaymentDetails() {
        EditText edtCardNum;
        edtCardNum = (EditText) findViewById(R.id.edtCardNum);

        EditText edtDate;
        edtDate = (EditText) findViewById(R.id.edtDate);

        EditText edtEmail;
        edtEmail = (EditText) findViewById(R.id.edtEmail);
    }

    private void ClearCash() {
        RadioButton rbCash;
        rbCash = (RadioButton) findViewById(R.id.rbCash);
        rbCash.setChecked(false);
    }

    private void ClearVisa() {
        RadioButton rbVisa;
        rbVisa = (RadioButton) findViewById(R.id.rbVisa);
        rbVisa.setChecked(false);
        EditText edtCardNum;
        edtCardNum = (EditText) findViewById(R.id.edtCardNum);
        edtCardNum.setEnabled(false);
        EditText edtCVV;
        edtCVV = (EditText) findViewById(R.id.edtCVV);
        edtCVV.setEnabled(false);
        EditText edtDate;
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtDate.setEnabled(false);
    }

    private void ClearPaypal() {
        RadioButton rbPaypal;
        rbPaypal = (RadioButton) findViewById(R.id.rbPaypal);
        rbPaypal.setChecked(false);
        EditText edtEmail;
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtEmail.setEnabled(false);
    }
}
