package com.gwh.pluginapplication;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * Created by Guo Wenhui 2020/10/29
 **/
public class HookProxyActivity extends Activity {
  private static final String TAG = HookProxyActivity.class.getSimpleName();
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hook);
  }
}
