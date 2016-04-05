package com.baidu.oped.iop.m4.domain.repository.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;
import com.baidu.oped.iop.m4.domain.repository.audit.AuditHistoryRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * Test cases for class .
 *
 * @author mason
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
public class ReceiverCallbackRepositoryTest {

    @Autowired
    private ReceiverCallbackRepository repository;

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    @Before
    public void setUp() throws Exception {
        auditHistoryRepository.deleteAll();
        repository.deleteAll();
        ReceiverCallback callback = new ReceiverCallback();
        callback.setProductName("productName");
        callback.setName("callback");
        callback.setCallbackUri("http://www.baidu.com");
        callback.setNeedCredential(true);
        callback.setUsername("username");
        callback.setPassword("password");
        repository.save(callback);

        callback = new ReceiverCallback();
        callback.setProductName("productName1");
        callback.setName("callback");
        callback.setCallbackUri("http://www.baidu.com");
        callback.setNeedCredential(true);
        callback.setUsername("username");
        callback.setPassword("password");
        repository.save(callback);

        callback = new ReceiverCallback();
        callback.setProductName("productName1");
        callback.setName("callback1");
        callback.setCallbackUri("http://www.baidu.com");
        callback.setNeedCredential(true);
        callback.setUsername("username");
        callback.setPassword("password");
        repository.save(callback);
    }

    @Test
    public void findOneByProductNameAndName() throws Exception {
        Optional<ReceiverCallback> callback = repository.findOneByProductNameAndName("productName", "callback");
        assertTrue(callback.isPresent());

        callback = repository.findOneByProductNameAndName("productName", "NonExistCallbackName");
        assertFalse(callback.isPresent());
    }

    @Test
    public void findByProductName() throws Exception {
        Page<ReceiverCallback> callbacks = repository.findByProductName("productName", new PageRequest(0, 10));
        assertEquals(1, callbacks.getContent().size());
        callbacks = repository.findByProductName("productName1", new PageRequest(0, 10));
        assertEquals(2, callbacks.getContent()
                .size());
    }

    @Test
    public void findAll() throws Exception {
        Page<ReceiverCallback> callbackPage =
                repository.findAll(new ReceiverCallbackRepository.SearchSpecification("productName1", "NAME:callback"),
                        new PageRequest(0, 10));
        assertEquals(2, callbackPage.getContent()
                .size());

        callbackPage =
                repository.findAll(new ReceiverCallbackRepository.SearchSpecification("productName1", "NAME:callback1"),
                        new PageRequest(0, 10));
        assertEquals(1, callbackPage.getContent()
                .size());

        callbackPage = repository.findAll(
                new ReceiverCallbackRepository.SearchSpecification("productName1", "CALLBACK_URL:baidu.com"),
                new PageRequest(0, 10));
        assertEquals(2, callbackPage.getContent()
                .size());

        callbackPage =
                repository.findAll(new ReceiverCallbackRepository.SearchSpecification("productName1", "baidu.com"),
                        new PageRequest(0, 10));
        assertEquals(2, callbackPage.getContent()
                .size());
    }
}