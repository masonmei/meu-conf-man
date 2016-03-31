package com.baidu.oped.iop.m4.mvc.rest.alert;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.repository.alert.ActionRepository;
import com.baidu.oped.iop.m4.mvc.dto.alert.ActionDto;
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
@RequestMapping("products/{productName}/actions")
public class ActionController {
    private static final Logger LOG = LoggerFactory.getLogger(ActionController.class);

    @Autowired
    private ActionRepository repository;

    @RequestMapping(method = POST)
    public ActionDto createAction(@PathVariable("productName") String productName, @RequestBody ActionDto actionDto) {
        LOG.debug("Create Action for productName: {} with ActionDto: {}", productName, actionDto);
        Optional<Action> findOne = repository.findOneByProductNameAndName(productName, actionDto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The Action with name %s already exists.", actionDto.getName()));
        }
        Action task = new Action();
        actionDto.toModel(task);
        task.setProductName(productName);
        Action saved = repository.save(task);
        LOG.debug("Action {} saved succeed.", saved);
        return new ActionDto().fromModel(saved);
    }

    @RequestMapping(value = "{actionName}", method = DELETE)
    public void deleteAction(@PathVariable("productName") String productName,
            @PathVariable("actionName") String actionName) {
        LOG.debug("Delete Action {}.{} ", productName, actionName);
        Optional<Action> findOne = repository.findOneByProductNameAndName(productName, actionName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist DerivedTask");
        }
        repository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<ActionDto> findActions(@PathVariable("productName") String productName, QueryParam queryParam) {
        LOG.debug("Finding Actions with productName: {}", productName);
        Page<Action> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query Actions with productName: {}", productName);
            tasks = repository.findByProductName(productName, queryParam.pageable());
        } else {
            LOG.debug("Query Actions with productName: {} and search: {}", productName, search);
            tasks = repository.findAll(new ActionRepository.SearchSpecification(productName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new ActionDto().fromModel(source));
    }

    @RequestMapping(value = "{actionName}", method = GET)
    public ActionDto findDAction(@PathVariable("productName") String productName,
            @PathVariable("actionName") String actionName) {
        LOG.debug("Find DerivedTask with productName: {}, actionName: {}", productName, actionName);
        Optional<Action> findOne = repository.findOneByProductNameAndName(productName, actionName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("Action not exist");
        }

        return new ActionDto().fromModel(findOne.get());
    }

    @RequestMapping(value = "{actionName}", method = PUT)
    public ActionDto updateAction(@PathVariable("productName") String productName,
            @PathVariable("actionName") String taskName, @RequestBody ActionDto actionDto) {
        LOG.debug("Update Action {}.{} with ActionDto: {}", productName, taskName, actionDto);

        Optional<Action> findOne = repository.findOneByProductNameAndName(productName, taskName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist Action");
        }

        Action collectTask = findOne.get();
        actionDto.toModel(collectTask);
        Action savedTask = repository.saveAndFlush(collectTask);
        return new ActionDto().fromModel(savedTask);
    }

}
