package com.gwh.pluginapplication;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * Created by Guo Wenhui 2020/10/29
 **/
public class HookHandleActivity extends Activity {
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hook_handle);
  }
}
