package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;
import com.baidu.oped.iop.m4.mvc.dto.collect.DerivedTaskDto;

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
@RequestMapping("products/{productName}/apps/{appName}/derivedTasks")
public class DerivedTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(DerivedTaskController.class);

    @RequestMapping(method = POST)
    public DerivedTask createDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody DerivedTaskDto derivedTaskDto) {
        LOG.debug("Create DerivedTask for productName: {} and appName: {} with DerivedTaskDto: {}", productName,
                appName, derivedTaskDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public DerivedTask deleteDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete DerivedTask {}.{}.{} ", productName, appName, taskName);

        return null;
    }

    @RequestMapping(method = GET)
    public List<DerivedTask> findDerivedTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding DerivedTasks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public DerivedTask updateDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody DerivedTaskDto derivedTaskDto) {
        LOG.debug("Update DerivedTask {}.{}.{} with DerivedTaskDto: {}", productName, appName, taskName,
                derivedTaskDto);

        return null;
    }

}
