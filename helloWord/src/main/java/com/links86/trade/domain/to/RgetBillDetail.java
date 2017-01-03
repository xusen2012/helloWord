package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

import java.sql.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-17 18:55
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class RgetBillDetail extends PageCondition {

    private long accountId;

    private Date beginTime;

    private Date endTime;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
