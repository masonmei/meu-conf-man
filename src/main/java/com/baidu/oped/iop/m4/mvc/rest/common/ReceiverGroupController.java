package com.baidu.oped.iop.m4.mvc.rest.common;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup;
import com.baidu.oped.iop.m4.domain.repository.common.ReceiverGroupRepository;
import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverGroupDto;

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
@RequestMapping("products/{productName}/groups")
public class ReceiverGroupController {
    private static final Logger LOG = LoggerFactory.getLogger(ReceiverGroupController.class);

    @Autowired
    private ReceiverGroupRepository repository;

    @RequestMapping(method = POST)
    public ReceiverGroup createReceiverGroup(@PathVariable("productName") String productName,
            @RequestBody ReceiverGroupDto receiverGroupDto) {
        LOG.debug("Create ReceiverGroup for productName: {} with ReceiverGroupDto: {}", productName, receiverGroupDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteReceiverGroup(@PathVariable("productName") String productName,
            @PathVariable("taskName") String taskName) {
        LOG.debug("Delete ReceiverGroup {}.{} ", productName, taskName);

    }

    @RequestMapping(method = GET)
    public List<ReceiverGroup> findReceiverGroups(@PathVariable("productName") String productName) {
        LOG.debug("Finding ReceiverGroups with productName: {}", productName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public ReceiverGroup updateReceiverGroup(@PathVariable("productName") String productName,
            @PathVariable("taskName") String taskName, @RequestBody ReceiverGroupDto receiverGroupDto) {
        LOG.debug("Update ReceiverGroup {}.{} with ReceiverGroupDto: {}", productName, taskName,
                receiverGroupDto);

        return null;
    }

}
