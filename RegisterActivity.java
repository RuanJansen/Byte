package com.example.bytev2;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    String[] arrRegisterDetails = new String[5];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_register);
    }
    public void onButtonClick(View view) {
        EditText firstNameText = findViewById(R.id.firstNameText);
        EditText lastNameText = findViewById(R.id.lastNameText);
        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);
        EditText rePasswordText = findViewById(R.id.rePasswordText);
        TextView passWrongText = findViewById(R.id.passwordTextView);
        TextView emailWrongText = findViewById(R.id.emailTextView);
        String userPassword = String.valueOf(passwordText.getText());
        String reUserPassword = String.valueOf(rePasswordText.getText());
        String userEmail = String.valueOf(emailText.getText());
        String userFirstName = String.valueOf(firstNameText.getText());
        String userLastName = String.valueOf(lastNameText.getText());
        boolean passOk = false;
        boolean emailOk = false;
        if (userEmail.contains("@")) {
            emailOk = true;
            emailWrongText.setText("");
        } else {
            emailOk = false;
            emailWrongText.setText("Invalid email");
            emailWrongText.setTextColor(Color.parseColor("red"));
        }
        if (reUserPassword.equals(userPassword)) {
            passOk = true;
            passWrongText.setText("");
        } else {
            passWrongText.setText("Wrong password");
            passWrongText.setTextColor(Color.parseColor("red"));
        }
        if ((passOk) && (emailOk)) {
            arrRegisterDetails[0] = userFirstName;
            arrRegisterDetails[1] = userLastName;
            arrRegisterDetails[2] = userEmail;
            arrRegisterDetails[3] = userPassword;
            arrRegisterDetails[4] = reUserPassword;
            for (int i = 0; i < arrRegisterDetails.length; i++) {
                System.out.println(arrRegisterDetails[i]);
            }
            Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
            startActivityForResult(myIntent, 0);
        }
    }
}

