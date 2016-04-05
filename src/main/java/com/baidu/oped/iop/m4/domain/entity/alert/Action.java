package com.baidu.oped.iop.m4.domain.entity.alert;

import com.baidu.oped.iop.m4.domain.entity.common.ProductLayerEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {@Index(name = "app_action_unique_index", columnList = "`product_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`")})
public class Action extends ProductLayerEntity<Long> {

    private static final long serialVersionUID = -499533822964411972L;

    @Embedded
    private ActionConfig config;

    @ElementCollection(targetClass = Notification.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "action_notifications", joinColumns = {@JoinColumn(name = "action_id")})
    private Set<Notification> notifications = new HashSet<>();
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "resumeActions", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    private Set<Policy> resumeActionsPolicies = new HashSet<>();
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "insufficientActions", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    private Set<Policy> insufficientActionPolicies = new HashSet<>();
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "incidentActions", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    private Set<Policy> incidentActionPolicies = new HashSet<>();

    public ActionConfig getConfig() {
        return config;
    }

    public void setConfig(ActionConfig config) {
        this.config = config;
    }

//    public Set<Policy> getIncidentActionPolicies() {
//        return incidentActionPolicies;
//    }
//
//    public void setIncidentActionPolicies(Set<Policy> incidentActionPolicies) {
//        this.incidentActionPolicies = incidentActionPolicies;
//    }
//
//    public Set<Policy> getInsufficientActionPolicies() {
//        return insufficientActionPolicies;
//    }
//
//    public void setInsufficientActionPolicies(Set<Policy> insufficientActionPolicies) {
//        this.insufficientActionPolicies = insufficientActionPolicies;
//    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

//    public Set<Policy> getResumeActionsPolicies() {
//        return resumeActionsPolicies;
//    }
//
//    public void setResumeActionsPolicies(Set<Policy> resumeActionsPolicies) {
//        this.resumeActionsPolicies = resumeActionsPolicies;
//    }
}
