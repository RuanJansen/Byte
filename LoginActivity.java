package com.example.abyte;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity  {
    Button btnNext;
    TextView textviewRegister;
    EditText email, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_login);
        getLogin();
        chechLogin();

        btnNext = (Button)findViewById(R.id.btnNext);
        textviewRegister = (TextView)findViewById(R.id.textviewRegester);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("click");
            }
        });
        textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("click");
            }
        });
    }

    public void getLogin(){
        email = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.edtPassword);
     }

     public void chechLogin(){
         //System.out.println(email+" "+password);
     }







}
