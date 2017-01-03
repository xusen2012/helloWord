package com.links86.trade.domain.to;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 14:48
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class CResponse {

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

    /**
     * 重写等于方法,用来判断返回对象
     *
     * @param o 传入的结果
     * @return 是否相等
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == getClass()) {
            CResponse cResponse = (CResponse) o;
            return code != null ? code.equals(cResponse.code) : cResponse.code == null && (type != null ? type.equals(cResponse.type) : cResponse.type == null && (message != null ? message.equals(cResponse.message) : cResponse.message == null));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    private String code;
    private String type;
    private String message;
    private LinkedHashMap<String, Object> result = new LinkedHashMap<>();

    public CResponse() {
    }

    public CResponse(Map<String, Object> model) {
        if (model != null) {
            getResult().putAll(model);
        }
    }

    public CResponse addObject(String attributeName, Object attributeValue) {
        getResult().put(attributeName, attributeValue);
        return this;
    }


    public LinkedHashMap<String, Object> getResult() {
        return this.result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
