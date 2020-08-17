package com.example.bytev2;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationActivity extends AppCompatActivity{
    LocalTime now, userTime;
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_notification);
        TextView textviewTimer;
        textviewTimer = (TextView) findViewById(R.id.textviewTimer);

        userTime = LocalTime.parse(CheckoutActivity.globalHour+":"+CheckoutActivity.globalMinute+":"+"00");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                now = LocalTime.now();
                int timeLeft =
                        (userTime.getHour() * 3600 + userTime.getMinute() * 60 + userTime.getSecond()) - (now.getHour() * 3600 + now.getMinute() * 60 + now.getSecond() );

                int hourLeft = (int)(Math.floor(timeLeft/3600));
                timeLeft -= hourLeft*3600;

                int minuteLeft = (int)(Math.floor(timeLeft/60));
                timeLeft -= minuteLeft*60;

                textviewTimer.setText( hourLeft + ":" + minuteLeft + ":" + timeLeft);
                //System.out.println("Time until your order is ready: " + hourLeft + ":" + minuteLeft + ":" + timeLeft);

            }
        },0,1000);

        SendNotification();
    }

    private void SendNotification() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder( NotificationActivity.this)
//                .setSmallIcon(R.drawable.byte_white)
//                .setContentTitle("Collect your order!")
//                .setContentText("Your order is ready for collection. Please speak to one of the employees at the counter to collect it ;)")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
