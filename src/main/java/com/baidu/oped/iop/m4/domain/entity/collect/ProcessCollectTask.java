package com.baidu.oped.iop.m4.domain.entity.collect;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "proc_mon_task_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
public class ProcessCollectTask extends AbstractCollectTask<Long> {

    private static final long serialVersionUID = 3743423308796068692L;

    @Transient
    private String method = "procmon";

    @Column(length = 1024)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
