package com.baidu.oped.iop.m4.domain.entity.common;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "product_receiver_group_unique_index", columnList = "`product_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`")})
@Document(indexName = "receiver_groups")
public class ReceiverGroup extends AbstractReceiver<Long> implements Receiver {

    private static final long serialVersionUID = 4291078795956514729L;
    private static final String JOIN_COLUMN_NAME = "receiver_group_id";

    private String description;

    @ElementCollection
    private Set<String> members = new HashSet<>();

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
