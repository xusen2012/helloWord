package com.links86.trade.domain.em;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-14 10:12
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 *  账户类型枚举
 */
public enum AccountType {
    /**
     * 用户
     */
    USER("USER", "用户"),
    /**
     * 公司
     */
    COMPANY("COMPANY", "企业");
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
    AccountType(String code, String description) {
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
