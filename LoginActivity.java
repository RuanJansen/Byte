package com.example.bytev2;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {
    Button btnNext;
    TextView textviewRegister;
    EditText email, password;
    String sEmail, sPassword;
    String type = "login";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_login);
        btnNext = (Button) findViewById(R.id.btnConfirm);
        textviewRegister = (TextView) findViewById(R.id.textviewRegester);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
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


    private  void reset() {
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        email.setText("");
        password.setText("");
    }


    public void getLogin() {
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        sEmail = email.getText().toString();
        sPassword = password.getText().toString();
        System.out.println(sEmail+sPassword+" 1");

        class BackgroundAsync extends AsyncTask<String,Void,String> {
            String type;
            AlertDialog alertDialog;
            Context context;
            BackgroundAsync(Context ctx) {
                context = ctx;
            }
            @Override
            protected String doInBackground(String... params) {
                type = params[0];
                String login_url = "http://192.168.0.131/BytePHP/UserLogin.php";
                if(type.equals("login"))
                {
                    try {
                        String email = params[1];
                        String password = params[2];
                        URL url = new URL(login_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password,"UTF-8")
                                + "&" + URLEncoder.encode("email","UTF-8") + "=" +URLEncoder.encode(email,"UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                        String result = "";
                        String line;
                        while((line = bufferedReader.readLine()) != null){
                            result += line;
                        }
                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();
                        return result;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPreExecute()
            {
                alertDialog = new AlertDialog.Builder(context).create();
            }
            @Override
            protected void onPostExecute(String s)
            {
                if(s.contains("true"))
                {
                    nextPage();
                    reset();
                }
                else{
                    alertDialog.setMessage(s);
                    alertDialog.show();
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }
        BackgroundAsync backgroundAsync = new BackgroundAsync(this);
        backgroundAsync.execute(type, sEmail,sPassword);
    }

    private void nextPage() {
        Intent myIntent = new Intent(this, RestaurantActivity.class);
        startActivityForResult(myIntent, 0);
    }

}