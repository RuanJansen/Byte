package com.example.bytev2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btnNext;
    TextView textviewRegister;
    EditText email, password;
    String sEmail, sPassword;
    String type = "Login";
    static Boolean loginChecker = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_login);

        btnNext = (Button) findViewById(R.id.btnConfirm);
        textviewRegister = (TextView) findViewById(R.id.textviewRegester);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
                if (checkLogin()) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantActivity.class);
                    startActivityForResult(myIntent, 0);
                } else {
                    System.out.println("user not found");
                }
            }
        });

        textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Register");
                Intent myIntent = new Intent(view.getContext(), RegisterActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public void getLogin() {
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        sEmail = email.getText().toString();
        sPassword = password.getText().toString();

        
        
        
        
        
    }

    public boolean checkLogin() {

BackgroundAsync backgroundAsync = new BackgroundAsync(this);
backgroundAsync.execute(type,sEmail,sPassword);



        }
}
