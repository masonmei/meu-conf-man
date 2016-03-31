package com.baidu.oped.iop.m4.domain.repository.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;

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
public class ExecCollectTaskRepositoryTest {

    @Autowired
    private ExecCollectTaskRepository taskRepository;

    @Test
    public void findOneByProductNameAndAppNameAndName() throws Exception {
        Optional<ExecCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName("productName", "appName", "taskName");
        assertTrue(findOne.isPresent());
        assertEquals("comment", findOne.get()
                .getComment());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContainsOrTargetContains() throws Exception {
        Page<ExecCollectTask> tasks = taskRepository.findAll(
                new ExecCollectTaskRepository.SearchSpecification("productName1", "appName1", "-ef1"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new ExecCollectTaskRepository.SearchSpecification("productName1", "appName1", "taskName"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppName() throws Exception {
        Page<ExecCollectTask> tasks =
                taskRepository.findByProductNameAndAppName("productName", "appName", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findByProductNameAndAppName("productName1", "appName1", new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndTargetContains() throws Exception {
        Page<ExecCollectTask> tasks = taskRepository.findAll(
                new ExecCollectTaskRepository.SearchSpecification("productName", "appName", "TARGET:ps"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new ExecCollectTaskRepository.SearchSpecification("productName", "appName", "TARGET:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }


    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<ExecCollectTask> tasks = taskRepository.findAll(
                new ExecCollectTaskRepository.SearchSpecification("productName", "appName", "NAME:Name"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new ExecCollectTaskRepository.SearchSpecification("productName", "appName", "NAME:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }

    @Before
    public void setUp() throws Exception {
        taskRepository.deleteAll();
        ExecCollectTask task = new ExecCollectTask();
        task.setProductName("productName");
        task.setAppName("appName");
        task.setName("taskName");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("ps -ef");
        taskRepository.save(task);

        task = new ExecCollectTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("taskName");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("ps -ef1");
        taskRepository.save(task);

        task = new ExecCollectTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("taskName1");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("ps -ef1");
        taskRepository.save(task);
    }
}