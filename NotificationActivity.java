package com.example.bytev2;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotificationActivity extends AppCompatActivity{
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byte_notification);
        getTime();
        SendNotification();
    }

    private void SendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( NotificationActivity.this)
                .setSmallIcon(R.drawable.byte_white)
                .setContentTitle("Collect your order!")
                .setContentText("Your order is ready for collection. Please speak to one of the employees at the counter to collect it ;)")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private void getTime() {
//        String collectionHour, collectionMinute;
//        collectionHour = CheckoutActivity.globalHour;
//        collectionMinute = CheckoutActivity.globalMinute;
//
//        int iHour, iMinute, itotalSeconds;
//        iHour = Integer.parseInt(collectionHour);
//        iMinute = Integer.parseInt(collectionMinute);
//        itotalSeconds = ((60)*(60)*iHour)+((60)*iMinute);

//        TextView timer;
//        timer = (TextView)findViewById(R.id.textviewTimer);
//
//        int millis;
//        millis = itotalSeconds*(60);
//
//        new CountDownTimer(millis, 1000) {
//            public void onTick(long millisUntilFinished) {
//                long secondsInMilli = 1000;
//                long minutesInMilli = secondsInMilli * 60;
//                long hoursInMilli = minutesInMilli * 60;
//                long elapsedHours = millisUntilFinished / hoursInMilli;
//                millisUntilFinished = millisUntilFinished % hoursInMilli;
//                long elapsedMinutes = millisUntilFinished / minutesInMilli;
//                millisUntilFinished = millisUntilFinished % minutesInMilli;
//                long elapsedSeconds = millisUntilFinished / secondsInMilli;
//                String yy = String.format("%02d:%02d", elapsedHours, elapsedMinutes);
//                timer.setText(yy);
//            }
//            public void onFinish() {
//                timer.setText("00:00");
//            }
//        }.start();
    }
}
