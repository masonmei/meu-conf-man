package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.collect.ExecCollectTaskDto;

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
@RequestMapping("products/{productName}/apps/{appName}/execCollectTasks")
public class ExecCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(ExecCollectTaskController.class);

    @RequestMapping(method = POST)
    public ExecCollectTask createExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody ExecCollectTaskDto execCollectTaskDto) {
        LOG.debug("Create ExecCollectTask for productName: {} and appName: {} with ExecCollectTaskDto: {}", productName,
                appName, execCollectTaskDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete ExecCollectTask {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<ExecCollectTask> findExecCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding ExecCollectTasks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public ExecCollectTask updateExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody ExecCollectTaskDto execCollectTaskDto) {
        LOG.debug("Update ExecCollectTask {}.{}.{} with ExecCollectTaskDto: {}", productName, appName, taskName,
                execCollectTaskDto);

        return null;
    }

}
