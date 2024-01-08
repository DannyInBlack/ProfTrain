package com.example.project;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NetworkManager extends Worker {

    public NetworkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            Log.d("NetworkWorker", "Working: Checking network state...");
            boolean ok = checkWiFiState(getApplicationContext());
            if(ok){
                Log.d("NetworkWorker", "Working: Connected...");

            } else{
                Log.w("NetworkWorker", "Working: Disconnected...");
            }

            return Result.success(new Data.Builder().putBoolean("Connected", ok).build());
        } catch (Exception e) {
            Log.d("NetworkWorker", "Failure: Cannot check network!");
            return Result.failure();
        }

    }

    private boolean checkWiFiState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiNetwork != null) {
            return wifiNetwork.isConnectedOrConnecting();
        }

        return false;
    }

//
}
