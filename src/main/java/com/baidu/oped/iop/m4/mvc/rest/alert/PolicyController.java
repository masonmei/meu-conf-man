package com.baidu.oped.iop.m4.mvc.rest.alert;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.domain.repository.alert.PolicyRepository;
import com.baidu.oped.iop.m4.mvc.dto.alert.PolicyDto;

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
@RequestMapping("products/{productName}/apps/{appName}/policies")
public class PolicyController {
    private static final Logger LOG = LoggerFactory.getLogger(PolicyController.class);

    @Autowired
    private PolicyRepository policyRepository;

    @RequestMapping(method = POST)
    public Policy createPolicy(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @RequestBody PolicyDto policyDto) {
        LOG.debug("Create Policy for productName: {} and appName: {} with PolicyDto: {}", productName, appName,
                policyDto);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deletePolicy(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("taskName") String taskName) {
        LOG.debug("Delete Policy {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<Policy> findPolicys(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding Policies with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public Policy updatePolicy(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("taskName") String taskName, @RequestBody PolicyDto policyDto) {
        LOG.debug("Update Policy {}.{}.{} with PolicyDto: {}", productName, appName, taskName, policyDto);

        return null;
    }

}
