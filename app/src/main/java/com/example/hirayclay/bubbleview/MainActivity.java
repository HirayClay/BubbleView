package com.example.hirayclay.bubbleview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View view) {
        PopHelper popHelper = new PopHelper(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        Bubble bubble = (Bubble) inflater.inflate(R.layout.bubble_layout, null);
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        if (Settings.canDrawOverlays(this))
            popHelper.showAtPosition(bubble, xy[0], xy[1]);
        else startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
