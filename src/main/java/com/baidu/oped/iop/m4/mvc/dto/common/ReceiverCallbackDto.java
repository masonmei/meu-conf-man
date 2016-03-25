package com.baidu.oped.iop.m4.mvc.dto.common;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;

import java.util.Date;

/**
 * Data translate object of ReceiverCallback.
 *
 * @author mason
 */
public class ReceiverCallbackDto extends AbstractAuditableDto<ReceiverCallback> {

    private Long id;
    private String productName;
    private String appName;
    private String name;
    private String callbackUri;
    private String username;
    private String password;

    @Override
    public void fromModel(ReceiverCallback receiverCallback) {
        initProcess(receiverCallback);
        this.id = receiverCallback.getId();
        this.productName = receiverCallback.getProductName();
        this.name = receiverCallback.getName();
        this.callbackUri = receiverCallback.getCallbackUri();
        this.username = receiverCallback.getUsername();
        this.password = receiverCallback.getPassword();
    }

    @Override
    public void toModel(ReceiverCallback receiverCallback) {
        receiverCallback.setProductName(productName);
        receiverCallback.setName(name);
        receiverCallback.setCallbackUri(callbackUri);
        receiverCallback.setUsername(username);
        receiverCallback.setPassword(password);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
