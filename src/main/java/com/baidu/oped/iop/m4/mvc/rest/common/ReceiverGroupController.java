package com.baidu.oped.iop.m4.mvc.rest.common;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup;
import com.baidu.oped.iop.m4.domain.repository.common.ReceiverGroupRepository;
import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverGroupDto;
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
@RequestMapping("products/{productName}/groups")
public class ReceiverGroupController {
    private static final Logger LOG = LoggerFactory.getLogger(ReceiverGroupController.class);

    @Autowired
    private ReceiverGroupRepository repository;

    @RequestMapping(method = POST)
    public ReceiverGroupDto createReceiverGroup(@PathVariable("productName") String productName,
            @RequestBody ReceiverGroupDto dto) {
        LOG.debug("Create ReceiverGroup for productName: {} with ReceiverGroupDto: {}", productName, dto);
        Optional<ReceiverGroup> findOne = repository.findOneByProductNameAndName(productName, dto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(
                    format("The ReceiverGroup with groupName %s already exists.", dto.getName()));
        }
        ReceiverGroup callback = new ReceiverGroup();
        dto.toModel(callback);
        callback.setProductName(productName);
        ReceiverGroup saved = repository.save(callback);
        LOG.debug("ReceiverCallback {} saved succeed.", saved);
        return new ReceiverGroupDto().fromModel(saved);
    }

    @RequestMapping(value = "{groupName}", method = DELETE)
    public void deleteReceiverGroup(@PathVariable("productName") String productName,
            @PathVariable("groupName") String groupName) {
        LOG.debug("Delete ReceiverGroup {}.{} ", productName, groupName);
        Optional<ReceiverGroup> findOne = repository.findOneByProductNameAndName(productName, groupName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist ReceiverGroup");
        }
        repository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<ReceiverGroupDto> findReceiverGroups(@PathVariable("productName") String productName,
            QueryParam queryParam) {
        LOG.debug("Finding ReceiverGroups with productName: {}", productName);
        Page<ReceiverGroup> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query ReceiverGroups with productName: {}", productName);
            tasks = repository.findByProductName(productName, queryParam.pageable());
        } else {
            LOG.debug("Query ReceiverGroups with productName: {} and search: {}", productName, search);
            tasks = repository.findAll(new ReceiverGroupRepository.SearchSpecification(productName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new ReceiverGroupDto().fromModel(source));
    }


    @RequestMapping(value = "{groupName}", method = GET)
    public ReceiverGroupDto findReceiverGroup(@PathVariable("productName") String productName,
            @PathVariable("groupName") String groupName) {
        LOG.debug("Find ReceiverGroup with productName: {}, groupName: {}", productName, groupName);
        Optional<ReceiverGroup> findOne = repository.findOneByProductNameAndName(productName, groupName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("ReceiverGroup not exist");
        }

        return new ReceiverGroupDto().fromModel(findOne.get());
    }

    @RequestMapping(value = "{groupName}", method = PUT)
    public ReceiverGroupDto updateReceiverGroup(@PathVariable("productName") String productName,
            @PathVariable("groupName") String groupName, @RequestBody ReceiverGroupDto dto) {
        LOG.debug("Update ReceiverGroup {}.{} with ReceiverGroupDto: {}", productName, groupName, dto);
        Optional<ReceiverGroup> findOne = repository.findOneByProductNameAndName(productName, groupName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist ReceiverGroup");
        }

        ReceiverGroup collectTask = findOne.get();
        dto.toModel(collectTask);
        ReceiverGroup savedTask = repository.saveAndFlush(collectTask);
        return new ReceiverGroupDto().fromModel(savedTask);
    }

}
