package com.links86.trade.repo;

import com.links86.trade.domain.TAccount;
import com.links86.trade.domain.em.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-14 10:28
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
@Transactional
public interface TAccountRepo extends JpaRepository<TAccount, Long> {

    @Query(value = "select t from TAccount t where t.tUser.id=:userId and t.tUser.type =:type")
    TAccount findByUserId(@Param(value = "userId") Long userId, @Param(value = "type") AccountType type);

}

