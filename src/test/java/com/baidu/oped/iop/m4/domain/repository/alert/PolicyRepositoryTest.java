package com.baidu.oped.iop.m4.domain.repository.alert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.ActionConfig;
import com.baidu.oped.iop.m4.domain.entity.alert.ComparisonOperator;
import com.baidu.oped.iop.m4.domain.entity.alert.Filter;
import com.baidu.oped.iop.m4.domain.entity.alert.Formula;
import com.baidu.oped.iop.m4.domain.entity.alert.MonitoringObject;
import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.domain.entity.alert.PolicyLevel;
import com.baidu.oped.iop.m4.domain.entity.alert.Statistic;
import com.baidu.oped.iop.m4.domain.entity.alert.TimeRange;
import com.baidu.oped.iop.m4.domain.entity.common.MetricType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Test cases for class .
 *
 * @author mason
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
public class PolicyRepositoryTest {

    @Autowired
    private PolicyRepository repository;

    @Autowired
    private ActionRepository actionRepository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        actionRepository.deleteAll();
        Action action = new Action();
        action.setProductName("productName");
        action.setName("action");
        ActionConfig config = new ActionConfig();
        config.setAlertThreshold(100);
        Set<TimeRange> disabledTimes = new HashSet<>();
        TimeRange timeRange = new TimeRange();
        timeRange.setFrom(new Time(10, 10, 10));
        timeRange.setTo(new Time(11, 11, 11));
        disabledTimes.add(timeRange);
        config.setDisabledTimes(disabledTimes);
        action.setConfig(config);
        Set<Notification> notifications = new HashSet<>();
        Notification notification = new Notification();
        notification.setType("sms");
        notification.setReceiverId(1L);
        notifications.add(notification);
        action.setNotifications(notifications);
        Action savedAction = actionRepository.save(action);

        Policy policy = new Policy();
        policy.setProductName("productName");
        policy.setAppName("appName");
        policy.setName("policyName");
        MonitoringObject monitoringObject = new MonitoringObject();
        monitoringObject.setName("monitoringObject1");
        monitoringObject.setType(MetricType.INSTANCE);
        policy.setMonitoringObject(monitoringObject);
        Filter filter = new Filter();
        filter.setTotal(10);
        filter.setMax(3);
        policy.setFilter(filter);

        Formula formula = new Formula();
        formula.setMetric("metricName");
        formula.setStatistic(Statistic.AVG);
        formula.setComparisonOperator(ComparisonOperator.GT);
        formula.setValue(new BigDecimal(19));
        policy.setFormula(formula);
        policy.setLevel(PolicyLevel.CRITICAL);

        Set<Action> resumeActions = new HashSet<>();
        resumeActions.add(savedAction);
        policy.setResumeActions(resumeActions);
        Set<Action> incidentActions = new HashSet<>();
        incidentActions.add(savedAction);
        policy.setIncidentActions(incidentActions);
        Set<Action> insufficientActions = new HashSet<>();
        insufficientActions.add(savedAction);
        policy.setInsufficientActions(insufficientActions);
        repository.save(policy);

        policy = new Policy();
        policy.setProductName("productName1");
        policy.setAppName("appName1");
        policy.setName("policyName");
        monitoringObject = new MonitoringObject();
        monitoringObject.setName("monitoringObject1");
        monitoringObject.setType(MetricType.INSTANCE);
        policy.setMonitoringObject(monitoringObject);
        filter = new Filter();
        filter.setTotal(10);
        filter.setMax(3);
        policy.setFilter(filter);

        formula = new Formula();
        formula.setMetric("metricName");
        formula.setStatistic(Statistic.AVG);
        formula.setComparisonOperator(ComparisonOperator.GT);
        formula.setValue(new BigDecimal(19));
        policy.setFormula(formula);
        policy.setLevel(PolicyLevel.CRITICAL);

        resumeActions = new HashSet<>();
        resumeActions.add(savedAction);
        policy.setResumeActions(resumeActions);
        incidentActions = new HashSet<>();
        incidentActions.add(savedAction);
        policy.setIncidentActions(incidentActions);
        insufficientActions = new HashSet<>();
        insufficientActions.add(savedAction);
        policy.setInsufficientActions(insufficientActions);
        repository.save(policy);

        policy = new Policy();
        policy.setProductName("productName1");
        policy.setAppName("appName1");
        policy.setName("policyName1");
        monitoringObject = new MonitoringObject();
        monitoringObject.setName("monitoringObject1");
        monitoringObject.setType(MetricType.INSTANCE);
        policy.setMonitoringObject(monitoringObject);
        filter = new Filter();
        filter.setTotal(10);
        filter.setMax(3);
        policy.setFilter(filter);

        formula = new Formula();
        formula.setMetric("metricName");
        formula.setStatistic(Statistic.AVG);
        formula.setComparisonOperator(ComparisonOperator.GT);
        formula.setValue(new BigDecimal(19));
        policy.setFormula(formula);
        policy.setLevel(PolicyLevel.CRITICAL);

        resumeActions = new HashSet<>();
        resumeActions.add(savedAction);
        policy.setResumeActions(resumeActions);
        incidentActions = new HashSet<>();
        incidentActions.add(savedAction);
        policy.setIncidentActions(incidentActions);
        insufficientActions = new HashSet<>();
        insufficientActions.add(savedAction);
        policy.setInsufficientActions(insufficientActions);
        repository.save(policy);
    }

    @Test
    public void findOneByProductNameAndAppNameAndName() throws Exception {
        Optional<Policy> findOne =
                repository.findOneByProductNameAndAppNameAndName("productName", "appName", "policyName");
        assertTrue(findOne.isPresent());
        assertEquals("monitoringObject1", findOne.get()
                .getMonitoringObject()
                .getName());
    }

    @Test
    public void findByProductNameAndAppNameAndMonitoringObjectNameContains() throws Exception {
        Page<Policy> policies = repository.findAll(
                new PolicyRepository.SearchSpecification("productName", "appName",
                        "MONITORING_OBJECT:monitoringObject"), new PageRequest(0, 10));
        assertEquals(1, policies.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<Policy> tasks = repository.findAll(
                new PolicyRepository.SearchSpecification("productName", "appName", "NAME:Name1"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
        tasks = repository.findAll(
                new PolicyRepository.SearchSpecification("productName1", "appName1", "NAME:policyName"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppName() throws Exception {
        Page<Policy> tasks = repository.findByProductNameAndAppName("productName", "appName", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = repository.findByProductNameAndAppName("productName1", "appName1", new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndFormulaMetricContains() throws Exception {
        Page<Policy> tasks = repository.findAll(
                new PolicyRepository.SearchSpecification("productName1", "appName1",
                        "FORMULA:NonExistMetricName"), new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
        tasks = repository.findAll(
                new PolicyRepository.SearchSpecification("productName1", "appName1", "FORMULA:metricName"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findAll() throws Exception {
        PolicyRepository.SearchSpecification searchSpecification =
                new PolicyRepository.SearchSpecification("productName", "appName", "Name1");

        Page<Policy> policies = repository.findAll(searchSpecification, new PageRequest(0, 10));
        assertEquals(0, policies.getContent()
                .size());

        searchSpecification = new PolicyRepository.SearchSpecification("productName1", "appName1", "metricName");
        policies = repository.findAll(searchSpecification, new PageRequest(0, 10));
        assertEquals(2, policies.getContent()
                .size());
    }
}