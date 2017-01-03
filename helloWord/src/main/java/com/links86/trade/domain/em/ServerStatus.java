package com.links86.trade.domain.em;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 17:12
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public enum ServerStatus {
    ON("ON", "在用"),
    OFF("OFF", "下架");
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

    ServerStatus(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

}

