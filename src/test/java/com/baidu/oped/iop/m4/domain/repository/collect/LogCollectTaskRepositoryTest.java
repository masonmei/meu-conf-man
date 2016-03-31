package com.baidu.oped.iop.m4.domain.repository.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.LogTaskParam;

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
public class LogCollectTaskRepositoryTest {
    @Autowired
    private LogCollectTaskRepository taskRepository;

    @Test
    public void findOneByProductNameAndAppNameAndName() throws Exception {
        Optional<LogCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName("productName", "appName", "taskName");
        assertTrue(findOne.isPresent());
        assertEquals("comment", findOne.get()
                .getComment());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContainsOrTargetContains() throws Exception {
        Page<LogCollectTask> tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName1", "appName1", "Target1"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName1", "appName1", "taskName"),
                new PageRequest(0, 10));
        assertEquals(2, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndParamsContains() throws Exception {
        Page<LogCollectTask> tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName", "appName", "PARAMS:logfilePath"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName", "appName", "PARAMS:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndTargetContains() throws Exception {
        Page<LogCollectTask> tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName", "appName", "TARGET:logTarget"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName", "appName", "Target:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<LogCollectTask> tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName", "appName", "NAME:Name"),
                new PageRequest(0, 10));
        assertEquals(1, tasks.getContent()
                .size());
        tasks = taskRepository.findAll(
                new LogCollectTaskRepository.SearchSpecification("productName", "appName", "NAME:NonExistName"),
                new PageRequest(0, 10));
        assertEquals(0, tasks.getContent()
                .size());
    }

    @Before
    public void setUp() throws Exception {
        taskRepository.deleteAll();
        LogCollectTask task = new LogCollectTask();
        task.setProductName("productName");
        task.setAppName("appName");
        task.setName("taskName");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("logTarget");
        LogTaskParam logTaskParam = new LogTaskParam();
        logTaskParam.setValue("234");
        logTaskParam.setCondition("condition");
        logTaskParam.setLimitRate(3);
        logTaskParam.setLogFilepath("/logfilePath");
        logTaskParam.setMatchStr("matchStr");
        logTaskParam.setPreMatch("preMatch");
        task.setLogTaskParam(logTaskParam);
        taskRepository.save(task);

        task = new LogCollectTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("taskName");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("logTarget1");
        logTaskParam = new LogTaskParam();
        logTaskParam.setValue("234");
        logTaskParam.setCondition("condition");
        logTaskParam.setLimitRate(3);
        logTaskParam.setLogFilepath("/logfilePath");
        logTaskParam.setMatchStr("matchStr");
        logTaskParam.setPreMatch("preMatch");
        task.setLogTaskParam(logTaskParam);
        taskRepository.save(task);

        task = new LogCollectTask();
        task.setProductName("productName1");
        task.setAppName("appName1");
        task.setName("taskName1");
        task.setComment("comment");
        task.setCycle(60);
        task.setComment("comment");
        task.setTarget("logTarget1");
        logTaskParam = new LogTaskParam();
        logTaskParam.setValue("234");
        logTaskParam.setCondition("condition");
        logTaskParam.setLimitRate(3);
        logTaskParam.setLogFilepath("/logfilePath");
        logTaskParam.setMatchStr("matchStr");
        logTaskParam.setPreMatch("preMatch");
        task.setLogTaskParam(logTaskParam);
        taskRepository.save(task);
    }
}