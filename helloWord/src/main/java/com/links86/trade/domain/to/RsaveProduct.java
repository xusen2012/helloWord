package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

import java.util.Date;


/**
 * Project name is links86-system
 * Created by xuli on 2016-09-17 17:52
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class RsaveProduct {

    private long id;

    private String name;
    private long companyId;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new Links86Exception("产品名称不能为null!");
        this.name = name;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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
        RsaveProduct rsaveProduct = new RsaveProduct();
        System.out.println(rsaveProduct);
        long begin = 1480057158879l;
        System.out.println(begin);
        System.out.println(30*24*3600);
        System.out.println(begin+30*24*3600);
        System.out.println(new Date().getTime());
        System.out.println(1480057450004l+2592000);
    }
}
