package com.links86.trade.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单详细表
 * Created by leoxu on 2016/11/10.
 */
@Entity
@Table(name = "t_loan_detail")
public class TLoanDetail {
    //订单唯一标示
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    //订购数量 
    private int count;

    // 订单总额 
    private BigDecimal commodityAmount;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private TLoan tLoan;

    //manytoone
    //卖家账户id
    @ManyToOne
    @JoinColumn(name="seller_account_id")
    /*@JsonIgnore*/
    private TAccount sellerAccount;

    //产品id 
    @ManyToOne
    @JoinColumn(name = "product_id")
   /* @JsonIgnore*/
    private TProduct tProduct;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TLoan gettLoan() {
        return tLoan;
    }

    public void settLoan(TLoan tLoan) {
        this.tLoan = tLoan;
    }

    public TProduct gettProduct() {
        return tProduct;
    }

    public void settProduct(TProduct tProduct) {
        this.tProduct = tProduct;
    }

    public TAccount getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(TAccount sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getCommodityAmount() {
        return commodityAmount;
    }

    public void setCommodityAmount(BigDecimal commodityAmount) {
        this.commodityAmount = commodityAmount;
    }
}
