package com.gwh.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
        View viewById = findViewById(R.id.tv_go);
        Log.d("MainActivity", viewById + " ");
        viewById .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mActivity, SecondActivity.class));
                        startService(new Intent(mActivity,OneService.class));
                    }
                });
    }
}
