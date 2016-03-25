package com.baidu.oped.iop.m4.mvc.rest.aggr;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.aggr.AggrTask;
import com.baidu.oped.iop.m4.domain.repository.aggr.AggrTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.aggr.AggrTaskDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

;

/**
 * @author mason
 */
@RestController
@RequestMapping("products/{productName}/apps/{appName}/aggrTasks")
public class AggrTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(AggrTaskController.class);

    @Autowired
    private AggrTaskRepository aggrTaskRepository;

    @RequestMapping(method = POST)
    public AggrTask createAggrTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody AggrTaskDto aggrTaskDto) {
        LOG.debug("Create AggrTask for productName: {} and appName: {} with AggrTaskDto: {}", productName, appName,
                aggrTaskDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteAggrTask(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("taskName") String taskName) {
        LOG.debug("Delete AggrTask {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<AggrTask> findAggrTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding AggrTasks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public AggrTask updateAggrTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody AggrTaskDto aggrTaskDto) {
        LOG.debug("Update AggrTask {}.{}.{} with AggrTaskDto: {}", productName, appName, taskName, aggrTaskDto);

        return null;
    }

}
