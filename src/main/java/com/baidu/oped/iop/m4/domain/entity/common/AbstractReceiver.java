package com.baidu.oped.iop.m4.domain.entity.common;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 * @author mason
 */
@MappedSuperclass
public abstract class AbstractReceiver<PK extends Serializable> extends ProductLayerEntity<PK> {

    private static final long serialVersionUID = 934487915183531942L;

}