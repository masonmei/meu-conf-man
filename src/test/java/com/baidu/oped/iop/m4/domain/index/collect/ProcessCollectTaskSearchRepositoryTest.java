package com.baidu.oped.iop.m4.domain.index.collect;

import static org.junit.Assert.assertEquals;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.ProcessCollectTaskRepository;

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
public class ProcessCollectTaskSearchRepositoryTest {

    @Autowired
    private ProcessCollectTaskRepository collectTaskRepository;

    @Autowired
    private ProcessCollectTaskSearchRepository searchRepository;

    @Before
    public void setUp() throws Exception {
        searchRepository.deleteAll();
        collectTaskRepository.deleteAll();
        ProcessCollectTask collectTask = new ProcessCollectTask();
        collectTask.setProductName("productName");
        collectTask.setAppName("appName");
        collectTask.setName("processCollectTaskName");
        collectTask.setCycle(60);
        collectTask.setTarget("/bin/tomcat");
        collectTask.setComment("comment");
        ProcessCollectTask save = collectTaskRepository.save(collectTask);
        searchRepository.save(collectTask);
        save.setComment("comments");
        collectTaskRepository.save(save);
        searchRepository.save(save);
    }

    @Test
    public void findByProductNameAndAppNameAndTargetContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                searchRepository.findByProductNameAndAppNameAndTargetContains("productName", "appName", "tomcat",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndAppNameAndNameContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                searchRepository.findByProductNameAndAppNameAndNameContains("productName", "appName", "Name",
                        new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
    }

    @Test
    public void findByProductNameAndNameContains() throws Exception {
        Page<ProcessCollectTask> tasks =
                searchRepository.findByProductNameAndNameContains("productName", "Name", new PageRequest(0, 10));
        assertEquals(1, tasks.getContent().size());
    }
}