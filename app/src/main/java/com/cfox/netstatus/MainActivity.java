package com.cfox.netstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cfox.netstatus.netutils.NetObserver;
import com.cfox.netstatus.netutils.NetStatusReceiver;
import com.cfox.netstatus.netutils.NetType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        NetObserver.register(new NetStatusReceiver() {
            @Override
            public void netStatusChanged(NetType netType) {
                Toast.makeText(MainActivity.this,"main page net type" + netType ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openOtherActivity(View view) {
        Intent intent = new Intent(this,OtherActivity.class);
        startActivity(intent);
    }
}
