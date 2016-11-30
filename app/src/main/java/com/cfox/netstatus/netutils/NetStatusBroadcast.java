package com.cfox.netstatus.netutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : NetStatus
 * <br/>PACKAGE_NAME : com.cfox.netstatus.netutils
 * <br/>AUTHOR : CFOX
 * <br/>MSG :
 * <br/>************************************************
 */

public class NetStatusBroadcast extends BroadcastReceiver {
    private static final String TAG = "NetStatusBroadcast";
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    /** 有些时候网络改变时会调用两次,使用这个变了记录上一次改变时的网络状态,如果和上一次相同,则不向下通知 */
    private static NetType sNetTypeCache = NetType.NO_NET;
    @Override
    public void onReceive(Context context, Intent intent) {
        NetTools.init(context);
        Log.i(TAG,"sign");
        if (NetTools.netType() == sNetTypeCache) return;
        Log.i(TAG,"is net Changed");
        if (intent.getAction().equals(NET_CHANGE_ACTION)){
            List<NetStatusReceiver> netStatusReceiverList = NetObserver.getNetStatusReceivers();
            for (NetStatusReceiver netStatusReceiver : netStatusReceiverList) {
                netStatusReceiver.netStatusChanged(sNetTypeCache = NetTools.netType());
            }
        }
    }
}
