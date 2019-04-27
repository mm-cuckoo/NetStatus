package com.cfox.netstatus;

import android.support.v7.app.AppCompatActivity;
import com.cfox.netstatus.netutils.NetTypeObservable;

public abstract class BaseActivity extends AppCompatActivity {

    private MyNetStatusReceiver mReceiver;

    abstract MyNetStatusReceiver getNetStatusReceiver();

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
}
