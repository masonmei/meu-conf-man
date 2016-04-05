package com.baidu.oped.iop.m4.mvc.controller;

import static com.baidu.oped.sia.boot.utils.RequestUtils.getTraceId;
import static com.baidu.oped.sia.boot.utils.RequestUtils.getTraceSourceIp;
import static com.baidu.oped.sia.boot.utils.RequestUtils.getTraceStartTime;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;

import com.baidu.oped.iop.m4.configuration.AppProperties;
import com.baidu.oped.sia.boot.common.BaseResponse;
import com.baidu.oped.sia.boot.exception.access.IpForbiddenException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application Info Controller.
 *
 * @author mason
 */
@RestController
@RequestMapping("application")
public class AppInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(AppInfoController.class);

    @Autowired
    private AppProperties properties;

    @RequestMapping(value = {"info"}, method = RequestMethod.GET)
    public AppProperties.Info getApplicationInfo() {
        LOG.debug("invoking to get application info");
        return properties.getInfo();
    }

    @RequestMapping(value = "error")
    public BaseResponse handleError(HttpServletRequest request, HttpServletResponse response){
        BaseResponse.Builder builder = BaseResponse.builder().requestId(getTraceId())
                .traceStartTime(getTraceStartTime())
                .traceSourceIp(getTraceSourceIp());

        Object attribute = request.getAttribute(ERROR_EXCEPTION);
        if(attribute != null && Throwable.class.isAssignableFrom(attribute.getClass())){
            Throwable ex = (Throwable) attribute;
            RequestContext requestContext = new RequestContext(request);
            builder.message(requestContext.getMessage(ex.getMessage()));
        }
        return builder.build();
    }

}
