package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.LogCollectTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.LogCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.param.PageData;
import com.baidu.oped.iop.m4.mvc.param.QueryParam;
import com.baidu.oped.iop.m4.utils.PageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 * @author mason
 */
@RestController
@RequestMapping("products/{productName}/apps/{appName}/logCollectTasks")
public class LogCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(LogCollectTaskController.class);

    @Autowired
    private LogCollectTaskRepository taskRepository;

    @RequestMapping(method = POST)
    public LogCollectTaskDto createLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody LogCollectTaskDto taskDto) {
        LOG.debug("Create LogCollectTask for productName: {} and appName: {} with LogCollectTaskDto: {}", productName,
                appName, taskDto);
        Optional<LogCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskDto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The task with name %s already exists.", taskDto.getName()));
        }
        LogCollectTask task = new LogCollectTask();
        taskDto.toModel(task);
        task.setProductName(productName);
        task.setAppName(appName);
        LogCollectTask saved = taskRepository.save(task);
        LOG.debug("LogCollectTask {} saved succeed.", saved);
        return new LogCollectTaskDto().fromModel(saved);
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete LogCollectTask {}.{}.{} ", productName, appName, taskName);
        Optional<LogCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist LogCollectTask");
        }
        taskRepository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<LogCollectTaskDto> findLogCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, QueryParam queryParam) {
        LOG.debug("Finding LogCollectTasks with productName: {} and appName: {}", productName, appName);
        Page<LogCollectTask> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query LogCollectTasks with productName: {}, appName: {}", productName, appName);
            tasks = taskRepository.findByProductNameAndAppName(productName, appName, queryParam.pageable());
        } else {
            LOG.debug("Query LogCollectTasks with productName: {}, appName: {} and searcj: {}", productName, appName,
                    search);
            tasks = taskRepository.findAll(
                    new LogCollectTaskRepository.SearchSpecification(productName, appName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new LogCollectTaskDto().fromModel(source));
    }

    @RequestMapping(value = "{taskName}", method = GET)
    public LogCollectTaskDto findLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Find LogCollectTask with productName: {}, appName: {}, taskName: {}", productName, appName,
                taskName);
        Optional<LogCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("LogCollectTask not exist");
        }

        return new LogCollectTaskDto().fromModel(findOne.get());
    }


    @RequestMapping(value = "{taskName}", method = PUT)
    public LogCollectTaskDto updateLogCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody LogCollectTaskDto taskDto) {
        LOG.debug("Update LogCollectTask {}.{}.{} with LogCollectTaskDto: {}", productName, appName, taskName, taskDto);

        Optional<LogCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist LogCollectTask");
        }

        LogCollectTask collectTask = findOne.get();
        taskDto.toModel(collectTask);
        LogCollectTask savedTask = taskRepository.saveAndFlush(collectTask);
        return new LogCollectTaskDto().fromModel(savedTask);
    }

}
