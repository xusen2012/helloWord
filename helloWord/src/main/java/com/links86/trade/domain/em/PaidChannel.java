package com.links86.trade.domain.em;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-08 16:16
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 *   订单支付渠道
 */
public enum PaidChannel {
    /* paypal*/
    PP("PP","PayPal"),
    /** 支付宝 */
    ZFB("ZFB", "支付宝"),
    /**账户余额**/
    ACT("ACT","账户余额"),
    /** 微信 **/
    WX("WX","微信"),
    /** 银联 */
    YL("YL", "银联");


    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.code;
    }

    PaidChannel(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
}