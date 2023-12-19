package com.blackpuppydev.tracktalk_release.app;

import android.content.Intent;
import android.os.Bundle;

import com.blackpuppydev.tracktalk_release.R;
import com.blackpuppydev.tracktalk_release.view.BaseDialog;
import com.blackpuppydev.tracktalk_release.view.LoginView;

public class LoginPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        new LoginView(this).setLoginCallBack(new LoginView.OnCallBack() {
            @Override
            public void onLoginSuccess() {
                startActivity(new Intent(LoginPageActivity.this,MainActivity.class));
            }

            @Override
            public void onLoginFail() {
                //show dialog check detail login again
                BaseDialog baseDialog = new BaseDialog(getApplicationContext());
                baseDialog.setCallBack(() -> baseDialog.dismiss());
                baseDialog.show();

            }

            @Override
            public void goToRegister() {
                //register page
            }
        });

    }
}