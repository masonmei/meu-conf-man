package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.collect.ProcessCollectTaskDto;

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
@RequestMapping("products/{productName}/apps/{appName}/processCollectTasks")
public class ProcessCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessCollectTaskController.class);

    @RequestMapping(method = POST)
    public ProcessCollectTask createProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody ProcessCollectTaskDto processCollectTaskDto) {
        LOG.debug("Create ProcessCollectTask for productName: {} and appName: {} with ProcessCollectTaskDto: {}",
                productName, appName, processCollectTaskDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete ProcessCollectTask {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<ProcessCollectTask> findProcessCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding ProcessCollectTasks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public ProcessCollectTask updateProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody ProcessCollectTaskDto processCollectTaskDto) {
        LOG.debug("Update ProcessCollectTask {}.{}.{} with ProcessCollectTaskDto: {}", productName, appName, taskName,
                processCollectTaskDto);

        return null;
    }

}
