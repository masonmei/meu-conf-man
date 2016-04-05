package com.baidu.oped.iop.m4.mvc.rest.alert;

import static com.baidu.oped.iop.m4.utils.JacksonUtils.serialize;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.domain.entity.alert.TimeRange;
import com.baidu.oped.iop.m4.domain.repository.alert.ActionRepository;
import com.baidu.oped.iop.m4.domain.repository.alert.PolicyRepository;
import com.baidu.oped.iop.m4.mvc.dto.alert.ActionConfigDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.ActionDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.NotificationDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.TimeRangeDto;

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

import java.sql.Time;
import java.time.LocalTime;
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
public class ActionControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ActionRepository repository;

    @Autowired
    private PolicyRepository policyRepository;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

        policyRepository.deleteAll();
        repository.deleteAll();
        for (int i = 0; i < 15; i++) {
            Action action = new Action();
            action.setProductName("productName");
            action.setName("action" + i);
            ActionConfig config = new ActionConfig();
            config.setAlertThreshold(i);
            Set<TimeRange> disabledTimes = new HashSet<>();
            TimeRange timeRange = new TimeRange();
            timeRange.setFrom(new Time(10, 10, 10));
            timeRange.setTo(new Time(11, 11, 11));
            disabledTimes.add(timeRange);
            timeRange = new TimeRange();
            timeRange.setFrom(new Time(20, 10, 10));
            timeRange.setTo(new Time(21, 11, 11));
            disabledTimes.add(timeRange);
            config.setDisabledTimes(disabledTimes);
            action.setConfig(config);
            Set<Notification> notifications = new HashSet<>();
            Notification notification = new Notification();
            notification.setType("sms");
            notification.setReceiverId(1L);
            notifications.add(notification);
            action.setNotifications(notifications);
            repository.save(action);
        }
    }

    @Test
    public void createAction() throws Exception {
        ActionDto dto = new ActionDto();
        dto.setName("action");
        ActionConfigDto config = new ActionConfigDto();
        config.setAlertThreshold(10);
        Set<TimeRangeDto> disabledTime = new HashSet<>();
        TimeRangeDto timeRange = new TimeRangeDto();
        timeRange.setFrom(LocalTime.now()
                .minusMinutes(10));
        timeRange.setTo(LocalTime.now());
        disabledTime.add(timeRange);
        config.setDisabledTimes(disabledTime);
        dto.setConfig(config);
        Set<NotificationDto> notifications = new HashSet<>();
        NotificationDto notification = new NotificationDto();
        notification.setType("sms");
        notification.setReceiverId(1L);
        notifications.add(notification);
        dto.setNotifications(notifications);
        mvc.perform(post("/products/{productName}/actions", "productName1").accept(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user").password("123456"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serialize(dto)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName1")))
                .andExpect(jsonPath("$.data.id", notNullValue()));
    }

    @Test
    public void deleteAction() throws Exception {
        mvc.perform(delete("/products/{productName}/actions/{actionName}", "productName", "action1").with(
                user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateAction() throws Exception {
        ActionDto dto = new ActionDto();
        dto.setName("action");
        ActionConfigDto config = new ActionConfigDto();
        config.setAlertThreshold(10);
        Set<TimeRangeDto> disabledTime = new HashSet<>();
        TimeRangeDto timeRange = new TimeRangeDto();
        timeRange.setFrom(LocalTime.now()
                .minusMinutes(10));
        timeRange.setTo(LocalTime.now());
        disabledTime.add(timeRange);
        config.setDisabledTimes(disabledTime);
        dto.setConfig(config);
        Set<NotificationDto> notifications = new HashSet<>();
        NotificationDto notification = new NotificationDto();
        notification.setType("sms");
        notification.setReceiverId(1L);
        notifications.add(notification);
        dto.setNotifications(notifications);
        mvc.perform(put("/products/{productName}/actions/{actionName}", "productName", "action1").accept(
                MediaType.APPLICATION_JSON_UTF8)
                .with(user("user").password("123456"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serialize(dto)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()));
    }

    @Test
    public void findAction() throws Exception {
        mvc.perform(get("/products/{productName}/actions/{actionName}", "productName", "action1").with(
                user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("action1")));
    }

    @Test
    public void findActions() throws Exception {
        mvc.perform(get("/products/{productName}/actions", "productName").param("orderBy", "-name")
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

        mvc.perform(get("/products/{productName}/actions", "productName").param("pageNumber", "1")
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

        mvc.perform(get("/products/{productName}/actions", "productName").param("query", "name:action1")
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

        mvc.perform(get("/products/{productName}/actions", "productName", "appName").param("query", "action1")
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