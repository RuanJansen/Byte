package com.example.databasetest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText passwordET, usernameET,emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 passwordET = (EditText)findViewById(R.id.passwordTextEdit);
 usernameET = (EditText)findViewById(R.id.nameTextEdit);
 emailET = (EditText)findViewById(R.id.emailTextEdit);

    }

    public void OnLogin(View view)
    {
        String username, password,email ;
        username = usernameET.getText().toString();

        password = passwordET.getText().toString();
        email = emailET.getText().toString();

        String lastname = "Jones";
        String rePassword = "a";


        String type = "register";

        Background background = new Background(this);
        background.execute(username,lastname,password,email,rePassword,type);




    }



}