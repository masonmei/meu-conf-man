package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.collect.LogCollectTaskDto;

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
@RequestMapping("products/{productName}/apps/{appName}/logCollectTasks")
public class LogCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(LogCollectTaskController.class);

    @RequestMapping(method = POST)
    public LogCollectTask createLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody LogCollectTaskDto logCollectTaskDto) {
        LOG.debug("Create LogCollectTask for productName: {} and appName: {} with LogCollectTaskDto: {}", productName,
                appName, logCollectTaskDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete LogCollectTask {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<LogCollectTask> findLogCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding LogCollectTasks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public LogCollectTask updateLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody LogCollectTaskDto logCollectTaskDto) {
        LOG.debug("Update LogCollectTask {}.{}.{} with LogCollectTaskDto: {}", productName, appName, taskName,
                logCollectTaskDto);

        return null;
    }

}
