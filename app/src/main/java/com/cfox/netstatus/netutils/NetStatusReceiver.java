package com.cfox.netstatus.netutils;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : NetStatus
 * <br/>PACKAGE_NAME : com.cfox.netstatus.netutils
 * <br/>AUTHOR : CFOX
 * <br/>MSG :
 * <br/>************************************************
 */

public interface NetStatusReceiver {

    public void netStatusChanged(NetType netType);
}
