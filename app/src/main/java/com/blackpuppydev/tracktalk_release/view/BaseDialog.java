package com.blackpuppydev.tracktalk_release.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.blackpuppydev.tracktalk_release.R;

public class BaseDialog extends Dialog implements View.OnClickListener{

    private final String TAG = "BaseDialog";
    private OnCallBack callBack;
    private TextView textDialog;

    public BaseDialog(Context context) {
        super(context);
        initDialog();
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        initDialog();
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    public void initDialog(){

        Log.d(TAG,"initDialog BaseDialog");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.base_dialog);

        textDialog = findViewById(R.id.text_dialog);







    }


    @Override
    public void show() {
        super.show();


    }

    public void setTextContent(String textContent){
        textDialog.setText(textContent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_confirm){
            callBack.onComplete();
        }

    }


    public interface OnCallBack {
        void onComplete();
    }

    public void setCallBack(OnCallBack callBack){
        this.callBack = callBack;
    }

}
