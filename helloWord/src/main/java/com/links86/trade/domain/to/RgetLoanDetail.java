package com.links86.trade.domain.to;


import java.sql.Date;

/**
 * 用于查询订单订单明细传入参数的封装
 * @author xusen
 *
 */
public class RgetLoanDetail extends PageCondition {
    private Date beginTime;//开始时间

    private Date endTime;//结束时间

    private String  selectParam;//买家名 产品名 订单号

    private long buyerId;//买家id

    private long sellerId;//卖家id

    public String getSelectParam() {
        return selectParam;
    }

    public void setSelectParam(String selectParam) {
        this.selectParam = selectParam;
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

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }
}
