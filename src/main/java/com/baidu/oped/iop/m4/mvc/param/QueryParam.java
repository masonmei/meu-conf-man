package com.baidu.oped.iop.m4.mvc.param;

import static com.baidu.oped.sia.boot.utils.Constrains.Datetime.DATE_TIME_FORMAT;

import static org.springframework.util.StringUtils.trimLeadingCharacter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mason
 */
public class QueryParam {
    private String query;
    private String[] fields;
    private String[] orderBy;
    private int pageNumber;
    private int pageSize;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private Date from;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private Date to;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Pageable pageable() {
        int page = 0;
        int size = 10;
        if (pageNumber > 0) {
            page = pageNumber;
        }

        if (pageSize > 0) {
            size = pageSize;
        }

        Sort sort = buildSort();

        return new PageRequest(page, size, sort);
    }

    public static String searchHeader(String query) {
        Assert.hasText(query, "Query String must have text.");
        if (query.contains(":")) {
            return query.substring(0, query.indexOf(":"));
        }
        return null;
    }

    public static String searchValue(String query) {
        Assert.hasText(query, "Query String must have text.");
        if (query.contains(":")) {
            return trimLeadingCharacter(query.substring(query.indexOf(":")), ':');
        }
        return query;
    }

    private Sort buildSort() {
        if (null == orderBy) {
            return null;
        }
        List<Sort.Order> orders = new ArrayList<>();
        for (String order : orderBy) {
            String sig = "+";
            String prop = trimLeadingCharacter(order, '+');
            if (order.startsWith("-")) {
                sig = "-";
                prop = trimLeadingCharacter(order, '-');
            }

            switch (sig) {
                case "-":
                    orders.add(new Sort.Order(Sort.Direction.DESC, prop));
                case "+":
                default:
                    orders.add(new Sort.Order(Sort.Direction.ASC, prop));
                    break;
            }
        }
        return new Sort(orders);
    }
}
