package com.links86.trade.repo;

import com.links86.trade.domain.TLoanDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单明细
 * Created by leoxu on 2016/11/10.
 */
@Transactional
@Repository
public interface TLoanDetailRepo extends JpaRepository<TLoanDetail, Long> {

    Page<TLoanDetail> findAll(Specification<TLoanDetail> specification, Pageable page);

    @Query("select sum(a.commodityAmount*a.count) from TLoanDetail a where a.tLoan.id = :lid")
    BigDecimal computeTotalAmount(@Param(value = "lid")long lid);

    @Query("select max(a.sellerAccount.id) from TLoanDetail a where a.tLoan.id=:lid")
    Long findSellerId(@Param(value = "lid")long lid);
}