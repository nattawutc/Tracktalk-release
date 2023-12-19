package com.blackpuppydev.tracktalk_release.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.blackpuppydev.tracktalk_release.R;
import com.blackpuppydev.tracktalk_release.demo.TestActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


    }



    public void permissionChecker(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
            try {
                PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
                if (info.requestedPermissions != null) {
                    for (String permission : info.requestedPermissions) {
                        ActivityCompat.requestPermissions(this,new String[]{permission},2);
                    }
                } else {
                    startActivity(new Intent(this,TestActivity.class));
                    finish();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length ; i++) {
            boolean checkPermission = checkPermissionResult(requestCode, permissions[i], grantResults[i] != PackageManager.PERMISSION_GRANTED);
            if (checkPermission){
                startActivity(new Intent(this, TestActivity.class));
                finish();
            }else{ }
        }
    }


    protected boolean checkPermissionResult(final int requestCode, final String permission, final boolean result) {
        if (!result && (permission != null)) {
            ActivityCompat.requestPermissions(this,new String[]{permission},requestCode);
            return true;
        }
        return false;
    }


    //time out for speech



}