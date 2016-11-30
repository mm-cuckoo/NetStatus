package com.cfox.netstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cfox.netstatus.netutils.NetObserver;
import com.cfox.netstatus.netutils.NetStatusReceiver;
import com.cfox.netstatus.netutils.NetType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetObserver.register(new NetStatusReceiver() {
            @Override
            public void netStatusChanged(NetType netType) {
                Toast.makeText(MainActivity.this,"net type" + netType ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
