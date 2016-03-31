package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.Filter;
import com.baidu.oped.iop.m4.domain.entity.alert.Formula;
import com.baidu.oped.iop.m4.domain.entity.alert.MonitoringObject;
import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.domain.entity.alert.PolicyLevel;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Data translate object of Policy.
 *
 * @author mason
 */
public class PolicyDto extends AbstractAppLayerAuditableDto<Policy> {

    private static final long serialVersionUID = -5260111655804236619L;

    private FilterDto filter;
    private PolicyLevel level;
    private FormulaDto formula;
    private MonitoringObjectDto monitoringObject;
    private Set<ActionDto> incidentActions = new HashSet<>();
    private Set<ActionDto> insufficientActions = new HashSet<>();
    private Set<ActionDto> resumeActions = new HashSet<>();

    @Override
    public void toModel(Policy policy) {
        policy.setName(getName());
        policy.setLevel(level);
        Filter filter = new Filter();
        this.filter.toModel(filter);
        policy.setFilter(filter);

        Formula formula = new Formula();
        this.formula.toModel(formula);
        policy.setFormula(formula);

        MonitoringObject monitoringObject = new MonitoringObject();
        this.monitoringObject.toModel(monitoringObject);
        policy.setMonitoringObject(monitoringObject);

        policy.setIncidentActions(incidentActions.stream()
                .map(actionDto -> {
                    Action action = new Action();
                    action.setName(actionDto.getName());
                    return action;
                })
                .collect(Collectors.toSet()));
        policy.setInsufficientActions(insufficientActions.stream()
                .map(actionDto -> {
                    Action action = new Action();
                    action.setName(actionDto.getName());
                    return action;
                })
                .collect(Collectors.toSet()));
        policy.setResumeActions(resumeActions.stream()
                .map(actionDto -> {
                    Action action = new Action();
                    action.setName(actionDto.getName());
                    return action;
                })
                .collect(Collectors.toSet()));
    }

    @Override
    public PolicyDto fromModel(Policy policy) {
        super.initProcess(policy);
        this.level = policy.getLevel();
        this.filter = new FilterDto().fromModel(policy.getFilter());
        this.formula = new FormulaDto().fromModel(policy.getFormula());
        this.monitoringObject = new MonitoringObjectDto().fromModel(policy.getMonitoringObject());
        this.incidentActions = policy.getIncidentActions()
                .stream()
                .map(action -> new ActionDto().fromModel(action))
                .collect(Collectors.toSet());
        this.insufficientActions = policy.getInsufficientActions()
                .stream()
                .map(action -> new ActionDto().fromModel(action))
                .collect(Collectors.toSet());
        this.resumeActions = policy.getResumeActions()
                .stream()
                .map(action -> new ActionDto().fromModel(action))
                .collect(Collectors.toSet());
        return this;
    }

    public FilterDto getFilter() {
        return filter;
    }

    public void setFilter(FilterDto filter) {
        this.filter = filter;
    }

    public PolicyLevel getLevel() {
        return level;
    }

    public void setLevel(PolicyLevel level) {
        this.level = level;
    }

    public FormulaDto getFormula() {
        return formula;
    }

    public void setFormula(FormulaDto formula) {
        this.formula = formula;
    }

    public MonitoringObjectDto getMonitoringObject() {
        return monitoringObject;
    }

    public void setMonitoringObject(MonitoringObjectDto monitoringObject) {
        this.monitoringObject = monitoringObject;
    }

    public Set<ActionDto> getIncidentActions() {
        return incidentActions;
    }

    public void setIncidentActions(Set<ActionDto> incidentActions) {
        this.incidentActions = incidentActions;
    }

    public Set<ActionDto> getInsufficientActions() {
        return insufficientActions;
    }

    public void setInsufficientActions(Set<ActionDto> insufficientActions) {
        this.insufficientActions = insufficientActions;
    }

    public Set<ActionDto> getResumeActions() {
        return resumeActions;
    }

    public void setResumeActions(Set<ActionDto> resumeActions) {
        this.resumeActions = resumeActions;
    }

}
