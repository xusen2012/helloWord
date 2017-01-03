package com.links86.trade.domain.to;

import com.links86.trade.domain.TLoanDetail;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * Created by hand on 2016/11/15.
 */
public class Test {

    public static void main(String arg[]){
        String idStr=null;
        String sellerId=null;
        Specification<TLoanDetail> specification =
                (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                   Predicate predicate= cb.equal(root.get("Student").get("id"), idStr);
                    query.getRestriction();
            return null;
        };

    }
}
