package com.example.databasetest2;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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
import java.sql.SQLOutput;

public class Background extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context context;
    Background (Context ctx){

        context = ctx;

    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[5];





        if(type.equals("login"))
        {
            String login_url = "http://192.168.1.109/testForAndroid/loginTest.php";
            try {
                String username = params[0];
                String password = params[2];
                String email = params[3];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(username,"UTF-8") + "&" + URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password,"UTF-8")+ "&" + URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(email,"UTF-8");
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
        else if(type.equals("register"))
        {
            String register_url = "http://192.168.1.109/testForAndroid/register.php";
            try {
                String username = params[0];
                String lastName = params[1];
                String password = params[2];
                String email = params[3];
                String rePassword = params[4];

                for (int i = 0; i < params.length; i++)
                {

                    System.out.println(params[i]);

                }

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("userFirstName","UTF-8") + "=" + URLEncoder.encode(username,"UTF-8") + "&" + URLEncoder.encode("userPassword","UTF-8") + "=" +
                        URLEncoder.encode(password,"UTF-8")+ "&" + URLEncoder.encode("userEmail","UTF-8") + "=" + URLEncoder.encode(email,"UTF-8") + "&" + URLEncoder.encode("userLastName","UTF-8") + "="
                        + URLEncoder.encode(lastName,"UTF-8") + "&" + URLEncoder.encode("userRePassword","UTF-8") + "=" + URLEncoder.encode(rePassword,"UTF-8");
                bufferedWriter.write(post_data);

                System.out.println(post_data);

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

    protected void onPreExecute()
    {
    alertDialog = new AlertDialog.Builder(context).create();
    alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String s) {

        alertDialog.setMessage(s);
        alertDialog.show();


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
