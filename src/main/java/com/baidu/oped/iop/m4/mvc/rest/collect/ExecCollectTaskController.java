package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.ExecCollectTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.ExecCollectTaskDto;
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
@RequestMapping("products/{productName}/apps/{appName}/execCollectTasks")
public class ExecCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(ExecCollectTaskController.class);

    @Autowired
    private ExecCollectTaskRepository taskRepository;

    @RequestMapping(method = POST)
    public ExecCollectTaskDto createExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody ExecCollectTaskDto taskDto) {
        LOG.debug("Create ExecCollectTask for productName: {} and appName: {} with ExecCollectTaskDto: {}", productName,
                appName, taskDto);
        Optional<ExecCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskDto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The task with name %s already exists.", taskDto.getName()));
        }
        ExecCollectTask task = new ExecCollectTask();
        taskDto.toModel(task);
        task.setProductName(productName);
        task.setAppName(appName);
        ExecCollectTask saved = taskRepository.save(task);
        LOG.debug("ExecCollectTask {} saved succeed.", saved);
        return new ExecCollectTaskDto().fromModel(saved);
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete ExecCollectTask {}.{}.{} ", productName, appName, taskName);
        Optional<ExecCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist ExecCollectTask");
        }
        taskRepository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<ExecCollectTaskDto> findExecCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, QueryParam queryParam) {
        LOG.debug("Finding ExecCollectTasks with productName: {} and appName: {}", productName, appName);
        Page<ExecCollectTask> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query ExecCollectTasks with productName: {}, appName: {}", productName, appName);
            tasks = taskRepository.findByProductNameAndAppName(productName, appName, queryParam.pageable());
        } else {
            LOG.debug("Query ExecCollectTasks with productName: {}, appName: {} and search: {}", productName, appName,
                    search);
            tasks = taskRepository.findAll(
                    new ExecCollectTaskRepository.SearchSpecification(productName, appName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new ExecCollectTaskDto().fromModel(source));
    }

    @RequestMapping(value = "{taskName}", method = GET)
    public ExecCollectTaskDto findExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Find ExecCollectTask with productName: {}, appName: {}, taskName: {}", productName, appName,
                taskName);
        Optional<ExecCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("ExecCollectTask not exist");
        }

        return new ExecCollectTaskDto().fromModel(findOne.get());
    }


    @RequestMapping(value = "{taskName}", method = PUT)
    public ExecCollectTaskDto updateExecCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody ExecCollectTaskDto taskDto) {
        LOG.debug("Update ExecCollectTask {}.{}.{} with ExecCollectTaskDto: {}", productName, appName, taskName,
                taskDto);

        Optional<ExecCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist ExecCollectTask");
        }

        ExecCollectTask collectTask = findOne.get();
        taskDto.toModel(collectTask);
        ExecCollectTask savedTask = taskRepository.saveAndFlush(collectTask);
        return new ExecCollectTaskDto().fromModel(savedTask);
    }

}
