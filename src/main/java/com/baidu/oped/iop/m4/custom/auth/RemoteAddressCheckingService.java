package com.baidu.oped.iop.m4.custom.auth;

/**
 * @author mason
 */
public interface RemoteAddressCheckingService {

    /**
     * Check if the remoteAddr is in white label list.
     *
     * @param remoteAddr user ip address
     * @return in white or not
     */
    boolean inWhiteLabel(String remoteAddr);

    /**
     * Check if the remoteAddr is in black label list.
     *
     * @param remoteAddr user ip address
     * @return in black or not
     */
    boolean inBlackLabel(String remoteAddr);

}
