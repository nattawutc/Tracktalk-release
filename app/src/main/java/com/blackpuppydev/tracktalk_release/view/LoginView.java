package com.blackpuppydev.tracktalk_release.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.blackpuppydev.tracktalk_release.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Map;

public class LoginView extends FrameLayout implements View.OnClickListener {

    private final String TAG = "LoginView";
    private Button btn_confirm;
    private EditText edit_username;
    private EditText edit_password;

    private OnCallBack onCallBack;

    public LoginView(Context context) {
        super(context);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_login, this, true);

        //find view by id
        btn_confirm = findViewById(R.id.btn_confirm);
        edit_username = findViewById(R.id.username);
        edit_password = findViewById(R.id.password);


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_confirm) {
            String username = edit_username.getText().toString();
            String password = edit_password.getText().toString();

            //check from firebase
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference dbLogin = firebaseFirestore.collection("login");

            dbLogin.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> result = document.getData();
                        if (username.equals(result.get("username"))) {
                            if (password.equals(result.get("password"))) {
                                //login success
                                onCallBack.onLoginSuccess();
                            }
                        }
                        Log.d(TAG, "onSuccess get data : " + document.getId() + " => " + document.getData());
                    }
                }
            }).addOnFailureListener(e -> {
                onCallBack.onLoginFail();
            });
        } else if (id == R.id.btn_register){
            onCallBack.goToRegister();
        }


    }


    public interface OnCallBack {
        void onLoginSuccess();
        void onLoginFail();
        void goToRegister();
    }


    public void setLoginCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

}
