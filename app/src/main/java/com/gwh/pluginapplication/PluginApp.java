package com.gwh.pluginapplication;

import android.app.Application;
import com.gwh.pluginapplication.hook.HookUtils;

/**
 * Created by Guo Wenhui 2020/10/29
 **/
public class PluginApp  extends Application {

  @Override public void onCreate() {
    super.onCreate();
    HookUtils hookUtils = new HookUtils();
    hookUtils.hookStartActivity(this);
    hookUtils.hookMh();
  }
}
