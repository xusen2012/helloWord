package com.links86.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 15:47
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Entity
@Table(name = "t_account_detail")
public class TAccountDetail {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    @Column(unique = true)
    private long number;

    //更新时间
    private Date updatedTime;

    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnore
    private TAccount tAccount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TAccount gettAccount() {
        return tAccount;
    }

    public void settAccount(TAccount tAccount) {
        this.tAccount = tAccount;
    }

}
