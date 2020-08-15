package com.example.bytev2;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
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

public class BackgroundAsync extends AsyncTask<String,Void,String> {


    AlertDialog alertDialog;
    Context context;

    BackgroundAsync(Context ctx) {

        context = ctx;
    }

        @Override
    protected String doInBackground(String... params) {
        String type = params[0];
            String register_url = "http://192.168.1.109/BytePHP/UserRegister.php";
            String login_url = "http://192.168.1.109/BytePHP/UserLogin.php";

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
                String post_data = URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password,"UTF-8") + "&" + URLEncoder.encode("email","UTF-8") + "=" +URLEncoder.encode(email,"UTF-8");

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
        alertDialog.setTitle("Login Status");

    }

        @Override
    protected void onPostExecute(String s)
    {
        if(s.contains("true"))
        {
            LoginActivity.loginChecker = true;

            alertDialog.setMessage("Logged in");
            alertDialog.show();
            if(LoginActivity.loginChecker)
            {

                System.out.println("checked");
            }

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
