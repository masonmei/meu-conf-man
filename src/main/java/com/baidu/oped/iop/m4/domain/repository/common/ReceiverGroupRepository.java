package com.baidu.oped.iop.m4.domain.repository.common;

import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;
import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback_;
import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup;
import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup_;
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
public interface ReceiverGroupRepository
        extends JpaRepository<ReceiverGroup, Long>, JpaSpecificationExecutor<ReceiverGroup> {
    Page<ReceiverGroup> findByProductName(String productName, Pageable pageable);

    Optional<ReceiverGroup> findOneByProductNameAndName(String productName, String name);

    class SearchSpecification implements Specification<ReceiverGroup> {

        private final String productName;
        private final String searchHeader;
        private final String search;

        public SearchSpecification(String productName, String search) {
            this.productName = productName;
            this.searchHeader = searchHeader(search);
            String searchValue = searchValue(search);
            this.search = getContainsLikePattern(searchValue);
        }


        @Override
        public Predicate toPredicate(Root<ReceiverGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Predicate pnPre = cb.equal(root.get(ReceiverGroup_.productName), productName);
            Predicate nameLike = cb.like(root.get(ReceiverGroup_.name), search);

            Predicate searchPre;
            SearchField searchField = SearchField.get(searchHeader);
            switch (searchField) {
                case NAME:
                    searchPre = cb.or(nameLike);
                    break;
                case ALL:
                default:
                    searchPre = cb.or(nameLike);
                    break;
            }

            return cb.and(pnPre, searchPre);
        }
    }
}
