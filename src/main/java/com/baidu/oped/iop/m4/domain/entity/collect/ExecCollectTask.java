package com.baidu.oped.iop.m4.domain.entity.collect;


import static com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTaskMethod.EXEC;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "exec_mon_task_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
public class ExecCollectTask extends AbstractCollectTask<Long> {
    private static final long serialVersionUID = 3743423308796068692L;

    @Enumerated(value = STRING)
    private ExecCollectTaskMethod method = EXEC;

    @Column(length = 1024)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ExecCollectTaskMethod getMethod() {
        return method;
    }

    public void setMethod(ExecCollectTaskMethod method) {
        this.method = method;
    }

}
