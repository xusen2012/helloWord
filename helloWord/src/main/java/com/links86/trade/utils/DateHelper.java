package com.links86.trade.utils;

import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Date Tool</p>
 * Created by xuli on 16/1/5.
 */
public class DateHelper {
    private static final Logger logger = LoggerFactory.getLogger(DateHelper.class);
    public static final String fmt_yyyyMMdd = "yyyy-MM-dd";
    public static final String fmt_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public static Date addMinutes(Date old, int minutes) {
        return new Date(old.getTime() + minutes * 60 * 1000);
    }

    public static boolean isValidDate(String yyyyMMdd) {
        return isConvert(yyyyMMdd, fmt_yyyyMMdd);
    }

    public static boolean isValidTime(String yyyyMMddHHmmss) {
        return isConvert(yyyyMMddHHmmss, fmt_yyyyMMddHHmmss);
    }

    public static boolean isConvert(String tmp, String arg) {
        boolean convertResult = false;
        SimpleDateFormat sdf = new SimpleDateFormat(arg);
        try {
            sdf.setLenient(false);
            sdf.parse(tmp);
            convertResult = true;
        } catch (Exception e) {
            throw new Links86Exception(Links86Exception.TRADE_CODE,
                    UKUtil.get32UUID(),
                    e.getMessage().split("\n")[0],
                    e.getStackTrace()[0].getClassName(),
                    e.getStackTrace()[0].getFileName(),
                    e.getStackTrace()[0].getMethodName(),
                    e.getStackTrace()[0].getLineNumber());
        }
        return convertResult;
    }

    public static Date cvt_yyyyMMddHHmmss(String yyyyMMddHHmmss) throws Exception {
        return new SimpleDateFormat(fmt_yyyyMMddHHmmss).parse(yyyyMMddHHmmss);
    }

    public static String cvt_String(Date date) throws Exception {
        return new SimpleDateFormat(fmt_yyyyMMddHHmmss).format(date);
    }

}