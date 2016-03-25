package com.baidu.oped.iop.m4.mvc.rest.audit;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.baidu.oped.iop.m4.domain.entity.audit.AuditHistory;
import com.baidu.oped.iop.m4.domain.repository.audit.AuditHistoryRepository;
import com.baidu.oped.iop.m4.mvc.dto.audit.AuditHistoryDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mason
 */
@RestController
@RequestMapping("products/{productName}/apps/{appName}/auditHistories")
public class AuditHistoryController {
    private static final Logger LOG = LoggerFactory.getLogger(AuditHistoryController.class);

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    @RequestMapping(method = POST)
    public AuditHistory createAuditHistory(@PathVariable("productName") String productName,
            @PathVariable("appName") String appName, @RequestBody AuditHistoryDto auditHistoryDto) {
        LOG.debug("Create AuditHistory for productName: {} and appName: {} with AuditHistoryDto: {}", productName,
                appName, auditHistoryDto);

        return null;
    }

}
