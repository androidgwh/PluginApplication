package com.gwh.taopiaopiao;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import com.gwh.pluginstandard.InterfaceService;

import androidx.annotation.Nullable;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public class BaseService extends Service implements InterfaceService {
    private Service mService;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestory() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void attach(Service proxyService) {
        this.mService = proxyService;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {


    }

    @Override
    public void onLowMemory() {


    }

    @Override
    public void onTrimMemory(int level) {


    }

    @Override
    public boolean onUnbind(Intent intent) {

        return false;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {


    }

}
