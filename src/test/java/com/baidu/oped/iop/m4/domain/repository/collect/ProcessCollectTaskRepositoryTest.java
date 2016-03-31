package com.baidu.oped.iop.m4.domain.repository.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import java.util.Optional;

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
    public void findByProductNameAndAppName() throws Exception {
        Page<ProcessCollectTask> tasks =
                taskRepository.findByProductNameAndAppName("productName", "appName", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findByProductNameAndAppName("productName1", "appName1", new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContainsOrTargetContains() throws Exception {
        Page<ProcessCollectTask> tasks = taskRepository.findAll(
                new ProcessCollectTaskRepository.SearchSpecification("productName1", "appName1", "Name1"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findOneByProductNameAndAppNameAndName() throws Exception {
        Optional<ProcessCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName("productName", "appName",
                        "processCollectTaskName");
        assertTrue(findOne.isPresent());
        assertEquals("comment", findOne.get()
                .getComment());
    }

    @Test
    public void findByProductNameAndAppNameAndTargetContains() throws Exception {
        Page<ProcessCollectTask> tasks = taskRepository.findAll(
                new ProcessCollectTaskRepository.SearchSpecification("productName", "appName", "TARGET:tomcat"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new ProcessCollectTaskRepository.SearchSpecification("productName", "appName", "TARGET:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }


    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<ProcessCollectTask> tasks = taskRepository.findAll(
                new ProcessCollectTaskRepository.SearchSpecification("productName", "appName", "NAME:Name"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new ProcessCollectTaskRepository.SearchSpecification("productName", "appName", "NAME:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
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
        taskRepository.save(task);

        task = new ProcessCollectTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("processCollectTaskName");
        task.setCycle(60);
        task.setTarget("/bin/tomcat/Name1");
        task.setComment("comment");
        taskRepository.save(task);

        task = new ProcessCollectTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("processCollectTaskName1");
        task.setCycle(60);
        task.setTarget("/bin/tomcat1");
        task.setComment("comment");
        taskRepository.save(task);
    }
}