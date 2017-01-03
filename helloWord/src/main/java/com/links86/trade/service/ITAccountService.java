package com.links86.trade.service;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TAccount;
import com.links86.trade.domain.TAccountAddress;
import com.links86.trade.domain.TAccountDetail;
import com.links86.trade.domain.TAccountItem;
import com.links86.trade.domain.em.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 16:08
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public interface ITAccountService {
    void bindAccount(TAccount account)throws Links86Exception;
    void unpackAccount(long id)throws Links86Exception;
    void updateAccount(TAccount account)throws Links86Exception;
    TAccount findById(long id) throws Links86Exception;
    TAccount findByUserId(long uid, AccountType type)throws Links86Exception;

    Page<TAccountAddress> findAddressByAId(long id,PageRequest pageRequest)throws Links86Exception;
    void addAddress(TAccountAddress accountAddress)throws  Links86Exception;
    TAccountAddress getDefaultAddress(long acc_id)throws Links86Exception;
    void defaultAddress(TAccountAddress accountAddress)throws Links86Exception;
    void deleteAddress(long id)throws Links86Exception;
    TAccountAddress findAddressById(long aid)throws Links86Exception;

    void addAccountDetail(TAccountDetail tAccountDetail)throws Links86Exception;
    Page<TAccountDetail> findDetailByAId(long id,PageRequest pageRequest)throws Links86Exception;
    Page<TAccountDetail> findDetailByAId(long id, Date btime, Date etime, PageRequest pageRequest)throws Links86Exception;
    TAccountDetail findDetailByNumber(long number)throws Links86Exception;
    BigDecimal computeAccountAmount(long id)throws Links86Exception; //通过账户流水，计算账户总金额

    void addAccountItem(TAccountItem tAccountItem)throws Links86Exception;
    Page<TAccountItem> findItemByAccountId(long id,PageRequest pageRequest)throws Links86Exception;

    boolean exists(long id)throws Links86Exception;
    boolean existAddress(long ad_id)throws Links86Exception;
    boolean existDetail(long did)throws Links86Exception;
    boolean existItem(long iid)throws Links86Exception;

}
