package com.blackpuppydev.tracktalk_release.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blackpuppydev.tracktalk_release.R;
import com.blackpuppydev.tracktalk_release.service.Recognizer;

import java.util.ArrayList;
import java.util.Locale;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();


    }



    private void initView() {
        Button startSpeech = findViewById(R.id.startSpeech);
        Button stopSpeech = findViewById(R.id.stopSpeech);
        startSpeech.setOnClickListener(this);
        stopSpeech.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.startSpeech){
            startService(new Intent(this, Recognizer.class));
        }else if (id == R.id.stopSpeech){
            stopService(new Intent(this, Recognizer.class));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}