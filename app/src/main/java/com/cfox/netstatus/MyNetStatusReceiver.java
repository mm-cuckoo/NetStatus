package com.cfox.netstatus;


import com.cfox.netstatuslib.NetStatusReceiver;

public abstract class MyNetStatusReceiver extends NetStatusReceiver {
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
