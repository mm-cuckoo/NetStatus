package com.cfox.netstatuslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetTools {
    private static ConnectivityManager getConnectivityManager(Context context){
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isConnected(Context context){
        if (context == null){
            throw new RuntimeException(NetTools.class.getSimpleName() + " # Content  is null");
        }
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    static NetType netType(Context context){
        if (context == null){
            throw new RuntimeException(NetTools.class.getSimpleName() + " # Content  is null");
        }

        ConnectivityManager connectivityManager = getConnectivityManager(context);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) return NetType.NET_NO;
        int type = networkInfo.getType();
        if (type == ConnectivityManager.TYPE_MOBILE) return NetType.NET_3G_4G;
        if (type == ConnectivityManager.TYPE_WIFI) return NetType.NET_WIFI;
        return NetType.NET_NO;
    }
}
