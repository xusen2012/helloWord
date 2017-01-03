package com.links86.trade.repo;

import com.links86.trade.domain.TAccount;
import com.links86.trade.domain.TAccountDetail;
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
 * Project name is links86-system
 * Created by xuli on 2016-09-13 16:04
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Transactional
@Repository
public interface TAccountDetailRepo extends JpaRepository<TAccountDetail, Long> {
    Page<TAccountDetail> findAll(Specification<TAccountDetail> specification, Pageable page);

    TAccountDetail findByNumber(@Param(value = "number") long number);

    @Query("SELECT sum(a.amount) FROM TAccountDetail a WHERE a.tAccount.id = :id")
    BigDecimal computeTotalAmount(@Param(value = "id") Long id);
}
