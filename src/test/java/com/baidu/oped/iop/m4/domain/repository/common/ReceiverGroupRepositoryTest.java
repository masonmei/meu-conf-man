package com.baidu.oped.iop.m4.domain.repository.common;

import static org.junit.Assert.*;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;
import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup;

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
public class ReceiverGroupRepositoryTest {

    @Autowired
    private ReceiverGroupRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        ReceiverGroup callback = new ReceiverGroup();
        callback.setProductName("productName");
        callback.setName("group");
        callback.setDescription("http://www.baidu.com");
        callback.getMembers().clear();
        callback.getMembers().add("meidongxu");
        repository.save(callback);

        callback = new ReceiverGroup();
        callback.setProductName("productName1");
        callback.setName("group");
        callback.setDescription("http://www.baidu.com");
        callback.getMembers().clear();
        callback.getMembers().add("meidongxu");
        repository.save(callback);

        callback = new ReceiverGroup();
        callback.setProductName("productName1");
        callback.setName("group1");
        callback.setDescription("http://www.baidu.com");
        callback.getMembers().clear();
        callback.getMembers().add("meidongxu");
        repository.save(callback);

    }

    @Test
    public void findOneByProductNameAndName() throws Exception {
        Optional<ReceiverGroup> callback = repository.findOneByProductNameAndName("productName", "group");
        assertTrue(callback.isPresent());

        callback = repository.findOneByProductNameAndName("productName", "NonExistCallbackName");
        assertFalse(callback.isPresent());
    }

    @Test
    public void findByProductName() throws Exception {
        Page<ReceiverGroup> callbacks = repository.findByProductName("productName", new PageRequest(0, 10));
        assertEquals(1, callbacks.getContent().size());
        callbacks = repository.findByProductName("productName1", new PageRequest(0, 10));
        assertEquals(2, callbacks.getContent()
                .size());
    }

    @Test
    public void findAll() throws Exception {
        Page<ReceiverGroup> callbackPage =
                repository.findAll(new ReceiverGroupRepository.SearchSpecification("productName1", "NAME:group"),
                        new PageRequest(0, 10));
        assertEquals(2, callbackPage.getContent()
                .size());

        callbackPage =
                repository.findAll(new ReceiverGroupRepository.SearchSpecification("productName1", "NAME:group1"),
                        new PageRequest(0, 10));
        assertEquals(1, callbackPage.getContent()
                .size());

        callbackPage =
                repository.findAll(new ReceiverGroupRepository.SearchSpecification("productName1", "group"),
                        new PageRequest(0, 10));
        assertEquals(2, callbackPage.getContent()
                .size());
    }
}