package com.baidu.oped.iop.m4.domain.entity.alert;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mason
 */
@Embeddable
public class Notification {
    private static final long serialVersionUID = -8585661901100359263L;

    @Column(name = "notification_type")
    private String type;

    private Long receiverId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
