package com.baidu.oped.iop.m4.domain.entity.collect;


import com.baidu.oped.iop.m4.utils.JacksonUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "log_mon_task_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
@Document(indexName = "log_collect_tasks")
public class LogCollectTask extends AbstractCollectTask<Long> {

    private static final long serialVersionUID = 3616649158184701551L;

    @Transient
    private String method = "logmon";

    @Column(length = 1024)
    private String comment;

    @JsonIgnore
    @Basic(optional = false)
    @Lob
    private String params;

    @JsonProperty("params")
    @Transient
    private LogTaskParam logTaskParam;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getParams() {
        if (params == null) {
            params = JacksonUtils.serialize(logTaskParam);
        }
        return params;
    }

    public void setParams(String params) {
        this.params = params;
        this.logTaskParam = JacksonUtils.deserialize(params, LogTaskParam.class);
    }

    public LogTaskParam getLogTaskParam() {
        if (logTaskParam == null) {
            this.logTaskParam = JacksonUtils.deserialize(params, LogTaskParam.class);
        }
        return logTaskParam;
    }

    public void setLogTaskParam(LogTaskParam logTaskParam) {
        this.params = JacksonUtils.serialize(logTaskParam);
        this.logTaskParam = logTaskParam;
    }
}
