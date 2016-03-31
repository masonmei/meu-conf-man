package com.baidu.oped.iop.m4.domain.entity.common;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "product_receiver_callback_unique_index", columnList = "`product_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`")})
public class ReceiverCallback extends AbstractReceiver<Long> implements Receiver {
    private static final long serialVersionUID = 6061905584740599703L;

    private String callbackUri;
    private boolean needCredential = true;
    private String username;
    private String password;

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNeedCredential() {
        return needCredential;
    }

    public void setNeedCredential(boolean needCredential) {
        this.needCredential = needCredential;
    }
}
