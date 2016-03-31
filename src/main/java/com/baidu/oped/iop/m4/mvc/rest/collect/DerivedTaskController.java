package com.baidu.oped.iop.m4.mvc.rest.collect;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;
import com.baidu.oped.iop.m4.domain.repository.collect.DerivedTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.DerivedTaskDto;
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
@RequestMapping("products/{productName}/apps/{appName}/derivedTasks")
public class DerivedTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(DerivedTaskController.class);

    @Autowired
    private DerivedTaskRepository taskRepository;

    @RequestMapping(method = POST)
    public DerivedTaskDto createDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody DerivedTaskDto taskDto) {
        LOG.debug("Create DerivedTask for productName: {} and appName: {} with DerivedTaskDto: {}", productName,
                appName, taskDto);
        Optional<DerivedTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskDto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The task with name %s already exists.", taskDto.getName()));
        }
        DerivedTask task = new DerivedTask();
        taskDto.toModel(task);
        task.setProductName(productName);
        task.setAppName(appName);
        DerivedTask saved = taskRepository.save(task);
        LOG.debug("DerivedTask {} saved succeed.", saved);
        return new DerivedTaskDto().fromModel(saved);
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete DerivedTask {}.{}.{} ", productName, appName, taskName);
        Optional<DerivedTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist DerivedTask");
        }
        taskRepository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<DerivedTaskDto> findDerivedTasks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, QueryParam queryParam) {
        LOG.debug("Finding DerivedTasks with productName: {} and appName: {}", productName, appName);
        Page<DerivedTask> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query DerivedTasks with productName: {}, appName: {}", productName, appName);
            tasks = taskRepository.findByProductNameAndAppName(productName, appName, queryParam.pageable());
        } else {
            LOG.debug("Query DerivedTasks with productName: {}, appName: {} and search: {}", productName, appName,
                    search);
            tasks = taskRepository.findAll(new DerivedTaskRepository.SearchSpecification(productName, appName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new DerivedTaskDto().fromModel(source));
    }


    @RequestMapping(value = "{taskName}", method = GET)
    public DerivedTaskDto findDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Find DerivedTask with productName: {}, appName: {}, taskName: {}", productName, appName, taskName);
        Optional<DerivedTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("DerivedTask not exist");
        }

        return new DerivedTaskDto().fromModel(findOne.get());
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public DerivedTaskDto updateDerivedTask(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody DerivedTaskDto taskDto) {
        LOG.debug("Update DerivedTask {}.{}.{} with DerivedTaskDto: {}", productName, appName, taskName, taskDto);

        Optional<DerivedTask> findOne =
                taskRepository.findOneByProductNameAndAppNameAndName(productName, appName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist DerivedTask");
        }

        DerivedTask collectTask = findOne.get();
        taskDto.toModel(collectTask);
        DerivedTask savedTask = taskRepository.saveAndFlush(collectTask);
        return new DerivedTaskDto().fromModel(savedTask);
    }

}
