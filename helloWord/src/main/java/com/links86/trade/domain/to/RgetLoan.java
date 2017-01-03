package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-17 20:39
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class RgetLoan extends PageCondition {
    private long id;

    private long buyerAccount;

    private long sellerAccount;

    private Date beginCTime;

    private Date endCTime;

    private String paidChannel;

    private String loanStatus;

    private Date beginUTime;

    private Date endUTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(long buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public long getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(long sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public Date getBeginCTime() {
        return beginCTime;
    }

    public void setBeginCTime(Date beginCTime) {
        this.beginCTime = beginCTime;
    }

    public Date getEndCTime() {
        return endCTime;
    }

    public void setEndCTime(Date endCTime) {
        this.endCTime = endCTime;
    }

    public String getPaidChannel() {
        return paidChannel;
    }

    public void setPaidChannel(String paidChannel) {
        this.paidChannel = paidChannel;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getBeginUTime() {
        return beginUTime;
    }

    public void setBeginUTime(Date beginUTime) {
        this.beginUTime = beginUTime;
    }

    public Date getEndUTime() {
        return endUTime;
    }

    public void setEndUTime(Date endUTime) {
        this.endUTime = endUTime;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new Links86Exception(Links86Exception.TRADE_CODE,
                    UKUtil.get32UUID(),
                    e.getMessage().split("\n")[0],
                    e.getStackTrace()[0].getClassName(),
                    e.getStackTrace()[0].getFileName(),
                    e.getStackTrace()[0].getMethodName(),
                    e.getStackTrace()[0].getLineNumber());
        }
    }
}
