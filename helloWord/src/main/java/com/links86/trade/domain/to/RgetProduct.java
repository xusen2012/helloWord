package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-17 18:55
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class RgetProduct extends PageCondition {
    private long id;

    private String name;

    private long companyId;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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
        this.name = name;
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

    public static void main(String args[]) {
        RgetProduct rgetProduct = new RgetProduct();
        rgetProduct.setName("prod");
        rgetProduct.setOrderFields(new String[]{"id"});
        rgetProduct.setDirection("ASC");
        rgetProduct.setPageSize(10);
        rgetProduct.setPageIndex(0);
        System.out.println(rgetProduct);
    }
}
