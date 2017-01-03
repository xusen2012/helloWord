package com.links86.trade.domain.em;

/**
 * 订单状态
 *         @author Leoxu      </br>
 *         UNPAID   待支付	  </br>
 *         PAID_OFF 审批中	  </br>
 *         PAY_FAIL  付款失败 </br>
 *         REFUNDING 退款中   </br>
 */
public enum LoanStatus {

    /**
     * 退款成功
     */
    REFUNDED("REFUNDED","已退款"),
    /**
     * 待激活
     */
    SLEEP("SLEEP","待激活"),
    /**
     * 待支付
     */
    UNPAID("UNPAID", "待支付"),
    /**
     * 已付款
     */
    PAID_OFF("PAID_OFF", "已付款"),
    /**
     * 付款失败
     */
    PAY_FAIL("PAY_FAIL", "付款失败"),
    /**
     * 退款中
     */
    REFUNDING("REFUNDING", "退款中"),
    /**
     * 删除
     */
    DELETE("DELETE","删除");

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
    LoanStatus(String code, String description) {
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