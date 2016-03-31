package com.baidu.oped.iop.m4.mvc.dto.common;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;

/**
 * Data translate object of ReceiverCallback.
 *
 * @author mason
 */
public class ReceiverCallbackDto extends AbstractProductLayerAuditableDto<ReceiverCallback> {

    private String callbackUri;
    private boolean needCredential;
    private String username;
    private String password;

    @Override
    public ReceiverCallbackDto fromModel(ReceiverCallback receiverCallback) {
        super.initProcess(receiverCallback);
        this.callbackUri = receiverCallback.getCallbackUri();
        this.needCredential = receiverCallback.isNeedCredential();
        this.username = receiverCallback.getUsername();
        this.password = receiverCallback.getPassword();
        return this;
    }

    @Override
    public void toModel(ReceiverCallback receiverCallback) {
        receiverCallback.setName(getName());
        receiverCallback.setCallbackUri(callbackUri);
        receiverCallback.setNeedCredential(needCredential);
        receiverCallback.setUsername(username);
        receiverCallback.setPassword(password);
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNeedCredential() {
        return needCredential;
    }

    public void setNeedCredential(boolean needCredential) {
        this.needCredential = needCredential;
    }
}
