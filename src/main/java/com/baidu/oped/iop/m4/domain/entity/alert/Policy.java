package com.baidu.oped.iop.m4.domain.entity.alert;


import static com.baidu.oped.iop.m4.domain.entity.alert.PolicyLevel.CRITICAL;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.EnumType.STRING;

import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "app_policy_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
public class Policy extends AppLayerEntity<Long> {

    private static final String JOIN_COLUMN_NAME = "policy_id";

    @Enumerated(STRING)
    private PolicyLevel level = CRITICAL;

    @Embedded
    private MonitoringObject monitoringObject;

    @Embedded
    private Formula formula;

    @Embedded
    private Filter filter;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "policy_resume_action")
    private Set<Action> resumeActions = new HashSet<>();

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "policy_insufficient_action")
    private Set<Action> insufficientActions = new HashSet<>();

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "policy_incident_action")
    private Set<Action> incidentActions = new HashSet<>();

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Set<Action> getIncidentActions() {
        return incidentActions;
    }

    public void setIncidentActions(Set<Action> incidentActions) {
        this.incidentActions = incidentActions;
    }

    public Set<Action> getInsufficientActions() {
        return insufficientActions;
    }

    public void setInsufficientActions(Set<Action> insufficientActions) {
        this.insufficientActions = insufficientActions;
    }

    public PolicyLevel getLevel() {
        return level;
    }

    public void setLevel(PolicyLevel level) {
        this.level = level;
    }

    public MonitoringObject getMonitoringObject() {
        return monitoringObject;
    }

    public void setMonitoringObject(MonitoringObject monitoringObject) {
        this.monitoringObject = monitoringObject;
    }

    public Set<Action> getResumeActions() {
        return resumeActions;
    }

    public void setResumeActions(Set<Action> resumeActions) {
        this.resumeActions = resumeActions;
    }
}
