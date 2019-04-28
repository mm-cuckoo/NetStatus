package com.cfox.netstatuslib;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

class NetworkStatusManager {
    /** 有些时候网络改变时会调用两次,使用这个变了记录上一次改变时的网络状态,如果和上一次相同,则不向下通知 */
    private static NetType sNetTypeCache = null;

    private MessageHandle mThreadHandler;

    private static class MessageHandle extends Handler {
        private WeakReference<NetworkStatusManager> mManagerWeakReference;

        MessageHandle(NetworkStatusManager manager) {
            mManagerWeakReference = new WeakReference<>(manager);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mManagerWeakReference.get() != null) {
                NetTypeObservable.getInstance().dispatchMessage(sNetTypeCache);
            }
        }
    }

    NetworkStatusManager() {
        mThreadHandler = new MessageHandle(this);
    }

    void onReceive (Context context, Intent intent) {
        NetType netType = NetTools.netType(context);

        if (netType == sNetTypeCache) return;

        if (sNetTypeCache != null && mThreadHandler.hasMessages(sNetTypeCache.ordinal())) {
            mThreadHandler.removeMessages(sNetTypeCache.ordinal());
        }

        sNetTypeCache = netType;
        if (sNetTypeCache == NetType.NET_NO ){
            mThreadHandler.sendEmptyMessageDelayed(sNetTypeCache.ordinal(), 1000);
        } else {
            mThreadHandler.sendEmptyMessage(sNetTypeCache.ordinal());
        }
    }

    void release() {
        mThreadHandler.removeCallbacks(null);
        mThreadHandler = null;
    }
}
