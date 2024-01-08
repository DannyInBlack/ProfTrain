package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class Home extends AppCompatActivity {

    WorkManager workManager = WorkManager.getInstance(Home.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(NetworkManager.class).build();
        workManager.enqueue(workRequest);

        workManager.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, workInfo -> {
            if(workInfo.getState() == WorkInfo.State.SUCCEEDED){

                Log.d("Work Data", workInfo.getOutputData().toString());
                boolean connected = workInfo.getOutputData().getBoolean("Connected", false);
                Log.d("Connection status", String.valueOf(connected));
                if(connected){
                    showNotification("Connection Status", "You are connected");
                }
                else{
                    showNotification("Connection Status", "You are not connected");
                }
            }
        });

    }

    private void showNotification(String title, String message) {
        String channelId = "wifi_channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.car_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
            if(notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelId, "Network Check", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }


        }



        notificationManager.notify(0, builder.build());



    }


}