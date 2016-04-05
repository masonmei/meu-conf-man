package com.baidu.oped.iop.m4.custom.audit;

import com.baidu.oped.iop.m4.service.RecordAuditingService;
import com.baidu.oped.iop.m4.utils.JacksonUtils;
import com.baidu.oped.iop.m4.custom.SpringContextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * @author mason
 */
@Configurable
public class RecordAuditingEntityListener {
    private static final Logger LOG = LoggerFactory.getLogger(RecordAuditingEntityListener.class);

    @PostPersist
    public void postPersist(Object target) {
        LOG.debug("Post Persist processing: ", JacksonUtils.serialize(target));
        RecordAuditingService recordAuditingService = SpringContextUtils.getBean(RecordAuditingService.class);

        if (recordAuditingService != null) {
            recordAuditingService.recordCreated(target);
        }
    }

    @PostUpdate
    public void postUpdate(Object target) {
        LOG.debug("Post Update processing: ", JacksonUtils.serialize(target));
        RecordAuditingService recordAuditingService = SpringContextUtils.getBean(RecordAuditingService.class);

        if (recordAuditingService != null) {
            recordAuditingService.recordUpdated(target);
        }
    }

    @PostRemove
    public void postDelete(Object target) {
        LOG.debug("Post Remove processing: ", JacksonUtils.serialize(target));
        RecordAuditingService recordAuditingService = SpringContextUtils.getBean(RecordAuditingService.class);

        if (recordAuditingService != null) {
            recordAuditingService.recordRemoved(target);
        }
    }
}
