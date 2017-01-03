package com.links86.trade.domain.em;

public enum BillDetailStatus {

    /**
     * 已完成
     */
    FINISH("FINISH","已完成"),
    /**
     * 已付款
     */
    PAID_OFF("PAID_OFF", "已付款"),
    /**
     * 付款失败
     */
    PAY_FAIL("PAY_FAIL", "付款失败");

    /**
     * 枚举值
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 构造方法
     *
     * @param code 内容
     * @param description  注视
     */
    BillDetailStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.code;
    }
}