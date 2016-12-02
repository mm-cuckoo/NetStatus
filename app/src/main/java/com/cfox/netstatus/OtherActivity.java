package com.cfox.netstatus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cfox.netstatus.netutils.NetObserver;
import com.cfox.netstatus.netutils.NetStatusReceiver;
import com.cfox.netstatus.netutils.NetType;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : NetStatus
 * <br/>PACKAGE_NAME : com.cfox.netstatus
 * <br/>AUTHOR : CFOX
 * <br/>MSG :
 * <br/>************************************************
 */

public class OtherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetObserver.register(new NetStatusReceiver() {
            @Override
            public void netStatusChanged(NetType netType) {
                Toast.makeText(OtherActivity.this,"OtherActivity page net type" + netType ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void exitActivity(View view) {
        finish();
    }
}
