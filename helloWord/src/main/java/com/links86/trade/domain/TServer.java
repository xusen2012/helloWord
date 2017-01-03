package com.links86.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.links86.trade.domain.em.ServerStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 23:13
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Entity
@Table(name = "t_server")
public class TServer {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    private String name;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private ServerStatus serverStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private TProduct product;


    private Date beginTime;

    private Date endTime;

    public TServer() {
    }

    public TServer(long id, String name, BigDecimal balance, ServerStatus serverStatus, long prod_id, Date begintime, Date endTime) {
        super();
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.serverStatus = serverStatus;
        this.product = new TProduct();
        this.product.setId(prod_id);
        this.beginTime = begintime;
        this.endTime = endTime;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }
}
