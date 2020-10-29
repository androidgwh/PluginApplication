package com.gwh.pluginapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().loadPlugin(this);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.putExtra("className", PluginManager.getInstance().getPackageArchiveInfo().activities[0].name);
                ComponentName componentName =
                    new ComponentName("com.gwh.pluginapplication", "com.gwh.pluginapplication.ProxyActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
    }
}