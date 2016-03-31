package com.baidu.oped.iop.m4.domain.repository.collect;


import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;
import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask_;
import com.baidu.oped.iop.m4.domain.repository.SearchField;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author mason
 */
public interface DerivedTaskRepository extends JpaRepository<DerivedTask, Long>, JpaSpecificationExecutor<DerivedTask> {
    Page<DerivedTask> findByProductNameAndAppName(String productName, String appName, Pageable pageable);

    Optional<DerivedTask> findOneByProductNameAndAppNameAndName(String productName, String appName, String name);

    class SearchSpecification implements Specification<DerivedTask> {
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
        public Predicate toPredicate(Root<DerivedTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Predicate pnPre = cb.equal(root.get(DerivedTask_.productName), productName);
            Predicate anPre = cb.equal(root.get(DerivedTask_.appName), appName);
            Predicate nameLike = cb.like(root.get(DerivedTask_.name), search);
            Predicate formulaLike = cb.like(root.get(DerivedTask_.formula), search);

            Predicate searchPre;
            SearchField searchField = SearchField.get(searchHeader);
            switch (searchField) {
                case NAME:
                    searchPre = cb.or(nameLike);
                    break;
                case FORMULA:
                    searchPre = cb.or(formulaLike);
                    break;
                case ALL:
                default:
                    searchPre = cb.or(nameLike, formulaLike);
                    break;
            }
            return cb.and(pnPre, anPre, searchPre);
        }
    }
}
