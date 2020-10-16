package com.gwh.pluginstandard;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public interface InterfaceActivity {
    public void attach(Activity activity);
    /**
     * 生命周期
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();

}
