package com.cfox.netstatus.netutils;

import android.content.Context;

public class NetWorkStatus {
    private static NetWorkStatus sNetWorkStatus;
    private Context mContext;

    private Context getContext() {
        return mContext;
    }

    private NetWorkStatus(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private static NetWorkStatus init(Context context) {
        if (sNetWorkStatus == null) {
            synchronized (NetWorkStatus.class) {
                if (sNetWorkStatus == null) {
                    sNetWorkStatus = new NetWorkStatus(context);
                }
            }
        }
        return sNetWorkStatus;
    }

    public static void register(Context context) {
        NetworkBroadcast.register(init(context).getContext());
    }

    public static void unregister() {
        if (sNetWorkStatus == null) return;
        NetworkBroadcast.unRegister(sNetWorkStatus.getContext());
    }

    public static NetType getNetType (Context context){
        return NetTools.netType(context);
    }
}
