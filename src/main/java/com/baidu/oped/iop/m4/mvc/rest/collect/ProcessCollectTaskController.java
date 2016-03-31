package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.ProcessCollectTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.ProcessCollectTaskDto;
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
@RequestMapping("products/{productName}/apps/{appName}/processCollectTasks")
public class ProcessCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessCollectTaskController.class);

    @Autowired
    private ProcessCollectTaskRepository taskRepository;

    @RequestMapping(method = POST)
    public ProcessCollectTaskDto createProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody ProcessCollectTaskDto taskDto) {
        LOG.debug("Create ProcessCollectTask for productName: {} and appName: {} with ProcessCollectTaskDto: {}",
                productName, appName, taskDto);
        Optional<ProcessCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskDto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The task with name %s already exists.", taskDto.getName()));
        }
        ProcessCollectTask task = new ProcessCollectTask();
        taskDto.toModel(task);
        task.setProductName(productName);
        task.setAppName(appName);
        ProcessCollectTask saved = taskRepository.save(task);
        LOG.debug("ProcessCollectTask {} saved succeed.", saved);
        return new ProcessCollectTaskDto().fromModel(saved);
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete ProcessCollectTask {}.{}.{} ", productName, appName, taskName);
        Optional<ProcessCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist ProcessCollectTask");
        }
        taskRepository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<ProcessCollectTaskDto> findProcessCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, QueryParam queryParam) {
        LOG.debug("Query ProcessCollectTasks with productName: {}, appName: {}, queryParam: {}", productName, appName,
                queryParam);

        Page<ProcessCollectTask> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query ProcessCollectTasks with productName: {}, appName: {}", productName, appName);
            tasks = taskRepository.findByProductNameAndAppName(productName, appName, queryParam.pageable());
        } else {
            LOG.debug("Query ProcessCollectTasks with productName: {}, appName: {} and search: {}", productName,
                    appName, search);
            tasks = taskRepository.findAll(
                    new ProcessCollectTaskRepository.SearchSpecification(productName, appName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new ProcessCollectTaskDto().fromModel(source));
    }

    @RequestMapping(value = "{taskName}", method = GET)
    public ProcessCollectTaskDto findProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Find ProcessCollectTasks with productName: {}, appName: {}, taskName: {}", productName, appName,
                taskName);
        Optional<ProcessCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("ProcessCollectTask not exist");
        }

        return new ProcessCollectTaskDto().fromModel(findOne.get());
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public ProcessCollectTaskDto updateProcessCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody ProcessCollectTaskDto taskDto) {
        LOG.debug("Update ProcessCollectTask {}.{}.{} with ProcessCollectTaskDto: {}", productName, appName, taskName,
                taskDto);

        Optional<ProcessCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist ProcessCollectTask");
        }

        ProcessCollectTask collectTask = findOne.get();
        taskDto.toModel(collectTask);
        ProcessCollectTask savedTask = taskRepository.saveAndFlush(collectTask);
        return new ProcessCollectTaskDto().fromModel(savedTask);
    }

}
