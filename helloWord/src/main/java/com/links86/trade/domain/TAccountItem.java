package com.links86.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-28 16:54
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Entity
@Table(name = "t_account_item")
public class TAccountItem {

    //唯一标示
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    //账户对应信息
    @ManyToOne(optional = false,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    @JsonIgnore
    TAccount tAccount;

    //产品对应信息
    @OneToOne(optional = false,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnore
    TProduct tProduct;

    //产品对应数量
    private int count;

    //是否在用
    private boolean inUse;

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TAccount gettAccount() {
        return tAccount;
    }

    public void settAccount(TAccount tAccount) {
        this.tAccount = tAccount;
    }

    public TProduct gettProduct() {
        return tProduct;
    }

    public void settProduct(TProduct tProduct) {
        this.tProduct = tProduct;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
