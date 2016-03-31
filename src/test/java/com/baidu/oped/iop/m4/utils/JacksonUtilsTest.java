package com.baidu.oped.iop.m4.utils;

import com.baidu.oped.iop.m4.mvc.dto.alert.TimeRangeDto;

import org.junit.Test;

import java.time.LocalTime;

/**
 * Test cases for class .
 *
 * @author mason
 */
public class JacksonUtilsTest {

    @Test
    public void deserialize() throws Exception {
        TimeRangeDto timeRange = new TimeRangeDto();
        timeRange.setFrom(LocalTime.now().minusMinutes(1));
//        timeRange.setTo(LocalTime.now());
        String serialize = JacksonUtils.serialize(timeRange);
        System.out.println(serialize);
    }

    @Test
    public void deserialize1() throws Exception {

    }

    @Test
    public void serialize() throws Exception {

    }
}