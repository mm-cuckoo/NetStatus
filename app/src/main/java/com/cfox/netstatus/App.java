package com.cfox.netstatus;

import android.app.Application;
import android.util.Log;

import com.cfox.netstatus.netutils.NetObserver;
import com.cfox.netstatus.netutils.NetStatusReceiver;
import com.cfox.netstatus.netutils.NetType;
import com.squareup.leakcanary.LeakCanary;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : NetStatus
 * <br/>PACKAGE_NAME : com.cfox.netstatus
 * <br/>AUTHOR : CFOX
 * <br/>MSG :
 * <br/>************************************************
 */

public class App extends Application {
    private static final String TAG = "App";

    public static NetType sNetType;//设置全局网络变量

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        NetObserver.registerlGlobalReceiver(new NetStatusReceiver() {
            @Override
            public void netStatusChanged(NetType netType) {
                Log.i(TAG,"Global net " + netType);
                sNetType = netType;
            }
        });

    }
}
