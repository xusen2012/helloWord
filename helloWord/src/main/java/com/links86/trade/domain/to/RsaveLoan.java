package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-17 20:11
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class RsaveLoan {
    private long lid;

    //子订单id
    private long loanDetail;

    //子订单总金额
    private BigDecimal commodityAmount;

    //订单状态
    private String loanStatus;

    //支付渠道
    private String paidChannel;

    //卖家账户id
    private long sellerAccount;

    //买家账户id
    private long buyerAccount;

    //买家地址id
    private long buyerAddress;

    //描述
    private String description;

    //产品id
    private long productId;

    //购买产品数量
    private int count;

    public long getLoanDetail() {
        return loanDetail;
    }

    public void setLoanDetail(long loanDetail) {
        this.loanDetail = loanDetail;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    private Date createdTime;

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public long getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(long buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }


    public BigDecimal getCommodityAmount() {
        return commodityAmount;
    }

    public void setCommodityAmount(BigDecimal commodityAmount) {
        this.commodityAmount = commodityAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getPaidChannel() {
        return paidChannel;
    }

    public void setPaidChannel(String paidChannel) {
        this.paidChannel = paidChannel;
    }

    public long getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(long sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public long getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(long buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        RsaveLoan rsaveLoan = new RsaveLoan();
        System.out.println(rsaveLoan);
    }
}
