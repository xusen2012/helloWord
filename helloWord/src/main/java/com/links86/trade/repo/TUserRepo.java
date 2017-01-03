package com.links86.trade.repo;

import com.links86.trade.domain.TUser;
import com.links86.trade.domain.em.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-12 12:02
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
@Transactional
public interface TUserRepo extends JpaRepository<TUser, Long> {
    Page<TUser> findAll(Specification<TUser> specification, Pageable page);

    @Query(value = "select t from TUser t where t.outUid = :ouid")
    TUser findByOutUID(@Param(value = "ouid") long ouid);
}
