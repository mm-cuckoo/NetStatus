package com.cfox.netstatus.netutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class NetworkBroadcast extends BroadcastReceiver {

    private static NetworkBroadcast mNetworkBroadcast;
    private static final String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private NetworkStatusManager mNetworkStatusManager;

    private NetworkBroadcast() {
        mNetworkStatusManager = new NetworkStatusManager();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) return;
        if (intent.getAction().equals(NET_CHANGE_ACTION)){
            mNetworkStatusManager.onReceive(context, intent);
        }
    }


    public static synchronized void register(Context context) {
        if (mNetworkBroadcast != null) return;
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkBroadcast.NET_CHANGE_ACTION);
        mNetworkBroadcast = new NetworkBroadcast();
        context.registerReceiver(mNetworkBroadcast,filter);
    }


    public static synchronized void unRegister(Context context) {
        if (mNetworkBroadcast != null) {
            context.unregisterReceiver(mNetworkBroadcast);
            mNetworkBroadcast.mNetworkStatusManager.release();
            mNetworkBroadcast = null;
        }
    }

}
