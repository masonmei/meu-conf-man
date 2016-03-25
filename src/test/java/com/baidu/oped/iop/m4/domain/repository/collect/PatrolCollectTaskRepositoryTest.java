package com.baidu.oped.iop.m4.domain.repository.collect;

import static com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTaskMethod.HTTP;

import static org.junit.Assert.*;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.LogTaskParam;
import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTaskMethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test cases for class .
 *
 * @author mason
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
public class PatrolCollectTaskRepositoryTest {

    @Autowired
    private PatrolCollectTaskRepository taskRepository;

    @Test
    public void findByProductNameAndAppNameAndTargetContains() throws Exception {
        Page<PatrolCollectTask> tasks =
                taskRepository.findByProductNameAndAppNameAndTargetContains("productName", "appName", "test",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndAppNameAndTargetContains("productName", "appName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndTargetContains() throws Exception {
        Page<PatrolCollectTask> tasks =
                taskRepository.findByProductNameAndTargetContains("productName", "test", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndTargetContains("productName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<PatrolCollectTask> tasks =
                taskRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "Name",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndNameContains() throws Exception {
        Page<PatrolCollectTask> tasks =
                taskRepository.findByProductNameAndNameContains("productName", "Name", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndNameContains("productName", "NonExistName", new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Before
    public void setUp() throws Exception {
        taskRepository.deleteAll();
        PatrolCollectTask task = new PatrolCollectTask();
        task.setProductName("productName");
        task.setAppName("appName");
        task.setName("taskName");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("8080:/test");
        task.setMethod(HTTP);
        task.setPort(80);
        task.setTimeout(2000);
        taskRepository.save(task);
    }
}