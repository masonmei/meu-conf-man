package com.baidu.oped.iop.m4.domain.repository.alert;


import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchHeader;
import static com.baidu.oped.iop.m4.mvc.param.QueryParam.searchValue;
import static com.baidu.oped.iop.m4.utils.PageUtils.getContainsLikePattern;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.domain.entity.alert.Action_;
import com.baidu.oped.iop.m4.domain.entity.alert.Notification;
import com.baidu.oped.iop.m4.domain.entity.alert.Notification_;

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
import javax.persistence.criteria.SetJoin;

/**
 * @author mason
 */
public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action> {

    Page<Action> findByProductName(String productName, Pageable pageable);

    Optional<Action> findOneByProductNameAndName(String productName, String name);

    class SearchSpecification implements Specification<Action> {

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
        public Predicate toPredicate(Root<Action> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            SetJoin<Action, Notification> join = root.join(Action_.notifications);
            Path<String> notificationTypePath = join.get(Notification_.type);
            Predicate pnPre = cb.equal(root.get(Action_.productName), productName);
            Predicate nameLike = cb.like(root.get(Action_.name), search);
            Predicate ntLike = cb.like(notificationTypePath, search);

            Predicate searchPre;
            SearchField searchField = SearchField.get(searchHeader);
            switch (searchField) {
                case NAME:
                    searchPre = cb.or(nameLike);
                    break;
                case NOTIFY_TYPE:
                    searchPre = cb.or(ntLike);
                    break;
                case ALL:
                default:
                    searchPre = cb.or(nameLike, ntLike);
                    break;
            }

            return cb.and(pnPre, searchPre);
        }
    }

    enum SearchField {
        ALL,
        NAME,
        NOTIFY_TYPE;

        public static SearchField get(String name) {
            if (StringUtils.isEmpty(name)) {
                return ALL;
            }
            for (SearchField searchField : values()) {
                if (searchField.name()
                        .equalsIgnoreCase(name)) {
                    return searchField;
                }
            }
            return ALL;
        }
    }
}
