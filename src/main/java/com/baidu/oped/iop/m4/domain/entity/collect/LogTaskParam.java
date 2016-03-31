package com.baidu.oped.iop.m4.domain.entity.collect;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * @author mason
 */
public class LogTaskParam implements Serializable {
    private static final long serialVersionUID = 660382463129000940L;

    @NotNull
    @Column(name = "log_filepath", nullable = false)
    private String logFilepath;

    @NotNull
    private int limitRate;

    @NotNull
    @Column(length = 512)
    private String matchStr;

    @NotNull
    @Column(length = 512)
    private String preMatch;

    @NotNull
    @Column(name = "`value`", length = 128)
    private String value;

    @NotNull
    @Column(length = 255)
    private String tags;

    @NotNull
    @Column(name = "`condition`", length = 255)
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(int limitRate) {
        this.limitRate = limitRate;
    }

    public String getLogFilepath() {
        return logFilepath;
    }

    public void setLogFilepath(String logFilepath) {
        this.logFilepath = logFilepath;
    }

    public String getMatchStr() {
        return matchStr;
    }

    public void setMatchStr(String matchStr) {
        this.matchStr = matchStr;
    }

    public String getPreMatch() {
        return preMatch;
    }

    public void setPreMatch(String preMatch) {
        this.preMatch = preMatch;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
