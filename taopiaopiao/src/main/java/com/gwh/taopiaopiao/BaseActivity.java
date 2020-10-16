package com.gwh.taopiaopiao;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gwh.pluginstandard.InterfaceActivity;

import androidx.annotation.NonNull;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public class BaseActivity extends Activity implements InterfaceActivity {
    protected Activity mActivity;
    @Override
    public void attach(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void setContentView(int layoutResID) {
        mActivity.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mActivity.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return mActivity.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mActivity.getClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent intent1 = new Intent();
        intent1.putExtra("className", intent.getComponent().getClassName());
        mActivity.startActivity(intent1);
    }


    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return mActivity.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return mActivity.getApplicationInfo();
    }


    @Override
    public Window getWindow() {
        return mActivity.getWindow();
    }


    @Override
    public WindowManager getWindowManager() {
        return mActivity.getWindowManager();
    }




    @Override
    public void setContentView(View view) {
        if (mActivity != null) {
            mActivity.setContentView(view);
        }else {
            super.setContentView(view);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
