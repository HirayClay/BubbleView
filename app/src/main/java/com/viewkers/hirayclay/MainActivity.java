package com.viewkers.hirayclay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.bubbleview.R;
import com.viewkers.hiray.Bubble;
import com.viewkers.hiray.PopHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        PopHelper popHelper = new PopHelper(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        Bubble bubble = (Bubble) inflater.inflate(R.layout.bubble_layout, null);
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        popHelper.showAtPoint(bubble, xy[0], xy[1]);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showAtView(View view) {
        PopHelper popHelper = new PopHelper(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        Bubble bubble = (Bubble) inflater.inflate(R.layout.bubble_layout, null);
        popHelper.showAtView(bubble, view);
    }
}