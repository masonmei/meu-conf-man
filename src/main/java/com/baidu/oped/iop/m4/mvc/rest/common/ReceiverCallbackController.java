package com.baidu.oped.iop.m4.mvc.rest.common;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;
import com.baidu.oped.iop.m4.domain.repository.common.ReceiverCallbackRepository;
import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverCallbackDto;

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
@RequestMapping("products/{productName}/apps/{appName}/callbacks")
public class ReceiverCallbackController {
    private static final Logger LOG = LoggerFactory.getLogger(ReceiverCallbackController.class);

    @Autowired
    private ReceiverCallbackRepository repository;

    @RequestMapping(method = POST)
    public ReceiverCallback createReceiverCallback(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody ReceiverCallbackDto receiverCallbackDto) {
        LOG.debug("Create ReceiverCallback for productName: {} and appName: {} with ReceiverCallbackDto: {}",
                productName, appName, receiverCallbackDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteReceiverCallback(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName) {
        LOG.debug("Delete ReceiverCallback {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<ReceiverCallback> findReceiverCallbacks(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding ReceiverCallbacks with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public ReceiverCallback updateReceiverCallback(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String taskName,
            @RequestBody ReceiverCallbackDto receiverCallbackDto) {
        LOG.debug("Update ReceiverCallback {}.{}.{} with ReceiverCallbackDto: {}", productName, appName, taskName,
                receiverCallbackDto);

        return null;
    }

}
