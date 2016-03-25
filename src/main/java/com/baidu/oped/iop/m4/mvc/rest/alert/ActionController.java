package com.baidu.oped.iop.m4.mvc.rest.alert;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.repository.alert.ActionRepository;
import com.baidu.oped.iop.m4.mvc.dto.alert.ActionDto;

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
@RequestMapping("products/{productName}/apps/{appName}/actions")
public class ActionController {
    private static final Logger LOG = LoggerFactory.getLogger(ActionController.class);

    @Autowired
    private ActionRepository actionRepository;

    @RequestMapping(method = POST)
    public Action createAction(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @RequestBody ActionDto actionDto) {
        LOG.debug("Create Action for productName: {} and appName: {} with ActionDto: {}", productName, appName,
                actionDto);

        return null;
    }

    @RequestMapping(value = "{actionName}", method = DELETE)
    public void deleteAction(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("actionName") String actionName) {
        LOG.debug("Delete Action {}.{}.{} ", productName, appName, actionName);

    }

    @RequestMapping(method = GET)
    public List<Action> findActions(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding Actions with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public Action updateAction(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("taskName") String taskName, @RequestBody ActionDto actionDto) {
        LOG.debug("Update Action {}.{}.{} with ActionDto: {}", productName, appName, taskName, actionDto);

        return null;
    }

}
