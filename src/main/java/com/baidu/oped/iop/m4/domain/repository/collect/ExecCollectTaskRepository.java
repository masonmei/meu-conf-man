package com.baidu.oped.iop.m4.domain.repository.collect;


import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask_;

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
public interface ExecCollectTaskRepository
        extends JpaRepository<ExecCollectTask, Long>, JpaSpecificationExecutor<ExecCollectTask> {

    Page<ExecCollectTask> findByProductNameAndAppName(String productName, String appName, Pageable pageable);

    Optional<ExecCollectTask> findOneByProductNameAndAppNameAndName(String productName, String appName, String name);

    class SearchSpecification implements Specification<ExecCollectTask> {
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
        public Predicate toPredicate(Root<ExecCollectTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Predicate pnPre = cb.equal(root.get(ExecCollectTask_.productName), productName);
            Predicate anPre = cb.equal(root.get(ExecCollectTask_.appName), appName);
            Predicate nameLike = cb.like(root.get(ExecCollectTask_.name), search);
            Predicate targetLike = cb.like(root.get(ExecCollectTask_.target), search);

            Predicate searchPre;
            SearchField searchField = SearchField.get(searchHeader);
            switch (searchField) {
                case NAME:
                    searchPre = cb.or(nameLike);
                    break;
                case TARGET:
                    searchPre = cb.or(targetLike);
                    break;
                case ALL:
                default:
                    searchPre = cb.or(nameLike, targetLike);
                    break;
            }
            return cb.and(pnPre, anPre, searchPre);
        }
    }

    enum SearchField {
        ALL,
        NAME,
        TARGET;

        public static SearchField get(String name){
            if (name == null) {
                return ALL;
            }
            for (SearchField searchField : values()) {
                if(searchField.name().equalsIgnoreCase(name)){
                    return searchField;
                }
            }
            return ALL;
        }
    }
}
