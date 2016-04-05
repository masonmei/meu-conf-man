package com.baidu.oped.iop.m4.domain.repository.alert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.ActionConfig;
import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.domain.entity.alert.TimeRange;
import com.baidu.oped.iop.m4.domain.repository.audit.AuditHistoryRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class ActionRepositoryTest {
    @Autowired
    private ActionRepository repository;

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    @Test
    public void findOneByProductNameAndName() throws Exception {
        Optional<Action> findOne = repository.findOneByProductNameAndName("productName", "action");
        assertTrue(findOne.isPresent());
        assertEquals(100, findOne.get()
                .getConfig()
                .getAlertThreshold());
    }

    @Test
    public void findByProductName() throws Exception {
        Page<Action> actions = repository.findByProductName("productName", new PageRequest(0, 10));
        assertEquals(1, actions.getContent()
                .size());
        actions = repository.findByProductName("productName1", new PageRequest(0, 10));
        assertEquals(2, actions.getContent()
                .size());
    }

    @Test
    public void findAll() throws Exception {
        ActionRepository.SearchSpecification searchSpecification =
                new ActionRepository.SearchSpecification("productName", "sms");
        Page<Action> actions = repository.findAll(searchSpecification, new PageRequest(0, 10));
        assertEquals(1, actions.getContent()
                .size());

    }

    @Before
    public void setUp() throws Exception {
        auditHistoryRepository.deleteAll();
        repository.deleteAll();
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
        repository.save(action);

        action = new Action();
        action.setProductName("productName1");
        action.setName("action1");
        config = new ActionConfig();
        config.setAlertThreshold(100);
        disabledTimes = new HashSet<>();
        timeRange = new TimeRange();
        timeRange.setFrom(new Time(10, 10, 10));
        timeRange.setTo(new Time(11, 11, 11));
        disabledTimes.add(timeRange);
        config.setDisabledTimes(disabledTimes);
        action.setConfig(config);
        notifications = new HashSet<>();
        notification = new Notification();
        notification.setType("email");
        notification.setReceiverId(2L);
        notifications.add(notification);
        action.setNotifications(notifications);
        repository.save(action);

        action = new Action();
        action.setProductName("productName1");
        action.setName("action2");
        config = new ActionConfig();
        config.setAlertThreshold(100);
        disabledTimes = new HashSet<>();
        timeRange = new TimeRange();
        timeRange.setFrom(new Time(10, 10, 10));
        timeRange.setTo(new Time(11, 11, 11));
        disabledTimes.add(timeRange);
        config.setDisabledTimes(disabledTimes);
        action.setConfig(config);
        notifications = new HashSet<>();
        notification = new Notification();
        notification.setType("http");
        notification.setReceiverId(1L);
        notifications.add(notification);
        action.setNotifications(notifications);
        repository.save(action);
    }
}