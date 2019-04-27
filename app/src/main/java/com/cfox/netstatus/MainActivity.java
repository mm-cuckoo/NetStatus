package com.cfox.netstatus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cfox.netstatus.netutils.NetWorkStatus;
import com.cfox.netstatus.netutils.NetStatusReceiver;
import com.cfox.netstatus.netutils.NetType;
import com.cfox.netstatus.netutils.NetTypeObservable;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetTypeObservable.register(receiver1);
        NetTypeObservable.register(receiver2, 20);
        NetTypeObservable.register(receiver3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetWorkStatus.unregister();
    }

    public void unregisterReceiver(View view) {
        NetTypeObservable.unregister(receiver2);
    }

    private NetStatusReceiver receiver1 = new NetStatusReceiver() {
        @Override
        protected void netStatusChanged(NetType netType) {
            Log.d(TAG, "netStatusChanged: 11111" + netType);
        }

        @Override
        public boolean onFilter(NetType netType) {
            Log.d(TAG, "onFilter: 111111" + netType);
            return super.onFilter(netType);
        }
    };

    private NetStatusReceiver receiver2 = new NetStatusReceiver() {
        @Override
        protected void netStatusChanged(NetType netType) {
            Log.d(TAG, "netStatusChanged: 2222" + netType);
        }

        @Override
        public boolean onFilter(NetType netType) {
            Log.d(TAG, "onFilter: 22222" + netType);
            return super.onFilter(netType);
        }
    };


    private NetStatusReceiver receiver3 = new NetStatusReceiver() {
        @Override
        protected void netStatusChanged(NetType netType) {
            Log.d(TAG, "netStatusChanged: 333333" + netType);
        }

        @Override
        public boolean onFilter(NetType netType) {
            Log.d(TAG, "onFilter: 33333" + netType);
            return super.onFilter(netType);
        }
    };

    @Override
    MyNetStatusReceiver getNetStatusReceiver() {
        MyNetStatusReceiver receiver = new MyNetStatusReceiver() {
            @Override
            protected void netStatusChanged(NetType netType) {

            }
        };
        receiver.setPriority(30);
        return receiver;
    }
}
