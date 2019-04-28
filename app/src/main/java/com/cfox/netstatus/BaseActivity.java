package com.cfox.netstatus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cfox.netstatuslib.NetTypeObservable;
import com.cfox.netstatuslib.NetworkStatus;

public abstract class BaseActivity extends AppCompatActivity {

    private MyNetStatusReceiver mReceiver;

    abstract MyNetStatusReceiver getNetStatusReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkStatus.register(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
        mReceiver = getNetStatusReceiver();
        NetTypeObservable.register(mReceiver , mReceiver.getPriority());
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetTypeObservable.unregister(mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkStatus.unregister();
    }
}
