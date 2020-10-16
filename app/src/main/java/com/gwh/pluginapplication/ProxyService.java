package com.gwh.pluginapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.gwh.pluginstandard.InterfaceService;

import java.lang.reflect.Constructor;

import androidx.annotation.Nullable;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public class ProxyService extends Service {
    private static final String TAG = ProxyService.class.getSimpleName();
    private String className;
    private InterfaceService newInstance;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        init(intent);
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        if(newInstance==null) {
            init(intent);
        }
        return newInstance.onStartCommand(intent, flags, startId);

    }

    private void init(Intent intent){
        className = intent.getStringExtra("className");
        try {
            Class<?> loadClass =getClassLoader().loadClass(className);
            Constructor<?> constructor = loadClass.getConstructor(new Class[]{});
            newInstance = (InterfaceService) constructor.newInstance(new String[]{});
            newInstance.attach(this);
            newInstance.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public void onDestroy() {
        newInstance.onDestory();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return newInstance.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        newInstance.onRebind(intent);
    }

    @Override
    public void onLowMemory() {
        newInstance.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        newInstance.onTrimMemory(level);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        newInstance.onTaskRemoved(rootIntent);
    }
}
