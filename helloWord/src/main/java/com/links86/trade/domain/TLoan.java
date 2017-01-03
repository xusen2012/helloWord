package com.links86.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.links86.trade.domain.em.LoanStatus;
import com.links86.trade.domain.em.PaidChannel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单系统简单模型
 * Project name is links86-system
 * Created by xuli on 2016-09-08 14:56
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Entity
@Table(name = "t_loan")
public class TLoan implements Serializable {

    //订单唯一标示
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    //描述信息
    private String description;

    //创建时间
    private Date createdTime;

    //更新时间
    private Date updatedTime;

    //订单状态
    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.UNPAID;

    //付款渠道
    @Enumerated(EnumType.STRING)
    private PaidChannel paidChannel = PaidChannel.ZFB;

    @OneToMany(cascade={CascadeType.REMOVE},mappedBy="tLoan")
    @JsonIgnore
    private List<TLoanDetail> tLoanDetail;

    //买家收获地址
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buyer_address_id")
  /*  @JsonIgnore*/
    private TAccountAddress buyerAddress;

    private String taobaoTradeNo;

    public String getTaobaoTradeNo() {
        return taobaoTradeNo;
    }

    public void setTaobaoTradeNo(String taobaoTradeNo) {
        this.taobaoTradeNo = taobaoTradeNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public PaidChannel getPaidChannel() {
        return paidChannel;
    }

    public void setPaidChannel(PaidChannel paidChannel) {
        this.paidChannel = paidChannel;
    }

    public TAccountAddress getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(TAccountAddress buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public List<TLoanDetail> gettLoanDetail() {
        return tLoanDetail;
    }

    public void settLoanDetail(List<TLoanDetail> tLoanDetail) {
        this.tLoanDetail = tLoanDetail;
    }
}

