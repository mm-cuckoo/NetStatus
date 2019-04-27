package com.cfox.netstatus.netutils;

class NetTypeMessage {
    private int mPriority;
    private NetStatusReceiver mReceiver;
    private NetTypeMessage next;

    NetTypeMessage(int priority) {
        this.mPriority = priority;
    }

    void runMessage(NetType type, boolean jumpReceiver) {

        if (jumpReceiver && this.next != null) {
            this.next.runMessage(type, false);
            return;
        }

        if (mReceiver == null) return;
        mReceiver.netStatusChanged(type);
        if (mReceiver.onFilter(type) && this.next != null) {
            this.next.runMessage(type, false);
        }
    }

    void setReceiver(NetStatusReceiver receiver) {
        this.mReceiver = receiver;
    }

    private int getPriority() {
        return this.mPriority;
    }

    void setNextMsg(NetTypeMessage netTypeMessage) {
        if (this.next == null) {
            this.next = netTypeMessage;
            return;
        }
        if (netTypeMessage.getPriority() >= this.next.getPriority()) {
            netTypeMessage.next = this.next;
            this.next = netTypeMessage;
            return;
        }
        this.next.setNextMsg(netTypeMessage);
    }

    void remove(NetStatusReceiver receiver) {
        if (this.next == null) return;
        if (this.next.mReceiver.equals(receiver)) {
            NetTypeMessage netTypeMessage = this.next;
            this.next = this.next.next;
            netTypeMessage.release();
        } else {
            this.next.remove(receiver);
        }

    }
    private void release() {
        this.next = null;
        this.mReceiver = null;
    }
}
