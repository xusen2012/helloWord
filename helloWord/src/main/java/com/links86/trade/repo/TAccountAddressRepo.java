package com.links86.trade.repo;

import com.links86.trade.domain.TAccountAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project name is links86-system
 * Created by xuli on 2016-10-10 21:47
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
@Transactional
public interface TAccountAddressRepo extends JpaRepository<TAccountAddress, Long> {
    //按给定条件完成精准查询或者模糊查询
    Page<TAccountAddress> findAll(Specification<TAccountAddress> specification, Pageable page);

    @Query("select a from TAccountAddress a where a.isDefault = 1 and a.tAccount.id = :aid")
    TAccountAddress findDefault(@Param(value = "aid") Long aid);
}
