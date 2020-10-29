package com.gwh.pluginapplication.hook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.gwh.pluginapplication.HookProxyActivity;
import com.gwh.pluginapplication.ProxyActivity;
import com.gwh.pluginapplication.kotlin.One;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Guo Wenhui 2020/10/29
 **/
public class HookUtils {
  private Context context;

  public void hookMh() {
    try {
      Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
      Field sCurrentActivityThreadField =
          activityThreadClass.getDeclaredField("sCurrentActivityThread");
      sCurrentActivityThreadField.setAccessible(true);
      //获取ActivityThread中持有的自己的对象，是static
      Object activityThreadObject = sCurrentActivityThreadField.get(null);

      //找ActivityThread中的mh
      Field mHField = activityThreadClass.getDeclaredField("mH");
      mHField.setAccessible(true);

      Handler handler = (Handler) mHField.get(activityThreadObject);
      Field mCallbackField = Handler.class.getDeclaredField("mCallback");
      mCallbackField.setAccessible(true);

      mCallbackField.set(handler, new MyHandler(handler));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  class MyHandler implements Handler.Callback {
    Handler mh;

    public MyHandler(Handler mh) {
      this.mh = mh;
    }

    @Override public boolean handleMessage(@NonNull Message msg) {
      Log.d("hook handler", msg.what + "");
      Object obj = msg.obj;
      Log.d("handlerMsg", obj.getClass().getName());
      //反射activityThread的mActivities
      //根据ibinder获取ActivityClientRecord，获取其中的intent
     if(msg.what==149) {
       try {
         if (obj instanceof IBinder) {
           Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
           Field sCurrentActivityThreadField =
               activityThreadClass.getDeclaredField("sCurrentActivityThread");
           sCurrentActivityThreadField.setAccessible(true);
           //获取ActivityThread中持有的自己的对象，是static
           Object activityThreadObject = sCurrentActivityThreadField.get(null);
           Field mActivitiesField = activityThreadClass.getDeclaredField("mActivities");
           mActivitiesField.setAccessible(true);

           ArrayMap arrayMap = (ArrayMap) mActivitiesField.get(activityThreadObject);
           Object activityRecord = arrayMap.get(obj);
           Field intentField = activityRecord.getClass().getDeclaredField("intent");
           intentField.setAccessible(true);
           Intent realIntent = (Intent) intentField.get(activityRecord);
           Intent oldIntent = realIntent.getParcelableExtra("oldIntent");
           Log.d("aaaaaaaa", oldIntent.getComponent().getClassName());
           Log.d("aaaaaaaa", realIntent.getComponent().getClassName());

           //ComponentName componentName = new ComponentName(context, ProxyActivity.class);
           //realIntent.putExtra("extraIntent", oldIntent.getComponent().getClassName());
           realIntent.setComponent(oldIntent.getComponent());

           Log.d("aaaaaaaa", realIntent.getComponent().getClassName());
         }
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
      mh.handleMessage(msg);
      return true;
    }
  }

  public void hookStartActivity(Context context) {
    this.context = context;
    try {
      //找到hook点即startActivity，
      Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
      //IActivityManagerSingleton在ActivityThread中是static final 获取到得是系统的
      Field iActivityManagerSingletonField =
          activityManagerClass.getDeclaredField("IActivityManagerSingleton");
      //静态变量可以获取到系统的值
      iActivityManagerSingletonField.setAccessible(true);
      Object defaultObject = iActivityManagerSingletonField.get(null);

      Class<?> singletonClass = Class.forName("android.util.Singleton");
      Field mInstanceField = singletonClass.getDeclaredField("mInstance");
      mInstanceField.setAccessible(true);

      Object iSctivityManagerObject = mInstanceField.get(defaultObject);

      Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");
      StartActivity startActivity = new StartActivity(iSctivityManagerObject);
      Object myIactivityManager =
          Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
              new Class[] {iActivityManagerClass}, startActivity);
      //将系统的IActivityManager替换成自己通过动态代理实现的
      mInstanceField.set(defaultObject, myIactivityManager);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  class StartActivity implements InvocationHandler {
    private Object iActivityManagerObject;

    public StartActivity(Object iActivityManagerObject) {
      this.iActivityManagerObject = iActivityManagerObject;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Log.d("hookUtils", "hook success  methodName: " + method.getName());
      if ("startActivity".equals(method.getName())) {
        Log.d("hookUtils", "startActivity");
        //寻找传入的intent，还原它
        Intent intent = null;
        int index = 0;
        for (int i = 0; i < args.length; i++) {
          Object arg = args[i];
          if (arg instanceof Intent) {
            intent = (Intent) arg;
            index = i;
          }
        }

        Intent newIntent = new Intent();
        ComponentName componentName = new ComponentName(context, HookProxyActivity.class);
        newIntent.setComponent(componentName);
        newIntent.putExtra("oldIntent", intent);
        args[index] = newIntent;
      }
      return method.invoke(iActivityManagerObject, args);
    }
  }
}
