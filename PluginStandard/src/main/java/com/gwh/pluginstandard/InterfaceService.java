package com.gwh.pluginstandard;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public interface InterfaceService {
    public void onCreate();
    public void onStart(Intent intent,int startId);
    public int onStartCommand(Intent intent,int flags,int startId);

    public void onDestory();

    public void onConfigurationChanged(Configuration newConfig);

    public void onLowMemory();
    public void onTrimMemory(int level);

    public IBinder onBind(Intent intent);

    public boolean onUnbind(Intent intent);

    public void onRebind(Intent intent);

    public void onTaskRemoved(Intent intent);

    public void attach(Service proxyService);
}
