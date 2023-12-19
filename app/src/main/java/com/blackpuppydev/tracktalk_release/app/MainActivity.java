package com.blackpuppydev.tracktalk_release.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.blackpuppydev.tracktalk_release.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if

        startActivity(new Intent(this,SplashScreenActivity.class));

    }


}