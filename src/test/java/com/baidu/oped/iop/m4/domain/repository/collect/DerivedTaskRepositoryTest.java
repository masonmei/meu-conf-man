package com.baidu.oped.iop.m4.domain.repository.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;

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
public class DerivedTaskRepositoryTest {
    @Autowired
    private DerivedTaskRepository taskRepository;

    @Test
    public void findOneByProductNameAndAppNameAndName() throws Exception {
        Optional<DerivedTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName("productName", "appName", "taskName");
        assertTrue(findOne.isPresent());
        assertEquals("comment", findOne.get()
                .getComment());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContainsOrFormulaContains() throws Exception {
        Page<DerivedTask> tasks = taskRepository.findAll(
                new DerivedTaskRepository.SearchSpecification("productName1", "appName1", "CPU_IDLE1"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new DerivedTaskRepository.SearchSpecification("productName1", "appName1", "taskName"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppName() throws Exception {
        Page<DerivedTask> tasks =
                taskRepository.findByProductNameAndAppName("productName", "appName", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findByProductNameAndAppName("productName1", "appName1", new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndFormulaContains() throws Exception {
        Page<DerivedTask> tasks = taskRepository.findAll(
                new DerivedTaskRepository.SearchSpecification("productName", "appName", "FORMULA:CPU_IDLE"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new DerivedTaskRepository.SearchSpecification("productName", "appName", "FORMULA:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<DerivedTask> tasks = taskRepository.findAll(
                new DerivedTaskRepository.SearchSpecification("productName", "appName", "NAME:Name"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new DerivedTaskRepository.SearchSpecification("productName", "appName", "NAME:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }

    @Before
    public void setUp() throws Exception {
        taskRepository.deleteAll();
        DerivedTask task = new DerivedTask();
        task.setProductName("productName");
        task.setAppName("appName");
        task.setName("taskName");
        task.setComment("comment");
        task.setFormula("sum(CPU_IDLE)");
        taskRepository.save(task);

        task = new DerivedTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("taskName");
        task.setComment("comment");
        task.setFormula("sum(CPU_IDLE1)");
        taskRepository.save(task);

        task = new DerivedTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("taskName1");
        task.setComment("comment");
        task.setFormula("sum(CPU_IDLE1)");
        taskRepository.save(task);
    }
}