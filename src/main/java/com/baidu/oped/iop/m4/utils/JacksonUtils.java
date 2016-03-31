package com.baidu.oped.iop.m4.utils;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jackson Utils.
 *
 * @author mason
 */
public abstract class JacksonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);

    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(NON_NULL);
    }

    /**
     * Json String to Java Bean Object.
     *
     * @param jsonStr   json string present object.
     * @param valueType Java Bean Type.
     * @return Java Bean Object of the given type.
     */
    public static <T> T deserialize(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            LOG.warn("Deserialize {} to {} failed.", jsonStr, valueType);
        }

        return null;
    }

    /**
     * Json String to Java Bean Object.
     *
     * @param jsonStr      json string present object.
     * @param valueTypeRef Java Bean Type.
     * @return Java Bean Object of the given type.
     */
    public static <T> T deserialize(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            LOG.warn("Deserialize {} to {} failed.", jsonStr, valueTypeRef);
        }

        return null;
    }

    /**
     * Java Bean Object to Json String.
     *
     * @param object object to serialize
     * @param <T>    type of object
     * @return Json string represent object.
     */
    public static <T> String serialize(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            LOG.warn("Serialize {} to json string failed.", object);
        }

        return null;
    }
}
