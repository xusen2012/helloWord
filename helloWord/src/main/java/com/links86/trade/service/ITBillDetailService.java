package com.links86.trade.service;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TBillDetail;
import com.links86.trade.domain.TUser;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.domain.to.RgetBillDetail;
import com.links86.trade.repo.TBillDetailRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-12 12:04
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public interface ITBillDetailService {
    Page<TBillDetail> findAll(RgetBillDetail rgetBillDetail,PageRequest pageRequest) throws Links86Exception;

    void save(TBillDetail tBillDetail)throws Links86Exception;

    BigDecimal computeExAmount(Long accountId);

    BigDecimal computeInAmount(Long accountId);
}
