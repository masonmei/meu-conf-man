package com.baidu.oped.iop.m4.domain.repository.collect;

import static org.junit.Assert.*;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;
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
public class DerivedTaskRepositoryTest {
    @Autowired
    private DerivedTaskRepository taskRepository;

    @Test
    public void findByProductNameAndAppNameAndFormulaContains() throws Exception {
        Page<DerivedTask> tasks =
                taskRepository.findByProductNameAndAppNameAndFormulaContains("productName", "appName", "CPU_IDLE",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndAppNameAndFormulaContains("productName", "appName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<DerivedTask> tasks =
                taskRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "Name",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndFormulaContains() throws Exception {
        Page<DerivedTask> tasks =
                taskRepository.findByProductNameAndFormulaContains("productName", "CPU_IDLE",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndFormulaContains("productName", "NonExistName",
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndNameContains() throws Exception {
        Page<DerivedTask> tasks =
                taskRepository.findByProductNameAndNameContains("productName", "Name", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
        tasks = taskRepository.findByProductNameAndNameContains("productName", "NonExistName", new PageRequest(0, 10));
        assertEquals(0, tasks.getContent().size());
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
    }
}