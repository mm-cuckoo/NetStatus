package com.cfox.netstatus;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cfox.netstatus.netutils.NetStatusBroadcast;

public class MainActivity extends AppCompatActivity {

    private NetStatusBroadcast mBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(NetStatusBroadcast.NET_CHANGE_ACTION);
        mBroadcast = new NetStatusBroadcast();
        this.registerReceiver(mBroadcast,filter);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        NetObserver.register(new NetStatusReceiver() {
//            @Override
//            public void netStatusChanged(NetType netType) {
//                Toast.makeText(MainActivity.this,"main page net type" + netType ,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcast);
    }

    public void openOtherActivity(View view) {
        Intent intent = new Intent(this,OtherActivity.class);
        startActivity(intent);
    }
}
