package com.links86.trade.domain.em;

/**
 * Created by hand on 2016/11/17.
 */
public enum PayType {

    /**
     * 账户余额
     */
    ACCOUNTBALANCE("ACCOUNTBALANCE","账户余额"),
    /**
     * 直接支付
     */
    DIRECTPAYMENT("DIRECTPAYMENT", "直接支付");


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
    PayType(String code, String description) {
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
