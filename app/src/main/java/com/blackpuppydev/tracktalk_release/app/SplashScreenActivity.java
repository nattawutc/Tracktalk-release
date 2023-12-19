package com.blackpuppydev.tracktalk_release.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blackpuppydev.tracktalk_release.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SplashScreenActivity extends BaseActivity {

    private final String TAG = "SplashScreenActivity";
    boolean isLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);






    }

    @Override
    protected void onResume() {
        super.onResume();

        //for test demo

        if (isLocked){
            if (checkUserLogin().isEmpty()) {
                startActivity(new Intent(SplashScreenActivity.this, LoginPageActivity.class));
            } else startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        }

        permissionChecker();
    }

    private Map<String,Object> checkUserLogin() {

        Map<String,Object> dataLogin = new HashMap<>();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference dbLogin = firebaseFirestore.collection("login");

        dbLogin.get().addOnCompleteListener(task -> {
//            Log.d("onSuccess database ","success" );
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()) {
                    dataLogin.put(document.getId(),document.getData());
                    Log.d(TAG,"onSuccess get data : " + document.getId() + " => " + document.getData());
                }
                isLocked = true;
            }
        }).addOnFailureListener(e -> {
            Log.d(TAG,"error database : " + e.getMessage());
        });

        return dataLogin;



        //TODO ADD DATA TO FIREBASE
//        Map<String,String> login = new HashMap<>();
//        login.put("firstname","Nattawut");
//        login.put("lastname","Chitsaad");
//        login.put("username","nattawut.c");
//        login.put("password","1234");
//
//        //case add data to firebase
//        firebaseFirestore.collection("login").add(login).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Log.d("onSuccess database ","success");
//            }
//        }).addOnFailureListener(e -> {
//            Log.d("error database ",e.getMessage());
//        });










//        Map<String,String> login = new HashMap<>();
//        login.put("firstname","Nattawut");
//        login.put("lastname","Chitsaad");
//        login.put("username","nattawut.c");
//        login.put("password","1234");

    }





}