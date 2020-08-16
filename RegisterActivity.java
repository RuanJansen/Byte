package com.example.bytev2;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    String[] arrRegisterDetails = new String[5];
    String type = "register";
    public static boolean check1 = true;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_register);
        btnRegister = (Button) findViewById(R.id.btnRegister);
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
        if (userEmail.contains("@")) {
            emailWrongText.setText("");
        } else {
            emailWrongText.setText("Invalid email");
            emailWrongText.setTextColor(Color.parseColor("red"));
        }
        if (reUserPassword.equals(userPassword)) {
            passWrongText.setText("");
        } else {
            passWrongText.setText("Wrong password");
            passWrongText.setTextColor(Color.parseColor("red"));
        }

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
                String register_url = "http://192.168.0.131/BytePHP/UserRegister.php";
                if (type.equals("register")) {
                    try {
                        String email = params[1];
                        String password = params[2];
                        String firstname = params[3];
                        String lastname = params[4];
                        String repassword = params[5];
                        URL url = new URL(register_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
                                + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                                + "&" + URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8")
                                + "&" + URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8")
                                + "&" + URLEncoder.encode("repassword", "UTF-8") + "=" + URLEncoder.encode(repassword, "UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String result = "";
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
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
            protected void onPreExecute() {
                alertDialog = new AlertDialog.Builder(context).create();
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.contains("successfully")) {
                    nextPage();
                } else {
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
        backgroundAsync.execute(type,userEmail,userPassword,userFirstName,userLastName,reUserPassword);
    }
    private void nextPage() {
        Intent myIntent = new Intent(this, RestaurantActivity.class);
        startActivityForResult(myIntent, 0);
    }
}

