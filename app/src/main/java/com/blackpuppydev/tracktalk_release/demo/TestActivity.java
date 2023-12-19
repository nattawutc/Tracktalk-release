package com.blackpuppydev.tracktalk_release.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blackpuppydev.tracktalk_release.R;
import com.blackpuppydev.tracktalk_release.service.Recognizer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {


    private final String TAG = "TestActivity";
    TextView standardData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();


    }


    private void initView() {

        standardData = findViewById(R.id.standard);
        Button startSpeech = findViewById(R.id.startSpeech);
        Button stopSpeech = findViewById(R.id.stopSpeech);
        Button putDataTest = findViewById(R.id.test_put);
        startSpeech.setOnClickListener(this);
        stopSpeech.setOnClickListener(this);
        putDataTest.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.startSpeech:
                startService(new Intent(this, Recognizer.class));
                break;
            case R.id.stopSpeech:
                stopService(new Intent(this, Recognizer.class));
                break;
            case R.id.test_put:
                testPutData();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
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


    private void testPutData() {

        Log.d(TAG, "testPutData");

        Map<String, String> login = new HashMap<>();
        login.put("firstname", "Nattawut");
        login.put("lastname", "Chitsaad");
        login.put("username", "nattawut.c");
        login.put("password", "1234");

        //case add data to firebase
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("login").add(login).addOnSuccessListener(documentReference -> Log.d(TAG, "onSuccess database success")).addOnFailureListener(e -> {
            Log.d(TAG, "error database : " + e.getMessage());
        });
    }

}