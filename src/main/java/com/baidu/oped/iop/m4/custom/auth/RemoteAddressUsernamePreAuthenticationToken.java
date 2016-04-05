package com.baidu.oped.iop.m4.custom.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

/**
 * Extend UsernamePasswordAuthenticationToken with remote address added.
 *
 * @author mason
 */
public class RemoteAddressUsernamePreAuthenticationToken extends PreAuthenticatedAuthenticationToken {
    private static final long serialVersionUID = -2648091609635551716L;

    private final String remoteAddress;

    public RemoteAddressUsernamePreAuthenticationToken(String remoteAddress, Object principal,
            Object credentials) {
        super(principal, credentials);
        this.remoteAddress = remoteAddress;
    }

    public RemoteAddressUsernamePreAuthenticationToken(String remoteAddress, Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.remoteAddress = remoteAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }
}
