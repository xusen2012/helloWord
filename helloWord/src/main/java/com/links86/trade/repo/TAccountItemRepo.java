package com.links86.trade.repo;

import com.links86.trade.domain.TAccountItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-28 17:18
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
@Transactional
public interface TAccountItemRepo extends JpaRepository<TAccountItem, Long> {
    Page<TAccountItem> findAll(Specification<TAccountItem> specification, Pageable page);
}
