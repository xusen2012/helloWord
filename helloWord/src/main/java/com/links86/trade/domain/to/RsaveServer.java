package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-17 18:40
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class RsaveServer {

    private long id;

    @NotNull(message = "服务名称不能为空!")
    private String name;

    @NotNull(message = "服务价格不能为空!")
    private BigDecimal balance;

    private Long productId;

    private String serverStatus;

    private Date beginTime;

    private Date endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
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
        RsaveServer rsaveServer = new RsaveServer();
        rsaveServer.setBeginTime(new Date());
        System.out.println(rsaveServer);
    }
}
