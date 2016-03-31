package com.baidu.oped.iop.m4.custom.security;

import static java.lang.String.format;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * Meu Authority.
 *
 * @author mason
 */
public class AppAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 7700631575972947758L;

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("role_id")
    private int roleId;
    @JsonProperty("role_name")
    private String roleName;

    AppAuthority() {
    }

    public AppAuthority(String productName, String roleName) {
        Assert.hasText(productName, "Product Name must have content.");
        Assert.hasText(roleName, "Product Role name must have content.");
        this.productName = productName;
        this.roleName = roleName;
    }

    public String getProductName() {
        return productName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return format("%s:%s", productName, roleName);
    }
}
