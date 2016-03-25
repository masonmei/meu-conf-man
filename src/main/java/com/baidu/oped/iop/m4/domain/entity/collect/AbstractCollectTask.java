package com.baidu.oped.iop.m4.domain.entity.collect;


import static org.springframework.data.elasticsearch.annotations.FieldType.String;

import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author mason
 */
@MappedSuperclass
public abstract class AbstractCollectTask<PK extends Serializable> extends AppLayerEntity<PK> {

    private static final long serialVersionUID = -4602485729212686560L;

    @NotNull
    @Column(length = 255)
    @Field(type = String)
    private String target = "";

    private int cycle = 60;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}
