package com.baidu.oped.iop.m4.custom.auth;

/**
 * Record login attempts with no actions.
 *
 * @author mason
 */
public class NullRecordLoginAttemptService implements RecordLoginAttemptService {

    @Override
    public void loginSuccess(String key) {
    }

    @Override
    public void loginFailed(String key) {

    }

    @Override
    public boolean isBlocked(String key) {
        return false;
    }
}
