package com.cfox.netstatus;

import android.app.Application;

import com.cfox.netstatus.netutils.NetWorkStatus;

public class App extends Application {

    public void onCreate() {
        super.onCreate();
        NetWorkStatus.register(this);
    }
}
