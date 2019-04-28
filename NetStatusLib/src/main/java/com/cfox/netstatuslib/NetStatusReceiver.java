package com.cfox.netstatuslib;

public abstract class NetStatusReceiver {
    private static int sFlag = 0;
    private int mObjFlag = getFlag();
    protected abstract void netStatusChanged(NetType netType);

    public boolean onFilter(NetType netType) {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NetStatusReceiver)) {
            return false;
        }
        NetStatusReceiver receiver = (NetStatusReceiver) obj;
        return this.mObjFlag == receiver.mObjFlag;
    }

    private synchronized static int getFlag() {
        return ++sFlag;
    }
}
