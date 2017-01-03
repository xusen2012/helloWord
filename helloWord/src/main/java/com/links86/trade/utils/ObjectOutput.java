package com.links86.trade.utils;

import org.slf4j.Logger;

/**
 * Created by leoxu on 2016/11/7.
 */
public class ObjectOutput {
//    public static void main(String args[]){
//        CResponse cResponse = new CResponse();
//        System.out.println(cResponse);
//    }

    public static void exceptionOut(Exception e,Logger logger){
        StackTraceElement traceElement = e.getStackTrace()[0];
        String file = "file="+traceElement.getFileName();
        String line = "line="+traceElement.getLineNumber();
        String method = "method="+traceElement.getMethodName();
        traceElement.getClassName();
        logger.error(file+line+method+e.getMessage());
    }
}
