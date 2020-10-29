package com.gwh.pluginapplication.bean;

import android.util.Log;

/**
 * Created by Guo Wenhui
 * 2020/10/26
 **/
public class MyObjectBean  {
    private static final String TAG = MyObjectBean.class.getSimpleName();
    public    boolean shutDown;
    public int i = 1;

    public void doWork(){
        while (!shutDown){
            Log.d(TAG, "do work");
        }
    }
}
