package com.example.bytev2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginActivity extends AppCompatActivity {
    Button btnNext;
    TextView textviewRegister;
    EditText email, password;
    String sEmail, sPassword;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

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
        System.out.println(sEmail+" " + sPassword);
    }

    public boolean checkLogin() {
        conn = MySQLConnect.ConnectDB();
        String Sql = "Select * from users where userEmail=? and userPassword=?";
        try {
            pst = conn.prepareStatement(Sql);
            pst.setString(1, sEmail);
            pst.setString(2, sPassword);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("loggedIn");
                return true;
            } else {
                System.out.println("1");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
}

