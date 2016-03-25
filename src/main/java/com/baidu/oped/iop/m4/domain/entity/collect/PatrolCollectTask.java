package com.baidu.oped.iop.m4.domain.entity.collect;


import static javax.persistence.EnumType.STRING;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "patrol_mon_task_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
@Document(indexName = "patrol_collect_tasks")
public class PatrolCollectTask extends AbstractCollectTask<Long> {
    private static final long serialVersionUID = 3743423308796068692L;

    @Enumerated(value = STRING)
    private PatrolCollectTaskMethod method = PatrolCollectTaskMethod.PORT;

    @Column(length = 1024)
    private String comment;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 65535)
    private String configParam = "";

    private int port;

    private int timeout;

    public PatrolCollectTaskMethod getMethod() {
        return method;
    }

    public void setMethod(PatrolCollectTaskMethod method) {
        this.method = method;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getConfigParam() {
        return configParam;
    }

    public void setConfigParam(String configParam) {
        this.configParam = configParam;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
