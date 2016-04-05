package com.baidu.oped.iop.m4.custom.auth;

/**
 * Record Login attempts Service.
 *
 * @author mason
 */
public interface RecordLoginAttemptService {

    /**
     * Record user login success.
     *
     * @param key user identity
     */
    void loginSuccess(String key);

    /**
     * Record user login failed.
     *
     * @param key user identity
     */
    void loginFailed(String key);

    /**
     * Check if the user is blocked.
     *
     * @param key user identity
     * @return blocked or not
     */
    boolean isBlocked(String key);
}
