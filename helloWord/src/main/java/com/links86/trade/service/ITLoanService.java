package com.links86.trade.service;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TLoan;
import com.links86.trade.domain.TLoanDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 13:32
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public interface ITLoanService {

    void newLoan(TLoanDetail tLoanDetail) throws  Links86Exception;
    void updateLoan(TLoan tLoan)throws Links86Exception;
    void updateLoanDetail(TLoanDetail tLoanDetail)throws Links86Exception;
    void deleteLoan(long id) throws Links86Exception;

    TLoan findById(long id)throws Links86Exception;
    TLoanDetail findByLId(long ldid)throws Links86Exception;

    Page<TLoanDetail> findDetailByBuyer(long buyerId, PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findByLoanId(long loanId,PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findDetailByBuyer(long buyerId,String loanStatus, PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findByLoanId(long loanId,String loanStatus,PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findDetailByBuyer(long buyerId, Date btime, Date etime, PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findByLoanId(long loanId,Date btime,Date etime,PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findDetailByBuyer(long buyerId,Date btime,Date etime,String loanStatus, PageRequest pageRequest)throws Links86Exception;
    Page<TLoanDetail> findByLoanId(long loanId,Date btime,Date etime,String loanStatus,PageRequest pageRequest)throws Links86Exception;

    Page<TLoanDetail> findLoanAndDetail(Date beginTime,Date endTime,String selectParam,long buyerId,long sellerId,PageRequest pageRequest)throws Links86Exception;
    void deleteDetail(TLoanDetail tLoanDetail)throws Links86Exception;

    boolean exists(long lid) throws Links86Exception;

    boolean existDetail(long ldid)throws Links86Exception;

    BigDecimal computeTotalAmount(long loanId)throws Links86Exception;

    long findSellerId(long loanId)throws Links86Exception;


}
