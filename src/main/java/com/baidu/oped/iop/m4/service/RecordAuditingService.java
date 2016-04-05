package com.baidu.oped.iop.m4.service;

import static com.baidu.oped.iop.m4.domain.entity.audit.ActionType.CREATED;
import static com.baidu.oped.iop.m4.domain.entity.audit.ActionType.MODIFIED;
import static com.baidu.oped.iop.m4.domain.entity.audit.ActionType.REMOVE;

import com.baidu.oped.iop.m4.custom.audit.AbstractAuditable;
import com.baidu.oped.iop.m4.domain.entity.audit.ActionType;
import com.baidu.oped.iop.m4.domain.entity.audit.AuditHistory;
import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;
import com.baidu.oped.iop.m4.domain.entity.common.ProductLayerEntity;
import com.baidu.oped.iop.m4.domain.repository.audit.AuditHistoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

/**
 * @author mason
 */
@Service
public class RecordAuditingService {

    private static final Logger LOG = LoggerFactory.getLogger(RecordAuditingService.class);

    private static final Map<Class, String> entityNames = new HashMap<>();

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void constructMap() {
        LOG.debug("Construct entity name map.");
        Set<EntityType<?>> managedTypes = entityManager.getMetamodel()
                .getEntities();
        managedTypes.stream()
                .forEach(managedType -> entityNames.put(managedType.getJavaType(), managedType.getName()));
        LOG.debug("Construct entity name map finished.");
    }

    public void recordUpdated(Object target) {
        if (!AbstractAuditable.class.isAssignableFrom(target.getClass())) {
            LOG.debug("Auditing object is not an instance of {}", AbstractAuditable.class.getName());
            return;
        }
        LOG.debug("Recording object updated: {}", target);
        AbstractAuditable auditable = (AbstractAuditable) target;
        AuditHistory auditHistory = convertToAuditHistory(auditable, MODIFIED);
        if (auditHistory != null) {
            auditHistoryRepository.save(auditHistory);
        }
    }

    public void recordCreated(Object target) {
        if (!AbstractAuditable.class.isAssignableFrom(target.getClass())) {
            LOG.debug("Auditing object is not an instance of {}", AbstractAuditable.class.getName());
            return;
        }
        LOG.debug("Recording object created: {}", target);
        AbstractAuditable auditable = (AbstractAuditable) target;
        AuditHistory auditHistory = convertToAuditHistory(auditable, CREATED);
        if (auditHistory != null) {
            auditHistoryRepository.save(auditHistory);
        }
    }

    public void recordRemoved(Object target) {
        if (!AbstractAuditable.class.isAssignableFrom(target.getClass())) {
            LOG.debug("Auditing object is not an instance of {}", AbstractAuditable.class.getName());
            return;
        }
        LOG.debug("Recording object removed: {}", target);
        AbstractAuditable auditable = (AbstractAuditable) target;
        AuditHistory auditHistory = convertToAuditHistory(auditable, REMOVE);
        if (auditHistory != null) {
            auditHistoryRepository.save(auditHistory);
        }
    }

    private AuditHistory convertToAuditHistory(AbstractAuditable auditable, ActionType actionType) {
        Assert.notNull(auditable, "Auditable must not be null.");
        AuditHistory auditHistory = new AuditHistory();
        if (ProductLayerEntity.class.isAssignableFrom(auditable.getClass())) {
            ProductLayerEntity productLayerEntity = (ProductLayerEntity) auditable;
            auditHistory.setProductName(productLayerEntity.getProductName());
            auditHistory.setName(productLayerEntity.getName());
        }

        if (AppLayerEntity.class.isAssignableFrom(auditable.getClass())) {
            AppLayerEntity appLayerEntity = (AppLayerEntity) auditable;
            auditHistory.setAppName(appLayerEntity.getAppName());
        }

        auditHistory.setEntityName(entityNames.getOrDefault(auditable.getClass(), ""));

        auditHistory.setAuthor(auditable.getLastModifiedBy());
        auditHistory.setActionDate(auditable.getLastModifiedDate()
                .toDate());
        auditHistory.setVersion(auditable.getVersion());
        auditHistory.setDetailObject(auditable);
        auditHistory.setAction(actionType);
        return auditHistory;
    }
}
