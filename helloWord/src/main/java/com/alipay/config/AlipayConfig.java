package com.alipay.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.links86.trade.exception2.Links86Exception;
import com.links86.util.UKUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Project name is links86-system
 * Created by xuli on 2016-10-18 10:48
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 * 重新封装配置
 */
@Component
public class AlipayConfig {
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    //ow8q96gwfkf7yh0v7l4v3cfv4d9s1lcm  默认加密

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    @Value("${com.alipay.partner}")
    public String partner = "";

    public static String partner2 = "2088421743486096";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    @Value("${com.alipay.seller_id}")
    public String seller_id = partner;

    public static String seller_id2 = partner2;

    //商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
    @Value("${com.alipay.key}")
    public String key = "";

    public static String key2 = "ow8q96gwfkf7yh0v7l4v3cfv4d9s1lcm";

    // 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
//    @Value("${com.alipay.alipay_public_key}")
//    public String alipay_public_key = "";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${com.alipay.notify_url}")
    public String notify_url = "";

    public static String notify_url2 = "http://120.25.235.210:8080/links86trade/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${com.alipay.return_url}")
    public String return_url = "";

    public static String return_url2 = "http://120.25.235.210:8080/links86trade/return_url.jsp";

    // 签名方式
    @Value("${com.alipay.sign_type}")
    public String sign_type = "MD5";

    public static String sign_type2 = "MD5";

    // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
    @Value("${com.alipay.log_path}")
    public String log_path = "../logs";

    public static String log_path2 = "../logs";

    // 字符编码格式 目前支持 gbk 或 utf-8
    @Value("${com.alipay.input_charset}")
    public String input_charset = "UTF-8";

    public static String input_charset2 = "UTF-8";

    // 支付类型 ，无需修改
    @Value("${com.alipay.payment_type}")
    public String payment_type = "1";

    public static String payment_type2 = "1";

    // 调用的接口名，无需修改
    @Value("${com.alipay.service}")
    public String service = "create_direct_pay_by_user";

    public static String service2 = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
    public String anti_phishing_key = "";

    // 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
    public String exter_invoke_ip = "";

//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    //支付成功后跳转到商户自己的成功页面
    @Value("${com.alipay.success_return_url}")
    public String success_return_url;

    //支付失败跳转到相应的页面
    @Value("${com.alipay.fail_return_url}")
    public String fail_return_url;

    public void setSuccess_return_url(String success_return_url) {
        this.success_return_url = success_return_url;
    }

    public void setFail_return_url(String fail_return_url) {
        this.fail_return_url = fail_return_url;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    public String getAlipay_public_key() {
//        return alipay_public_key;
//    }
//
//    public void setAlipay_public_key(String alipay_public_key) {
//        this.alipay_public_key = alipay_public_key;
//    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getLog_path() {
        return log_path;
    }

    public void setLog_path(String log_path) {
        this.log_path = log_path;
    }

    public String getInput_charset() {
        return input_charset;
    }

    public void setInput_charset(String input_charset) {
        this.input_charset = input_charset;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAnti_phishing_key() {
        return anti_phishing_key;
    }

    public void setAnti_phishing_key(String anti_phishing_key) {
        this.anti_phishing_key = anti_phishing_key;
    }

    public String getExter_invoke_ip() {
        return exter_invoke_ip;
    }

    public void setExter_invoke_ip(String exter_invoke_ip) {
        this.exter_invoke_ip = exter_invoke_ip;
    }

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
}
