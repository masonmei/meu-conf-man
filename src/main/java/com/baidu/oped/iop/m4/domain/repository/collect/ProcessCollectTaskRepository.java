package com.baidu.oped.iop.m4.domain.repository.collect;

import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask_;
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
public interface ProcessCollectTaskRepository
        extends JpaRepository<ProcessCollectTask, Long>, JpaSpecificationExecutor<ProcessCollectTask> {
    Optional<ProcessCollectTask> findOneByProductNameAndAppNameAndName(String productName, String appName, String name);

    Page<ProcessCollectTask> findByProductNameAndAppName(String productName, String appName, Pageable pageable);

    class SearchSpecification implements Specification<ProcessCollectTask> {
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
        public Predicate toPredicate(Root<ProcessCollectTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Predicate pnPre = cb.equal(root.get(ProcessCollectTask_.productName), productName);
            Predicate anPre = cb.equal(root.get(ProcessCollectTask_.appName), appName);
            Predicate nameLike = cb.like(root.get(ProcessCollectTask_.name), search);
            Predicate targetLike = cb.like(root.get(ProcessCollectTask_.target), search);

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
