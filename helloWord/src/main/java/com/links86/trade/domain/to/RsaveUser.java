package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.domain.em.AccountType;
import com.links86.util.UKUtil;

/**
 * 对象请求封装user
 * Created by leoxu on 2016/11/11.
 */
public class RsaveUser extends PageCondition {
    private long id;
    private String name;

    private AccountType type;

    private long outUId;

    public long getOutUId() {
        return outUId;
    }

    public void setOutUId(long outUId) {
        this.outUId = outUId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
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

    public static void main(String args[]){
        RsaveUser rsaveUser = new RsaveUser();
        System.out.println(rsaveUser);
    }
}
