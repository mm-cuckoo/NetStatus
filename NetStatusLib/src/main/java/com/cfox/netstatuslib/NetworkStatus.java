package com.cfox.netstatuslib;

import android.content.Context;

public class NetworkStatus {
    private static NetworkStatus sNetworkStatus;
    private Context mContext;

    private Context getContext() {
        return mContext;
    }

    private NetworkStatus(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private static NetworkStatus init(Context context) {
        if (sNetworkStatus == null) {
            synchronized (NetworkStatus.class) {
                if (sNetworkStatus == null) {
                    sNetworkStatus = new NetworkStatus(context);
                }
            }
        }
        return sNetworkStatus;
    }

    public static void register(Context context) {
        NetworkBroadcast.register(init(context).getContext());
    }

    public static void unregister() {
        if (sNetworkStatus == null) return;
        NetworkBroadcast.unRegister(sNetworkStatus.getContext());
    }

    public static NetType getNetType (Context context){
        return NetTools.netType(context);
    }
}
