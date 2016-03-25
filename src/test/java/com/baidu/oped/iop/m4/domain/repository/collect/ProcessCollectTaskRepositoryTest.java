package com.baidu.oped.iop.m4.domain.repository.collect;

import static org.junit.Assert.assertEquals;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;

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
public class ProcessCollectTaskRepositoryTest {

    @Autowired
    private ProcessCollectTaskRepository taskRepository;

    @Test
    public void findByProductNameAndAppNameAndTargetContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                taskRepository.findByProductNameAndAppNameAndTargetContains("productName", "appName", "tomcat",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndAppNameAndTargetContains("productName", "appName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndTargetContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                taskRepository.findByProductNameAndTargetContains("productName", "tomcat", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndTargetContains("productName", "NonExistName", new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                taskRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "Name",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndNameContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                taskRepository.findByProductNameAndNameContains("productName", "Name", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndNameContains("productName", "NonExistName", new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Before
    public void setUp() throws Exception {
        taskRepository.deleteAll();
        ProcessCollectTask task = new ProcessCollectTask();
        task.setProductName("productName");
        task.setAppName("appName");
        task.setName("processCollectTaskName");
        task.setCycle(60);
        task.setTarget("/bin/tomcat");
        task.setComment("comment");
        ProcessCollectTask save = taskRepository.save(task);
        save.setComment("comments");
        taskRepository.save(save);
    }
}