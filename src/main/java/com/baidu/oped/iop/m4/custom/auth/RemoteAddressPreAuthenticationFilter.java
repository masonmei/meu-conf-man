package com.baidu.oped.iop.m4.custom.auth;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;

import com.baidu.oped.sia.boot.exception.access.IpForbiddenException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mason
 */
public class RemoteAddressPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteAddressPreAuthenticationFilter.class);

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private RemoteAddressCheckingService checkingService;

    public void setCheckingService(RemoteAddressCheckingService checkingService) {
        this.checkingService = checkingService;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        LOG.debug("RemoteAddressPreAuthenticatedFilter.getPreAuthenticatedPrincipal() called.");

        String username = obtainUsername(request);
        String remoteAddress = obtainRemoteAddress(request);

        if (checkingService.inBlackLabel(remoteAddress)) {
            LOG.debug("Client IP {} is in black list.", remoteAddress);
            throw new IpForbiddenException();
        }

        if (checkingService.inWhiteLabel(remoteAddress) && hasText(username)) {
            LOG.debug("Authenticated Remote Address {}, and username is {}", remoteAddress, username);
            return username;
        }

        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

    private String obtainRemoteAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }


    protected String obtainUsername(HttpServletRequest request) {
        String username = request.getParameter(usernameParameter);
        if (isEmpty(username)) {
            username = request.getHeader(usernameParameter);
        }
        return username;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

}
