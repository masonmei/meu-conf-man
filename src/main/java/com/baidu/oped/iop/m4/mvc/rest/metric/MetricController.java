package com.baidu.oped.iop.m4.mvc.rest.metric;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.domain.entity.metric.Metric;
import com.baidu.oped.iop.m4.domain.repository.metric.MetricRepository;
import com.baidu.oped.iop.m4.mvc.dto.metric.MetricDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;

/**
 * @author mason
 */
@RestController
@RequestMapping("products/{productName}/apps/{appName}/metrics")
public class MetricController {
    private static final Logger LOG = LoggerFactory.getLogger(MetricController.class);

    @Autowired
    private MetricRepository repository;

    @RequestMapping(method = POST)
    public MetricDto createMetric(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody MetricDto dto) {
        LOG.debug("Create Metric for productName: {} and appName: {} with MetricDto: {}", productName, appName, dto);

        Optional<Metric> findOne =
                repository.findOneByProductNameAndAppNameAndName(productName, appName, dto.getName());
        if (findOne.isPresent()) {
            throw new EntityExistsException(format("The Metric with name %s already exists.", dto.getName()));
        }
        Metric task = new Metric();
        dto.toModel(task);
        task.setProductName(productName);
        task.setAppName(appName);
        Metric saved = repository.save(task);
        LOG.debug("Metric {} saved succeed.", saved);
        return new MetricDto().fromModel(saved);
    }

    @RequestMapping(value = "{taskName}", method = DELETE)
    public void deleteMetric(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("taskName") String taskName) {
        LOG.debug("Delete Metric {}.{}.{} ", productName, appName, taskName);

    }

    @RequestMapping(method = GET)
    public List<Metric> findMetrics(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName) {
        LOG.debug("Finding Metrics with productName: {} and appName: {}", productName, appName);

        return null;
    }

    @RequestMapping(value = "{taskName}", method = PUT)
    public Metric updateMetric(@PathVariable("productName") String productName, @PathVariable("appName") String appName,
            @PathVariable("taskName") String taskName, @RequestBody MetricDto metricDto) {
        LOG.debug("Update Metric {}.{}.{} with MetricDto: {}", productName, appName, taskName, metricDto);

        return null;
    }

}
