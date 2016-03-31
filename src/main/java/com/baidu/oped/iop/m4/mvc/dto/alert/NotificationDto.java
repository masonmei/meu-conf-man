package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import javax.validation.constraints.NotNull;

/**
 * @author mason
 */
public class NotificationDto implements Dto<Notification>{

    @NotNull
    private Long receiverId;
    @NotNull
    private String type;

    @Override
    public NotificationDto fromModel(Notification model) {
        this.type = model.getType();
        this.receiverId = model.getReceiverId();
        return this;
    }

    @Override
    public void toModel(Notification model) {
        model.setType(type);
        model.setReceiverId(receiverId);
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
