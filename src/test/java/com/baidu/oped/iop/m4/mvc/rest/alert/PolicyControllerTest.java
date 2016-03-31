package com.baidu.oped.iop.m4.mvc.rest.alert;

import static com.baidu.oped.iop.m4.utils.JacksonUtils.serialize;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.ActionConfig;
import com.baidu.oped.iop.m4.domain.entity.alert.ComparisonOperator;
import com.baidu.oped.iop.m4.domain.entity.alert.Formula;
import com.baidu.oped.iop.m4.domain.entity.alert.MonitoringObject;
import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.domain.entity.alert.PolicyLevel;
import com.baidu.oped.iop.m4.domain.entity.alert.Statistic;
import com.baidu.oped.iop.m4.domain.entity.alert.TimeRange;
import com.baidu.oped.iop.m4.domain.entity.common.MetricType;
import com.baidu.oped.iop.m4.domain.repository.alert.ActionRepository;
import com.baidu.oped.iop.m4.domain.repository.alert.PolicyRepository;
import com.baidu.oped.iop.m4.mvc.dto.alert.ActionDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.FilterDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.FormulaDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.MonitoringObjectDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.PolicyDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;

/**
 * Test cases for class .
 *
 * @author mason
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
@WebAppConfiguration
public class PolicyControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private PolicyRepository repository;

    @Autowired
    private ActionRepository actionRepository;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

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

        for (int i = 0; i < 15; i++) {
            Policy policy = new Policy();
            policy.setProductName("productName");
            policy.setAppName("appName");
            policy.setName("policyName" + i);
            MonitoringObject monitoringObject = new MonitoringObject();
            monitoringObject.setName("monitoringObject" + i);
            monitoringObject.setType(MetricType.INSTANCE);
            policy.setMonitoringObject(monitoringObject);
            com.baidu.oped.iop.m4.domain.entity.alert.Filter filter =
                    new com.baidu.oped.iop.m4.domain.entity.alert.Filter();
            filter.setTotal(10);
            filter.setMax(3);
            policy.setFilter(filter);

            Formula formula = new Formula();
            formula.setMetric("metricName" + i);
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
        }
    }

    @Test
    public void createPolicy() throws Exception {
        PolicyDto policy = new PolicyDto();
        policy.setName("policyName");
        MonitoringObjectDto monitoringObject = new MonitoringObjectDto();
        monitoringObject.getNames()
                .add("monitoringObject");
        monitoringObject.setType(MetricType.INSTANCE);
        policy.setMonitoringObject(monitoringObject);
        FilterDto filter = new FilterDto();
        filter.setTotal(10);
        filter.setMax(3);
        policy.setFilter(filter);

        FormulaDto formula = new FormulaDto();
        formula.setMetric("metricName");
        formula.setStatistic(Statistic.AVG);
        formula.setComparisonOperator(ComparisonOperator.GT);
        formula.setValue(new BigDecimal(19));
        policy.setFormula(formula);
        policy.setLevel(PolicyLevel.CRITICAL);

        ActionDto actionDto = new ActionDto();
        actionDto.setName("action");
        Set<ActionDto> resumeActions = new HashSet<>();
        resumeActions.add(actionDto);
        policy.setResumeActions(resumeActions);
        Set<ActionDto> incidentActions = new HashSet<>();
        incidentActions.add(actionDto);
        policy.setIncidentActions(incidentActions);
        Set<ActionDto> insufficientActions = new HashSet<>();
        insufficientActions.add(actionDto);
        policy.setInsufficientActions(insufficientActions);

        mvc.perform(post("/products/{productName}/apps/{appName}/policies", "productName", "appName").accept(
                MediaType.APPLICATION_JSON_UTF8)
                .with(user("user").password("123456"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serialize(policy)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()));
    }

    @Test
    public void deletePolicy() throws Exception {
        mvc.perform(delete("/products/{productName}/apps/{appName}/policies/{policyName}", "productName", "appName",
                "policyName1").with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updatePolicy() throws Exception {
        PolicyDto policy = new PolicyDto();
        policy.setName("policyName");
        MonitoringObjectDto monitoringObject = new MonitoringObjectDto();
        monitoringObject.getNames()
                .add("monitoringObject");
        monitoringObject.setType(MetricType.INSTANCE);
        policy.setMonitoringObject(monitoringObject);
        FilterDto filter = new FilterDto();
        filter.setTotal(10);
        filter.setMax(3);
        policy.setFilter(filter);

        FormulaDto formula = new FormulaDto();
        formula.setMetric("metricName");
        formula.setStatistic(Statistic.AVG);
        formula.setComparisonOperator(ComparisonOperator.GT);
        formula.setValue(new BigDecimal(19));
        policy.setFormula(formula);
        policy.setLevel(PolicyLevel.CRITICAL);

        ActionDto actionDto = new ActionDto();
        actionDto.setName("action");
        Set<ActionDto> resumeActions = new HashSet<>();
        resumeActions.add(actionDto);
        policy.setResumeActions(resumeActions);
        Set<ActionDto> incidentActions = new HashSet<>();
        incidentActions.add(actionDto);
        policy.setIncidentActions(incidentActions);
        Set<ActionDto> insufficientActions = new HashSet<>();
        policy.setInsufficientActions(insufficientActions);

        mvc.perform(put("/products/{productName}/apps/{appName}/policies/{policyName}", "productName", "appName",
                "policyName1").with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serialize(policy)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("policyName")))
                .andExpect(jsonPath("$.data.version", is(1)))
                .andExpect(jsonPath("$.data.insufficientActions", hasSize(0)));
    }

    @Test
    public void findPolicy() throws Exception {
        mvc.perform(get("/products/{productName}/apps/{appName}/policies/{policyName}", "productName", "appName",
                "policyName1").with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("policyName1")));
    }

    @Test
    public void findPolicies() throws Exception {
        mvc.perform(get("/products/{productName}/apps/{appName}/policies", "productName", "appName")
                .param("orderBy", "-name")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(15)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/policies", "productName", "appName")
                .param("pageNumber", "1")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(15)))
                .andExpect(jsonPath("$.data.pageNumber", is(1)))
                .andExpect(jsonPath("$.data.first", is(false)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/policies", "productName", "appName")
                .param("query", "name:policyName1")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(6)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/policies", "productName", "appName")
                .param("query", "policyName1")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(6)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/policies", "productName", "appName")
                .param("query", "policyName1")
                .param("orderBy", "-name")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(6)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));
    }
}