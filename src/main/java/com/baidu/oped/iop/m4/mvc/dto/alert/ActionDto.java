package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.ActionConfig;
import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractProductLayerAuditableDto;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Data translate object of Action.
 *
 * @author mason
 */
public class ActionDto extends AbstractProductLayerAuditableDto<Action> {

    private static final long serialVersionUID = -1613536516204589930L;

    private Set<NotificationDto> notifications;
    private ActionConfigDto config;

    @Override
    public void toModel(Action action) {
        action.setName(getName());
        ActionConfig config = new ActionConfig();
        this.config.toModel(config);
        action.setConfig(config);

        action.setNotifications(notifications.stream()
                .map(notificationDto -> {
                    Notification notification = new Notification();
                    notificationDto.toModel(notification);
                    return notification;
                })
                .collect(Collectors.toSet()));
    }

    @Override
    public ActionDto fromModel(Action action) {
        super.initProcess(action);
        this.config = new ActionConfigDto().fromModel(action.getConfig());
        this.notifications = action.getNotifications()
                .stream()
                .map(notification -> new NotificationDto().fromModel(notification))
                .collect(Collectors.toSet());
        return this;
    }

    public Set<NotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public ActionConfigDto getConfig() {
        return config;
    }

    public void setConfig(ActionConfigDto config) {
        this.config = config;
    }

}
