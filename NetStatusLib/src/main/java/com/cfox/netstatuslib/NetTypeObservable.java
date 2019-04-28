package com.cfox.netstatuslib;

public class NetTypeObservable {
    private final static int DEFAULT_PRIORITY = 0;
    private static NetTypeObservable sNetTypeObservable;
    private NetTypeMessage mHead;
    private NetworkListener mListener;

    public void setListener(NetworkListener mListener) {
        this.mListener = mListener;
    }

    public static void setNetworkListener(NetworkListener listener) {
        NetTypeObservable.getInstance().setListener(listener);
    }

    private NetTypeObservable() {
        mHead = new NetTypeMessage(DEFAULT_PRIORITY);
    }

    static NetTypeObservable getInstance() {
        if (sNetTypeObservable == null) {
            synchronized (NetTypeObservable.class) {
                if (sNetTypeObservable == null) {
                    sNetTypeObservable = new NetTypeObservable();
                }
            }
        }
        return sNetTypeObservable;
    }

    private NetTypeMessage createNetMessage(int priority) {
        return new NetTypeMessage(priority);
    }

    private void registerReceiver(NetStatusReceiver receiver, int priority) {
        NetTypeMessage netTypeMessage = createNetMessage(priority);
        netTypeMessage.setReceiver(receiver);
        mHead.setNextMsg(netTypeMessage);
    }

    private void unregisterReceiver(NetStatusReceiver receiver) {
        mHead.remove(receiver);
    }

    void dispatchMessage(NetType type) {
        if (this.mListener != null) {
            this.mListener.netChange(type);
        }
        mHead.runMessage(type, true);
    }

    public static void register(NetStatusReceiver receiver) {
        NetTypeObservable.getInstance().registerReceiver(receiver, DEFAULT_PRIORITY);
    }

    public static void register(NetStatusReceiver receiver, int priority) {
        NetTypeObservable.getInstance().registerReceiver(receiver, priority);
    }

    public static void unregister(NetStatusReceiver receiver) {
        NetTypeObservable.getInstance().unregisterReceiver(receiver);
    }

}
