## Tools 使用方法：

1. 注册监听网络动态广播：
- 静态注册
```
<receiver android:name=".netutils.NetStatusBroadcast">
    <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
    </intent-filter>
</receiver>
```
- 动态注册
```
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetStatusBroadcast.NET_CHANGE_ACTION);
        mBroadcast = new NetStatusBroadcast();
        this.registerReceiver(mBroadcast,filter);
```
###### 推荐使用动态注册，在app 的主 activity 中 OnCreate 方法添加注册，在OnDestroy 方法中取消注册。
如下：
```
    private NetStatusBroadcast mBroadcast;

    ......


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(NetStatusBroadcast.NET_CHANGE_ACTION);
        mBroadcast = new NetStatusBroadcast();
        this.registerReceiver(mBroadcast,filter);

    }
```

```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcast);
    }
```

2. 添加网络变化监听：
在需要的添加监听的地方调用 NetObserver.register() 方法注册网络变化监听，在Activity 或 Fragment 中建议在 onResume() 方法注册，如下方式：
```

    private NetStatusReceiver receiver = new NetStatusReceiver() {
        @Override
        public void netStatusChanged(NetType netType) {
            Toast.makeText(OtherActivity.this, "OtherActivity page net type" + netType ,Toast.LENGTH_SHORT).show();

        }
    };


        @Override
    protected void onResume() {
        super.onResume();
        NetObserver.register(receiver);
    }
```

3. 销毁时取消注册监听：
在 Activity 或 Fragment 销毁时，即在 onDestroy() 方法中取消注册，如下方式：
```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetObserver.unregister();
    }
```

以上为 Tools 的使用，代码不多，也很简单，这里只是提供一个思路，可以根据自己项目修改，或是重新写，如果有什么地方有问题，请指出，共同学习，共同进步。
