package com.links86.trade.repo;

import com.links86.trade.domain.TLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 13:30
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
public interface TLoanRepo extends JpaRepository<TLoan,Long> {
    //多条件查询
    Page<TLoan> findAll(Specification<TLoan> specification, Pageable page);
}
