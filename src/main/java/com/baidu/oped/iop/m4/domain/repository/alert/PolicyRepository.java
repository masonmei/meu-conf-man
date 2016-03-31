package com.baidu.oped.iop.m4.domain.repository.alert;


import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.alert.Formula;
import com.baidu.oped.iop.m4.domain.entity.alert.Formula_;
import com.baidu.oped.iop.m4.domain.entity.alert.MonitoringObject;
import com.baidu.oped.iop.m4.domain.entity.alert.MonitoringObject_;
import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.domain.entity.alert.Policy_;
import com.baidu.oped.iop.m4.domain.repository.SearchField;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author mason
 */
public interface PolicyRepository extends JpaRepository<Policy, Long>, JpaSpecificationExecutor<Policy> {

    Page<Policy> findByProductNameAndAppName(String productName, String appName, Pageable pageable);

    Optional<Policy> findOneByProductNameAndAppNameAndName(String productName, String appName, String name);

    class SearchSpecification implements Specification<Policy> {
        private final String productName;
        private final String appName;
        private final String searchHeader;
        private final String search;

        public SearchSpecification(String productName, String appName, String search) {
            this.productName = productName;
            this.appName = appName;
            this.searchHeader = searchHeader(search);
            String searchValue = searchValue(search);
            this.search = getContainsLikePattern(searchValue);
        }

        @Override
        public Predicate toPredicate(Root<Policy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<MonitoringObject> moPath = root.get(Policy_.monitoringObject);
            Path<Formula> formulaPath = root.get(Policy_.formula);
            Predicate pnPre = cb.equal(root.get(Policy_.productName), productName);
            Predicate anPre = cb.equal(root.get(Policy_.appName), appName);
            Predicate nameLike = cb.like(root.get(Policy_.name), search);
            Predicate moNameLike = cb.like(moPath.get(MonitoringObject_.name), search);
            Predicate metricLike = cb.like(formulaPath.get(Formula_.metric), search);

            Predicate searchPre;
            SearchField searchField = SearchField.get(searchHeader);
            switch (searchField) {
                case NAME:
                    searchPre = cb.or(nameLike);
                    break;
                case MONITORING_OBJECT:
                    searchPre = cb.or(moNameLike);
                    break;
                case FORMULA:
                    searchPre = cb.or(metricLike);
                    break;
                case ALL:
                default:
                    searchPre = cb.or(nameLike, moNameLike, metricLike);
                    break;
            }
            return cb.and(pnPre, anPre, searchPre);
        }
    }
}
