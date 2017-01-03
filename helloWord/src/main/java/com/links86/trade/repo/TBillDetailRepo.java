package com.links86.trade.repo;

import com.links86.trade.domain.TBillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 *by xusen
 */
@Repository
@Transactional
public interface TBillDetailRepo extends JpaRepository<TBillDetail, Long> {

    Page<TBillDetail> findAll(Specification<TBillDetail> specification, Pageable page);

    @Query("SELECT sum(a.amount) FROM TBillDetail a where a.amount<0 and a.accountId = :accountId")
    BigDecimal computeExAmount(@Param(value = "accountId") Long accountId);

    @Query("SELECT sum(a.amount) FROM TBillDetail a where a.amount>0 and a.accountId=:accountId")
    BigDecimal computeInAmount(@Param(value = "accountId") Long accountId);


}
