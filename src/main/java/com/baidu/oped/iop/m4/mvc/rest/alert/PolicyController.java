package com.baidu.oped.iop.m4.mvc.rest.alert;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.domain.repository.alert.ActionRepository;
import com.baidu.oped.iop.m4.domain.repository.alert.PolicyRepository;
import com.baidu.oped.iop.m4.mvc.dto.alert.PolicyDto;
import com.baidu.oped.iop.m4.mvc.param.PageData;
import com.baidu.oped.iop.m4.mvc.param.QueryParam;
import com.baidu.oped.iop.m4.utils.PageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 * @author mason
 */
@RestController
@RequestMapping("products/{productName}/apps/{appName}/policies")
public class PolicyController {
    private static final Logger LOG = LoggerFactory.getLogger(PolicyController.class);

    @Autowired
    private PolicyRepository repository;

    @Autowired
    private ActionRepository actionRepository;

    @RequestMapping(method = POST)
    public PolicyDto createPolicy(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody PolicyDto dto) {
        LOG.debug("Create Policy for productName: {} and appName: {} with PolicyDto: {}", productName, appName, dto);
        Optional<Policy> findOne =
                repository.findOneByProductNameAndAppNameAndName(productName, appName, dto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The Policy with name %s already exists.", dto.getName()));
        }
        Policy policy = new Policy();
        dto.toModel(policy);
        policy.setProductName(productName);
        policy.setAppName(appName);
        postProcessPolicy(policy);
        Policy saved = repository.save(policy);
        LOG.debug("Policy {} saved succeed.", saved);
        return new PolicyDto().fromModel(saved);
    }

    @RequestMapping(value = "{policyName}", method = DELETE)
    public void deletePolicy(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("policyName") String policyName) {
        LOG.debug("Delete Policy {}.{}.{} ", productName, appName, policyName);
        Optional<Policy> findOne = repository.findOneByProductNameAndAppNameAndName(productName, appName, policyName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot delete a not exist Policy");
        }
        repository.delete(findOne.get());
    }

    @RequestMapping(method = GET)
    public PageData<PolicyDto> findPolicies(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, QueryParam queryParam) {
        LOG.debug("Finding Policies with productName: {} and appName: {}", productName, appName);
        Page<Policy> tasks;

        String search = queryParam.getQuery();
        if (!StringUtils.hasText(search)) {
            LOG.debug("Query Policies with productName: {}, appName: {}", productName, appName);
            tasks = repository.findByProductNameAndAppName(productName, appName, queryParam.pageable());
        } else {
            LOG.debug("Query Policies with productName: {}, appName: {} and search: {}", productName, appName, search);
            tasks = repository.findAll(new PolicyRepository.SearchSpecification(productName, appName, search),
                    queryParam.pageable());
        }

        return PageUtils.buildPageData(tasks, queryParam, source -> new PolicyDto().fromModel(source));
    }

    @RequestMapping(value = "{policyName}", method = GET)
    public PolicyDto findPolicies(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("policyName") String policyName) {
        LOG.debug("Finding Policy with productName: {} appName: {} and policyName: {}", productName, appName,
                policyName);

        Optional<Policy> findOne = repository.findOneByProductNameAndAppNameAndName(productName, appName, policyName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("Policy not exist");
        }

        return new PolicyDto().fromModel(findOne.get());
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public PolicyDto updatePolicy(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @PathVariable("taskName") String policyName,
            @RequestBody PolicyDto policyDto) {
        LOG.debug("Update Policy {}.{}.{} with PolicyDto: {}", productName, appName, policyName, policyDto);

        Optional<Policy> findOne = repository.findOneByProductNameAndAppNameAndName(productName, appName, policyName);
        if (!findOne.isPresent()) {
            throw new EntityNotFoundException("You cannot update a not exist Policy");
        }

        Policy policy = findOne.get();
        policyDto.toModel(policy);
        postProcessPolicy(policy);
        Policy savedPolicy = repository.saveAndFlush(policy);
        return new PolicyDto().fromModel(savedPolicy);
    }

    private void postProcessPolicy(Policy policy) {
        Assert.notNull(policy, "Policy must not be null while processing actions.");
        if (!CollectionUtils.isEmpty(policy.getIncidentActions())) {
            Set<Action> incidentActions = policy.getIncidentActions()
                    .stream()
                    .map(action -> actionRepository.findOneByProductNameAndName(policy.getProductName(), action.getName()))
                    .collect(Collectors.toSet())
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            policy.setIncidentActions(incidentActions);
        }

        if (!CollectionUtils.isEmpty(policy.getInsufficientActions())) {
            Set<Action> insufficientActions = policy.getInsufficientActions()
                    .stream()
                    .map(action -> actionRepository.findOneByProductNameAndName(policy.getProductName(), action.getName()))
                    .collect(Collectors.toSet())
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            policy.setInsufficientActions(insufficientActions);
        }

        if (!CollectionUtils.isEmpty(policy.getResumeActions())) {
            Set<Action> resumeActions = policy.getResumeActions()
                    .stream()
                    .map(action -> actionRepository.findOneByProductNameAndName(policy.getProductName(),
                            action.getName()))
                    .collect(Collectors.toSet())
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            policy.setResumeActions(resumeActions);
        }
    }

}
