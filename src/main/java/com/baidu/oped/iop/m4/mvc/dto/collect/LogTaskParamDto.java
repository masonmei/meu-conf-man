package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.LogTaskParam;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

/**
 * @author mason
 */
public class LogTaskParamDto implements Dto<LogTaskParam>{
    private String logFilepath;
    private int limitRate;
    private String matchStr;
    private String preMatch;
    private String value;
    private String tags;
    private String condition;

    @Override
    public LogTaskParamDto fromModel(LogTaskParam model) {
        this.condition = model.getCondition();
        this.limitRate = model.getLimitRate();
        this.logFilepath = model.getLogFilepath();
        this.matchStr = model.getMatchStr();
        this.preMatch = model.getPreMatch();
        this.tags = model.getTags();
        this.value = model.getValue();
        return this;
    }

    @Override
    public void toModel(LogTaskParam model) {
        model.setLimitRate(limitRate);
        model.setLogFilepath(logFilepath);
        model.setMatchStr(matchStr);
        model.setPreMatch(preMatch);
        model.setValue(value);
        model.setTags(tags);
        model.setCondition(condition);
    }

    public String getLogFilepath() {
        return logFilepath;
    }

    public void setLogFilepath(String logFilepath) {
        this.logFilepath = logFilepath;
    }

    public int getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(int limitRate) {
        this.limitRate = limitRate;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
