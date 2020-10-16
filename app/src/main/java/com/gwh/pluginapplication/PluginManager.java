package com.gwh.pluginapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public class PluginManager {
    private static PluginManager mPluginManager;
    private Context mContext;
    private DexClassLoader mDexClassLoader;
    private Resources mResources;
    private PackageInfo packageArchiveInfo;

    public PackageInfo getPackageArchiveInfo() {
        return packageArchiveInfo;
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }

    private PluginManager() {
    }

    public static PluginManager getInstance(){
        if(null==mPluginManager){
            synchronized (PluginManager.class){
                if(null==mPluginManager){
                    mPluginManager = new PluginManager();
                }
            }
        }
        return mPluginManager;
    }
    public void loadPlugin(Context context){
        this.mContext = context;
        InputStream open = null;
        try {
           open= context.getAssets().open("plugin.apk");
            PackageManager packageManager = context.getPackageManager();
            String fileName = "pluginb.apk";
            File pluginbFile = new File(context.getCacheDir(), fileName);
            String filePath = pluginbFile.getAbsolutePath();
            if(pluginbFile.exists()){
                pluginbFile.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(pluginbFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len =open .read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            File f = new File(filePath);
            if (f.exists()) {
                Toast.makeText(context, "dex overwrite", Toast.LENGTH_SHORT).show();
            }
            if(null!=packageManager){
                 packageArchiveInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
                File dexFile = context.getDir("dex", Context.MODE_PRIVATE);
                mDexClassLoader = new DexClassLoader(filePath, dexFile.getAbsolutePath(), null, mContext.getClassLoader());
                AssetManager assetManager = null;
                try {
                     assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                    addAssetPath.invoke(assetManager, filePath);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                mResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=open){
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
