package com.baidu.oped.iop.m4.domain.repository.collect;

import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask_;
import com.baidu.oped.iop.m4.domain.repository.SearchField;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author mason
 */
public interface PatrolCollectTaskRepository
        extends JpaRepository<PatrolCollectTask, Long>, JpaSpecificationExecutor<PatrolCollectTask> {

    Page<PatrolCollectTask> findByProductNameAndAppName(String productName, String appName, Pageable pageable);

    Optional<PatrolCollectTask> findOneByProductNameAndAppNameAndName(String productName, String appName, String name);

    class SearchSpecification implements Specification<PatrolCollectTask> {
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
        public Predicate toPredicate(Root<PatrolCollectTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Predicate pnPre = cb.equal(root.get(PatrolCollectTask_.productName), productName);
            Predicate anPre = cb.equal(root.get(PatrolCollectTask_.appName), appName);
            Predicate nameLike = cb.like(root.get(PatrolCollectTask_.name), search);
            Predicate targetLike = cb.like(root.get(PatrolCollectTask_.target), search);

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
}
