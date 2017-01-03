package com.ebay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * xusen
 */
@Component
public class PayPalConfig {

    @Value("${com.paypal.mode}")
    public String mode;

    @Value("${com.paypal.userName}")
    public String userName;

    @Value("${com.paypal.password}")
    public String password;

    @Value("${com.paypal.signature}")
    public String signature;

    @Value("${com.paypal.return_url}")
    public String return_url;

    @Value("${com.paypal.login_url}")
    public String login_url;

    @Value("${com.paypal.success_return_url}")
    public String success_return_url;

    @Value("${com.paypal.fail_return_url}")
    public String fail_return_url;

    @Value("${com.paypal.cancel_url}")
    public String cancel_url;

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getLogin_url() {
        return login_url;
    }

    public void setLogin_url(String login_url) {
        this.login_url = login_url;
    }

    public String getSuccess_return_url() {
        return success_return_url;
    }

    public void setSuccess_return_url(String success_return_url) {
        this.success_return_url = success_return_url;
    }

    public String getFail_return_url() {
        return fail_return_url;
    }

    public void setFail_return_url(String fail_return_url) {
        this.fail_return_url = fail_return_url;
    }
}
