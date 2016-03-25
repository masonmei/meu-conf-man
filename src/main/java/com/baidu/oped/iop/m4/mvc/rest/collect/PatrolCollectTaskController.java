package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.collect.PatrolCollectTaskDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mason
 */
@RestController
@RequestMapping("products/{productName}/apps/{appName}/patrolCollectTasks")
public class PatrolCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(PatrolCollectTaskController.class);

    @RequestMapping(method = POST)
    public PatrolCollectTask createPatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody PatrolCollectTaskDto patrolCollectTaskDto) {
        LOG.debug("Create PatrolCollectTask for productName: {} and appName: {} with PatrolCollectTaskDto: {}",
                productName, appName, patrolCollectTaskDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deletePatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete PatrolCollectTask {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<PatrolCollectTask> findPatrolCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding PatrolCollectTasks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public PatrolCollectTask updatePatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody PatrolCollectTaskDto patrolCollectTaskDto) {
        LOG.debug("Update PatrolCollectTask {}.{}.{} with PatrolCollectTaskDto: {}", productName, appName, taskName,
                patrolCollectTaskDto);

        return null;
    }

}
