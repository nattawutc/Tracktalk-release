package com.blackpuppydev.tracktalk_release.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.blackpuppydev.tracktalk_release.R;
import com.blackpuppydev.tracktalk_release.demo.TestActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        //for test demo
        startActivity(new Intent(this,TestActivity.class));
        finish();

//        checkUserLogin();
    }

    private void checkUserLogin() {


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final int n = Math.min(permissions.length, grantResults.length);
        for (int i = 0; i < n; i++) {
            checkPermissionResult(requestCode, permissions[i], grantResults[i] != PackageManager.PERMISSION_GRANTED);
        }
    }


    protected void checkPermissionResult(final int requestCode, final String permission, final boolean result) {

        if (!result && (permission != null)) {
            ActivityCompat.requestPermissions(this,new String[]{permission},requestCode);
        }
    }



}