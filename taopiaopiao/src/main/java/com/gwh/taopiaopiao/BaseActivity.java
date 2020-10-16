package com.gwh.taopiaopiao;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
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
        if(null==mActivity){
         super.setContentView(layoutResID);
        }else {
            mActivity.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if(null==mActivity){
            return super.findViewById(id);
        }
        return mActivity.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        if(null==mActivity){
            return super.getIntent();
        }
        return mActivity.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        if(null==mActivity){
            return super.getClassLoader();
        }
        return mActivity.getClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        Log.d("BaseActivity", intent.getComponent().getClassName());
        Intent intent1 = new Intent();
        intent1.putExtra("className", intent.getComponent().getClassName());
        if(null==mActivity){
         super.startActivity(intent);
        }else {
            mActivity.startActivity(intent1);
        }
    }

    @Override
    public ComponentName startService(Intent service) {
        Log.d("BaseActivity", service.getComponent().getClassName());
        Intent intent = new Intent();
        intent.putExtra("className", service.getComponent().getClassName());
        if(null==mActivity){
            super.startService(service);
        }
            return mActivity.startService(intent);

    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if(null==mActivity){
            return super.getLayoutInflater();
        }
        return mActivity.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if(null==mActivity){
            return super.getApplicationInfo();
        }
        return mActivity.getApplicationInfo();
    }


    @Override
    public Window getWindow() {
        if(null==mActivity){
            return super.getWindow();
        }
        return mActivity.getWindow();
    }


    @Override
    public WindowManager getWindowManager() {
        if(null==mActivity){
            return super.getWindowManager();
        }
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
