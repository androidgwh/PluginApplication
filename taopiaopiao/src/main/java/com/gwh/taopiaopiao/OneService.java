package com.gwh.taopiaopiao;

import android.app.Service;
import android.util.Log;

/**
 * Created by Guo Wenhui
 * 2020/10/16
 **/
public class OneService extends BaseService {
    int i;
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    Log.d("OneService", ""+(i++));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
