package com.baidu.oped.iop.m4.mvc.rest.common;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;
import com.baidu.oped.iop.m4.domain.repository.common.ReceiverCallbackRepository;
import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverCallbackDto;
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
@RequestMapping("products/{productName}/callbacks")
public class ReceiverCallbackController {
    private static final Logger LOG = LoggerFactory.getLogger(ReceiverCallbackController.class);

    @Autowired
    private ReceiverCallbackRepository repository;

    @RequestMapping(method = POST)
    public ReceiverCallbackDto createReceiverCallback(@PathVariable("productName") String productName,
            @RequestBody ReceiverCallbackDto dto) {
        LOG.debug("Create ReceiverCallback for productName: {} with ReceiverCallbackDto: {}", productName, dto);
        Optional<ReceiverCallback> findOne = repository.findOneByProductNameAndName(productName, dto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The ReceiverCallback with name %s already exists.", dto.getName()));
        }
        ReceiverCallback callback = new ReceiverCallback();
        dto.toModel(callback);
        callback.setProductName(productName);
        ReceiverCallback saved = repository.save(callback);
        LOG.debug("ReceiverCallback {} saved succeed.", saved);
        return new ReceiverCallbackDto().fromModel(saved);
    }

    @RequestMapping(value = "{callbackName}", method = DELETE)
    public void deleteReceiverCallback(@PathVariable("productName") String productName,
            @PathVariable("callbackName") String name) {
        LOG.debug("Delete ReceiverCallback {}.{} ", productName, name);
        Optional<ReceiverCallback> findOne = repository.findOneByProductNameAndName(productName, name);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist ReceiverCallback");
        }
        repository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<ReceiverCallbackDto> findReceiverCallbacks(@PathVariable("productName") String productName,
            QueryParam queryParam) {
        LOG.debug("Finding ReceiverCallbacks with productName: {}", productName);
        Page<ReceiverCallback> tasks;
        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query ReceiverCallbacks with productName: {}", productName);
            tasks = repository.findByProductName(productName, queryParam.pageable());
        } else {
            LOG.debug("Query ReceiverCallbacks with productName: {} and search: {}", productName, search);
            tasks = repository.findAll(new ReceiverCallbackRepository.SearchSpecification(productName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new ReceiverCallbackDto().fromModel(source));
    }

    @RequestMapping(value = "{callbackName}", method = GET)
    public ReceiverCallbackDto findReceiverCallback(@PathVariable("productName") String productName,
            @PathVariable("callbackName") String name) {
        LOG.debug("Find ReceiverCallback with productName: {}, name: {}", productName, name);
        Optional<ReceiverCallback> findOne = repository.findOneByProductNameAndName(productName, name);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("ReceiverCallback not exist");
        }

        return new ReceiverCallbackDto().fromModel(findOne.get());
    }


    @RequestMapping(value = "{callbackName}", method = PUT)
    public ReceiverCallbackDto updateReceiverCallback(@PathVariable("productName") String productName,
            @PathVariable("callbackName") String name, @RequestBody ReceiverCallbackDto dto) {
        LOG.debug("Update ReceiverCallback {}.{} with ReceiverCallbackDto: {}", productName, name, dto);
        Optional<ReceiverCallback> findOne = repository.findOneByProductNameAndName(productName, name);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist ReceiverCallback");
        }

        ReceiverCallback collectTask = findOne.get();
        dto.toModel(collectTask);
        ReceiverCallback savedTask = repository.saveAndFlush(collectTask);
        return new ReceiverCallbackDto().fromModel(savedTask);
    }

}
