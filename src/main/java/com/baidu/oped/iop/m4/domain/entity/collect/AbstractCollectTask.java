package com.baidu.oped.iop.m4.domain.entity.collect;


import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;

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
    private String target = "";

    private int cycle = 60;

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
