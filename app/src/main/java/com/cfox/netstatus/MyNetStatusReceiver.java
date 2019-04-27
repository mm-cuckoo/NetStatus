package com.cfox.netstatus;

import com.cfox.netstatus.netutils.NetStatusReceiver;

public abstract class MyNetStatusReceiver extends NetStatusReceiver {
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
