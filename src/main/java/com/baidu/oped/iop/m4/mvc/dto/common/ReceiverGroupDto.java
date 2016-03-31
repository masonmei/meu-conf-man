package com.baidu.oped.iop.m4.mvc.dto.common;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup;

import java.util.Date;
import java.util.Set;

/**
 * Data translate object of ReceiverGroup.
 *
 * @author mason
 */
public class ReceiverGroupDto extends AbstractProductLayerAuditableDto<ReceiverGroup> {

    private String description;
    private Set<String> members;

    @Override
    public ReceiverGroupDto fromModel(ReceiverGroup receiverGroup) {
        super.initProcess(receiverGroup);
        this.description = receiverGroup.getDescription();
        this.members = receiverGroup.getMembers();
        return this;
    }

    @Override
    public void toModel(ReceiverGroup receiverGroup) {
        receiverGroup.setName(getName());
        receiverGroup.setDescription(description);
        receiverGroup.setMembers(members);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }
}
