### 介绍
FxNetStatus 是一个监听网络状态变化的工具库.

### 功能
1. 动态注册
2. 在网络状态发生变化时,可以控制不同注册监听响应顺序
3. 可以对网络状态进行过滤和拦截处理.

### 使用

- 引入依赖

- 注册:
    ```
    NetworkStatus.register(context);
    ```

- 取消注册:
  ```
  NetworkStatus.unregister();
  ```

- 注册网络变化监听
    默认优先级, 优先级默认为0 ,最低
    ```
    private NetStatusReceiver receiver = new NetStatusReceiver() {
        @Override
        protected void netStatusChanged(NetType netType) {
            // 变化时,回调
        }

        @Override
        public boolean onFilter(NetType netType) {
            // 过来方法,如果拦截 返回false ,继续执行后面的监听返回true
            return super.onFilter(netType);
        }
    };
    NetTypeObservable.register(receiver);
    ```
   **带有优先级注册, 优先级越高优先执行.**
    ```
    private NetStatusReceiver receiver = new NetStatusReceiver() {
        @Override
        protected void netStatusChanged(NetType netType) {
            // 网络变化时,回调
        }

        @Override
        public boolean onFilter(NetType netType) {
            // 过滤和拦截方法,如果拦截 返回false ,继续执行后面的监听返回true
            return super.onFilter(netType);
        }
    };
    NetTypeObservable.register(receiver, 20);
    ```
    **说明:**
    1. 注册时可以设置Receiver的优先级,优先级高的先执行,相同优先级的,后注册的先被运行.
    2. 执行完`netStatusChanged`会执行到`onFilter`中,在`onFilter`中可以对网络状态进行干预和拦截.
    3. 使用`NetTypeObservable.register()`注册网络状态改变监听,可以注册多个,不使用的要使用`NetTypeObservable.unregister(receiver);` 取消注册.

- 取消网络变化监听注册
    ```
    NetTypeObservable.unregister(receiver);
    ```

- 单回调网络变化监听:
    无论注册多少次,在网络发生变化时,只有最后一次设置的监听对象会被回调.
    ```
    NetTypeObservable.setNetworkListener(new NetworkListener() {
        @Override
        public void netChange(NetType netType) {
            // 网络变化时
        }
    });
    ```

