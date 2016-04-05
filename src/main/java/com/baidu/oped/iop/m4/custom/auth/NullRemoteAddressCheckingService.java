package com.baidu.oped.iop.m4.custom.auth;

/**
 * Remote Address Checking Service with no action.
 *
 * @author mason
 */
public class NullRemoteAddressCheckingService implements RemoteAddressCheckingService {
    @Override
    public boolean inWhiteLabel(String remoteAddr) {
        if (remoteAddr.equals("127.0.0.1") || remoteAddr.equals("0:0:0:0:0:0:0:1")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean inBlackLabel(String remoteAddr) {
        if (remoteAddr.equals("192.168.1.103") || remoteAddr.equals("0:0:0:0:0:0:0:1")) {
            return true;
        }
        return false;
    }
}
