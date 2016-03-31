package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.PatrolCollectTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.PatrolCollectTaskDto;
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
@RequestMapping("products/{productName}/apps/{appName}/patrolCollectTasks")
public class PatrolCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(PatrolCollectTaskController.class);

    @Autowired
    private PatrolCollectTaskRepository taskRepository;

    @RequestMapping(method = POST)
    public PatrolCollectTaskDto createPatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody PatrolCollectTaskDto taskDto) {
        LOG.debug("Create PatrolCollectTask for productName: {} and appName: {} with PatrolCollectTaskDto: {}",
                productName, appName, taskDto);
        Optional<PatrolCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskDto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The task with name %s already exists.", taskDto.getName()));
        }
        PatrolCollectTask task = new PatrolCollectTask();
        taskDto.toModel(task);
        task.setProductName(productName);
        task.setAppName(appName);
        PatrolCollectTask saved = taskRepository.save(task);
        LOG.debug("PatrolCollectTask {} saved succeed.", saved);
        return new PatrolCollectTaskDto().fromModel(saved);
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deletePatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete PatrolCollectTask {}.{}.{} ", productName, appName, taskName);
        Optional<PatrolCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist PatrolCollectTask");
        }
        taskRepository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<PatrolCollectTaskDto> findPatrolCollectTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, QueryParam queryParam) {
        LOG.debug("Finding PatrolCollectTasks with productName: {}, appName: {} and queryParam: {}", productName,
                appName, queryParam);
        Page<PatrolCollectTask> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query PatrolCollectTasks with productName: {}, appName: {}", productName, appName);
            tasks = taskRepository.findByProductNameAndAppName(productName, appName, queryParam.pageable());
        } else {
            LOG.debug("Query PatrolCollectTasks with productName: {}, appName: {} and search", productName, appName,
                    search);
            tasks = taskRepository.findAll(
                    new PatrolCollectTaskRepository.SearchSpecification(productName, appName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new PatrolCollectTaskDto().fromModel(source));
    }

    @RequestMapping(value = "{taskName}", method = GET)
    public PatrolCollectTaskDto findPatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Find PatrolCollectTaskDto with productName: {}, appName: {}, taskName: {}", productName, appName,
                taskName);
        Optional<PatrolCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("PatrolCollectTask not exist");
        }

        return new PatrolCollectTaskDto().fromModel(findOne.get());
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public PatrolCollectTaskDto updatePatrolCollectTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody PatrolCollectTaskDto taskDto) {
        LOG.debug("Update PatrolCollectTask {}.{}.{} with PatrolCollectTaskDto: {}", productName, appName, taskName,
                taskDto);

        Optional<PatrolCollectTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist PatrolCollectTask");
        }

        PatrolCollectTask collectTask = findOne.get();
        taskDto.toModel(collectTask);
        PatrolCollectTask savedTask = taskRepository.saveAndFlush(collectTask);
        return new PatrolCollectTaskDto().fromModel(savedTask);
    }

}
