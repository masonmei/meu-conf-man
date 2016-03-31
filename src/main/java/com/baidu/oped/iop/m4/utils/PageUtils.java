package com.baidu.oped.iop.m4.utils;

import com.baidu.oped.iop.m4.mvc.param.PageData;
import com.baidu.oped.iop.m4.mvc.param.QueryParam;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

/**
 * Page And Query Utils.
 *
 * @author mason
 */
public class PageUtils {
    /**
     * Build page result with Spring Data Jpa query result and params.
     *
     * @param page      jpa query result
     * @param param     user request param
     * @param converter convert to translate from model to dto
     * @param <S>       model
     * @param <T>       dto
     * @return page data
     */
    public static <S, T> PageData<T> buildPageData(Page<S> page, QueryParam param, Converter<S, T> converter) {
        PageData<T> pageData = new PageData<>();
        pageData.setContent(page.getContent()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
        pageData.setTotalPages(page.getTotalPages());
        pageData.setTotalElements(page.getTotalElements());
        pageData.setPageElements(page.getNumberOfElements());
        pageData.setPageNumber(param.getPageNumber());
        pageData.setPageSize(page.getSize());
        pageData.setFirst(page.isFirst());
        pageData.setLast(page.isLast());
        pageData.setFields(param.getFields());
        pageData.setOrderBy(param.getOrderBy());
        pageData.setQuery(param.getQuery());

        return pageData;
    }

    /**
     * Build a like pattern with given search term
     *
     * @param searchTerm search content
     * @return like pattern
     */
    public static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm + "%";
        }
    }
}
