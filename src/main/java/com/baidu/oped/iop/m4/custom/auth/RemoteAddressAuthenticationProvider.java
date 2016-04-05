package com.baidu.oped.iop.m4.custom.auth;

import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author mason
 */
public class RemoteAddressAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteAddressAuthenticationProvider.class);

    private RemoteAddressCheckingService checkingService;

    private AuthenticationProvider delegate;

    public void setCheckingService(RemoteAddressCheckingService checkingService) {
        this.checkingService = checkingService;
    }

    public void setDelegate(AuthenticationProvider delegate) {
        this.delegate = delegate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();
        String sourceIpAddress = wad.getRemoteAddress();

        LOG.debug("Find request from {}", sourceIpAddress);

        if (checkingService.inWhiteLabel(sourceIpAddress)) {
            LOG.debug("Remote Address {} in white label", sourceIpAddress);
            String principal = authentication.getPrincipal().toString();
            RemoteAddressUsernamePreAuthenticationToken token =
                    new RemoteAddressUsernamePreAuthenticationToken(sourceIpAddress, principal, "N/A");
            token.setDetails(wad);
            return token;
        }

        if (delegate != null) {
            return delegate.authenticate(authentication);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
