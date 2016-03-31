package com.baidu.oped.iop.m4.custom.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Meu User Details.
 *
 * @author mason
 */
public class AppUserDetails implements UserDetails {
    private static final long serialVersionUID = 6308518180850513611L;

    private int id;
    private String name;
    @JsonProperty("zh_name")
    private String zhName;
    @JsonProperty("tel")
    private String telephone;
    private String email;
    private String bdpassport;

    private Set<AppAuthority> roles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBdpassport() {
        return bdpassport;
    }

    public void setBdpassport(String bdpassport) {
        this.bdpassport = bdpassport;
    }

    public Set<AppAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppAuthority> roles) {
        this.roles = roles;
    }

    @Override
    @JsonIgnore
    public Collection<AppAuthority> getAuthorities() {
        return roles;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return name;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
