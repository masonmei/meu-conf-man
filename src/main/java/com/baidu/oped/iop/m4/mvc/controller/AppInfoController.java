package com.baidu.oped.iop.m4.mvc.controller;

import com.baidu.oped.iop.m4.configuration.AppProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Application Info Controller.
 *
 * @author mason
 */
@RestController
@RequestMapping("/application")
public class AppInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(AppInfoController.class);

    @Autowired
    private AppProperties properties;

    @RequestMapping(value = {"info"}, method = RequestMethod.GET)
    public AppProperties.Info getApplicationInfo() {
        LOG.debug("invoking to get application info");
        return properties.getInfo();
    }

}
