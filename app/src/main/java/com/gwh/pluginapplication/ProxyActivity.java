package com.gwh.pluginapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.gwh.pluginstandard.InterfaceActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import androidx.annotation.Nullable;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public class ProxyActivity extends Activity {
    private static final String TAG = ProxyActivity.class.getSimpleName();
    private String className;
    private InterfaceActivity interfacaActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        className = getIntent().getStringExtra("className");
        try {
            Class<?> aClass = getClassLoader().loadClass(className);
            Constructor<?> constructor = aClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            interfacaActivity = (InterfaceActivity) instance;
            interfacaActivity.attach(this);
            Bundle bundle = new Bundle();
            interfacaActivity.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        Log.d(TAG, "startActivity");
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className);
        super.startActivity(intent1);
    }

    @Override
    public ComponentName startService(Intent service) {
        String serviceName = service.getStringExtra("className");
        Intent intent = new Intent(this,ProxyService.class);
        intent.putExtra("className", serviceName);
        return super.startService(intent);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        interfacaActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        interfacaActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        interfacaActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        interfacaActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interfacaActivity.onDestroy();
    }
}
