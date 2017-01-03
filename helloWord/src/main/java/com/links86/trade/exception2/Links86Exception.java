package com.links86.trade.exception2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.util.UKUtil;

/**
 * 通用模块内异常
 * Created by leoxu on 2016/11/9.
 */
public class Links86Exception extends RuntimeException {
    public final static Integer TRADE_CODE = 520;
    private String location;
    private Integer errorCode;
    private String className;
    private String fileName;
    private String methodName;
    private Integer lineNumber;

    public Links86Exception(String message) {
        super(message);
        this.location = UKUtil.get32UUID();
        this.errorCode = TRADE_CODE;
        this.className = this.getStackTrace()[0].getClassName();
        this.fileName = this.getStackTrace()[0].getMethodName();
        this.lineNumber = this.getStackTrace()[0].getLineNumber();
    }

    public Links86Exception(int code, String location, String message, String className, String fileName, String methodName, int lineNumber) {
        super(message);  //e.getMessage().split("\n")[0]
        this.location = location;
        this.errorCode = code;
        this.className = className;
        this.fileName = fileName;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    @Override
    public String getMessage() throws Links86Exception {
        return "{\"TRADE_CODE\":" + TRADE_CODE +
                ",\"location\":" + location +
                ",\"errorCode\":" + errorCode +
                ",\"className\"" + className +
                ",\"fileName\":" + fileName +
                ",\"methodName\":" + methodName +
                ",\"lineNumber\":" + lineNumber +
                ",\"message\":" + super.getMessage() +
                "}";
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

}
