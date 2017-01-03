package com.links86.trade.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.links86.trade.domain.*;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.domain.to.*;
import com.links86.trade.exception2.Links86Exception;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 14:06
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Controller
public class AccountController extends BaseController {


    /**
     * 绑定一个账户,可以是用户,也可以是企业
     *
     * @param account 账户传入参数
     * @return 返回http响应
     */
    @RequestMapping(value = "/trade/bind_account", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse bindAccount(@RequestBody RsaveAccount account) {
        try {
            TAccount tAccount = new TAccount();
            tAccount.setId(snowflake.next());
            tAccount.setFreeze(account.getFreeze());
            tAccount.setName(account.getAccountName());
            tAccount.settUser(itUserService.findById(account.getUserId()));
            itAccountService.bindAccount(tAccount);
            return buildSuccess().addObject("data", "绑定成功!").addObject("id", tAccount.getId());
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    @RequestMapping(value = "/trade/quick_bind", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse quickBind(@RequestBody RquickAccount rquickAccount) {
        try {
            if (checkUser(rquickAccount.getUid())) { //如果已存在账户
                TAccount tAccount = new TAccount();
                tAccount.setId(snowflake.next());
                tAccount.setFreeze(rquickAccount.isFreeze());
                tAccount.setName(rquickAccount.getAname());
                tAccount.settUser(itUserService.findById(rquickAccount.getUid()));
                itAccountService.bindAccount(tAccount);
                return buildSuccess().addObject("data", "绑定成功!").addObject("id", ""+tAccount.getId());
            } else {
                TUser tUser = new TUser(); //新建用户
                tUser.setId(snowflake.next());
                tUser.setName(rquickAccount.getName());
                tUser.setOutUid(rquickAccount.getOutUid());
                if(checkAccountType(rquickAccount.getType())){
                    tUser.setType(AccountType.valueOf(rquickAccount.getType()));
                    tUser.setUpdateTime(new Date());
                    itUserService.save(tUser);
                    //新建账户
                    TAccount tAccount = new TAccount();
                    tAccount.setId(snowflake.next());
                    tAccount.setFreeze(rquickAccount.isFreeze());
                    tAccount.setName(rquickAccount.getAname());
                    tAccount.settUser(tUser);
                    itAccountService.bindAccount(tAccount);
                    return buildSuccess().addObject("data", "绑定成功!").addObject("id",""+tAccount.getId());
                }else{
                    throw new Links86Exception("用户类型输入错误!");
                }
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    /**
     * 冻结账户
     *
     * @param account 账户传入参数
     * @return 返回http响应
     */
    @RequestMapping(value = "/trade/freeze_account", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse freezeAccount(@RequestBody RgetAccount account) {
        try {
            itAccountService.unpackAccount(account.getId());
            return buildSuccess();
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    /**
     * 更新账户
     *
     * @param account 账户修改传入
     * @return 返回http响应
     */
    @RequestMapping(value = "/trade/update_account", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse updateAccount(@RequestBody RsaveAccount account) {
        try {
            if (checkAccount(account.getAccountId())) {
                TAccount tAccount = itAccountService.findById(account.getAccountId());
                tAccount.setName(account.getAccountName());
                tAccount.setFreeze(account.getFreeze());
                itAccountService.updateAccount(tAccount);
                return buildSuccess();
            } else {
                throw new Links86Exception("账户信息不存在!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //通过账户id完成单条查询
    @RequestMapping(value = "/trade/get_account_by_id", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getAccountById(@RequestBody RsaveAccount account) {
        TAccount tAccount;
        try {
            tAccount = itAccountService.findById(account.getAccountId());
            return buildSuccess().addObject("data", tAccount);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //通过用户id完成单条查询
    @RequestMapping(value = "/trade/get_account_by_uid", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getAccountByUId(@RequestBody RsaveAccount account) {
        TAccount tAccount;
        try {
            if (checkAccountType(account.getType())) {
                if (AccountType.COMPANY.getCode().equals(account.getType())) {
                    tAccount = itAccountService.findByUserId(account.getUserId(), AccountType.COMPANY);
                    return buildSuccess().addObject("data", tAccount);
                } else {
                    tAccount = itAccountService.findByUserId(account.getUserId(), AccountType.USER);
                    return buildSuccess().addObject("data", tAccount);
                }
            } else {
                throw new Links86Exception("账户类型输入错误！");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //查询账户地址信息
    @RequestMapping(value = "/trade/get_account_address", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getAccountAddress(@RequestBody RgetAccount account) {
        setPageRequest(account.getPageIndex(), account.getPageSize(), account.getDirection(), account.getOrderFields());
        Page<TAccountAddress> accountAddresses;
        try {
            accountAddresses = itAccountService.findAddressByAId(account.getId(), pageRequest);
            return buildSuccess().addObject("data", accountAddresses);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //保存地址信息
    @RequestMapping(value = "/trade/save_account_address", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse saveAccountAddress(@RequestBody RsaveAccountAddress accountAddress) {
        try {
            if (checkAccount(accountAddress.getAccountId())) {
                TAccountAddress tAccountAddress = new TAccountAddress();
                if (accountAddress.getId() == 0) {
                    tAccountAddress.setId(snowflake.next());
                    tAccountAddress.settAccount(itAccountService.findById(accountAddress.getAccountId()));
                    tAccountAddress.setAddress(accountAddress.getAddress());
                    tAccountAddress.setEmail(accountAddress.getEmail());
                    tAccountAddress.setRecipientsName(accountAddress.getRecipientsName());
                    tAccountAddress.setPhone(accountAddress.getPhone());
                    itAccountService.addAddress(tAccountAddress);
                    return buildSuccess();
                } else if (checkAccountAddress(accountAddress.getId())) {
                    tAccountAddress.setId(accountAddress.getId());
                    tAccountAddress.settAccount(itAccountService.findById(accountAddress.getAccountId()));
                    tAccountAddress.setAddress(accountAddress.getAddress());
                    tAccountAddress.setEmail(accountAddress.getEmail());
                    tAccountAddress.setPhone(accountAddress.getPhone());
                    tAccountAddress.setRecipientsName(accountAddress.getRecipientsName());
                    itAccountService.addAddress(tAccountAddress);
                    return buildSuccess();
                } else {
                    throw new Links86Exception("账户地址信息输入错误!");
                }
            } else {
                throw new Links86Exception("账户信息不存在!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //保存地址信息
    @RequestMapping(value = "/trade/def_account_address", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse defAccountAddress(@RequestBody RsaveAccountAddress accountAddress) {
        try {
            if (checkAccountAddress(accountAddress.getId())) {
                TAccountAddress tAccountAddress = itAccountService.findAddressById(accountAddress.getId());
                tAccountAddress.setDefault(true);
                itAccountService.defaultAddress(tAccountAddress);
                return buildSuccess();
            } else {
                throw new Links86Exception("账户地址输入有错误!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //删除单条地址
    @RequestMapping(value = "/trade/del_account_address", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse delAccountAddress(@RequestBody RsaveAccountAddress accountAddress) {
        try {
            if (checkAccountAddress(accountAddress.getId())) {
                itAccountService.deleteAddress(accountAddress.getId());
                return buildSuccess();
            } else {
                throw new Links86Exception("地址信息未找到!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    //为账户充值
    @RequestMapping(value = "/trade/gain_money", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse gainMoney(@RequestBody RgetAccount account) {
        try {
            long aid = account.getId();
            if (checkAccount(aid)) {
                TAccountDetail tAccountDetail = new TAccountDetail();
                tAccountDetail.setId(snowflake.next());
                tAccountDetail.setNumber(snowflake.next());//此处原则和时间生成一致，但是需要暴露对外，主键不提倡暴露
                tAccountDetail.settAccount(itAccountService.findById(aid));
                tAccountDetail.setUpdatedTime(new Date());
                BigDecimal totalAmount = itAccountService.computeAccountAmount(aid);
                if (totalAmount == null) {
                    totalAmount = new BigDecimal(0);
                }
                if (totalAmount.compareTo(new BigDecimal(0)) < 0
                        && account.getAmount().compareTo(new BigDecimal(0)) < 0) {
                    throw new Links86Exception("不允许为欠费账户扣款!");
                }
                tAccountDetail.setAmount(account.getAmount());
                itAccountService.addAccountDetail(tAccountDetail);
                return buildSuccess();
            } else {
                throw new Links86Exception("账户不存在！");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //查询账户流水详单通过账户id
    @RequestMapping(value = "/trade/get_detail_by_aid", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getDetailByAId(@RequestBody RgetAccountDetail account) {
        try {
            setPageRequest(account.getPageIndex(), account.getPageSize(), account.getDirection(), account.getOrderFields());
            long aid = account.getId();
            if (checkAccount(aid)) {
                Page<TAccountDetail> tAccountDetails = itAccountService.findDetailByAId(aid, account.getBtime(), account.getEtime(), pageRequest);
                return buildSuccess().addObject("data", tAccountDetails);
            } else {
                throw new Links86Exception("账户不存在！");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //   提现操作
    @RequestMapping(value = "/trade/with_draw", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse withDraw(@RequestBody RgetAccount account) {
        try {
            checkAccount(account.getId());
            BigDecimal amount = itAccountService.computeAccountAmount(account.getId());
            if (amount.compareTo(new BigDecimal(0)) > 0) {
                TAccountDetail tAccountDetail = new TAccountDetail();
                tAccountDetail.setId(snowflake.next());
                tAccountDetail.setAmount(amount.negate());
                tAccountDetail.setUpdatedTime(new Date(System.currentTimeMillis()));
                tAccountDetail.setNumber(snowflake.next());
                TAccount tAccount = itAccountService.findById(account.getId());
                tAccountDetail.settAccount(tAccount);
                itAccountService.addAccountDetail(tAccountDetail);
                //提现动作需要在账户表里面记录一个该账户的支付宝信息，这样能保障提现自动完成，调用支付宝账户的直接对转！
                //此处没有需求就没有向下做！
                return buildSuccess().addObject("refundAmount", account);
            } else {
                throw new Links86Exception("账户金额少于0元,无法提现!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

//    //转账功能,暂时不支持!


    /**
     * 账单查询
     */
    @RequestMapping(value = "/trade/getBillDetail", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getBillDetail(@RequestBody RgetBillDetail rgetBillDetail) {
        try {
            if(!"".equals(rgetBillDetail.getAccountId())){
                setPageRequest(rgetBillDetail.getPageIndex(), rgetBillDetail.getPageSize(), rgetBillDetail.getDirection(), rgetBillDetail.getOrderFields());
                Page<TBillDetail> tBillDetails = itBillDetailService.findAll(rgetBillDetail, pageRequest);
                BigDecimal accountBalance = itAccountService.computeAccountAmount(rgetBillDetail.getAccountId());
                BigDecimal inAmount = itBillDetailService.computeInAmount(rgetBillDetail.getAccountId());
                BigDecimal exAmount = itBillDetailService.computeExAmount(rgetBillDetail.getAccountId());
                return buildSuccess().addObject("data", tBillDetails).addObject("accountBalance", accountBalance).addObject("inAmount", inAmount).addObject("exAmount", exAmount);
            }else {
                return buildFailed().addObject("exception","账户id不存在");
            }

        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    /**
     * 支付宝退款
     * @return
     */
    @RequestMapping(value="/trade/refund",method = RequestMethod.POST )
    public CResponse tradeRefund(String out_trade_no,String refund_amount){
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMEeSM8Ov8DgODdg2QvvJQVRUlRvdmPkOE/LqJNVccZ8F/TyQHHkiMKKlDbS5+oNV8vwdiRBw6y9pWkAvB7zCnCQQut55ulaX5gnluiuEc4HQ3beLHArd+i4xHsOKSi3T3Yvis+3ahFR84NZ3ZMGFHTG/93JHHXc2sG3VF8IV1JlAgMBAAECgYAd5DIg2oeTsyEAcV0nG7z/NbcompgYnhqZP0y4gWE697o9j8Gp31XLxudqFPsQn0AXsQCYXBWNh00zE4oKwFO9Ez3uASXvCSR3rDHnVumh8CGsZ9MaNfOITDHt+VWv7DT/Q7cfqF0Nd84GUR5jvqpEpGMQoq71MLXmzkluEPyYAQJBAPtRh3FukonvGBL+Sxs/w6eoq6yE+TlYSYX9bfuViouJhhp6o6iUScd2G3RMcls3XLSZfr/jTJW6rTF//hi9ZbECQQDEtzbVFwWtOMHhej4OwhHMiR1TlaWOcgnN+hM/tU1dt+trt0ERVz9XugVlC1oUTUNPlK4IbQ0FbqDvNi/QpQD1AkAiN7CEPjTPePFDLMJXHHBsNGfjXlt8dkvUZXQDDBJH+1p54nbD7iS5dpg4Vmw/hSMnJ7r9L1pvDHKmSbHz9l2RAkBTdmC/XHs+GZy5LAJ0hIbEv1BADHgnzd63E+li2Guw3b32/9ONpfTsJbLwQja/8mAivbRxQsOJkHybwePbpIaZAkBY1xwUgY/ajG/xaTGR+d8nRUW5DW4g/TjW3l1u6KNIcx0Tv4siowi4sY9xFX+h+u/9irFS8eQcxRsgOrEVrWae";
        String pulicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2016101702208724",privateKey,"json","UTF-8",pulicKey);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        out_trade_no="6216515223877062656";
        refund_amount="0.01";
        request.setBizContent("{" +
                "    \"out_trade_no\":"+ out_trade_no+"," +
                /*"    \"trade_no\":\"2014112611001004680073956707\"," +*/
                "    \"refund_amount\":"+refund_amount+
               /* "    \"refund_reason\":\"正常退款\"," +
                "    \"out_request_no\":\"HZ01RF001\"," +
                "    \"operator_id\":\"OP001\"," +
                "    \"store_id\":\"NJ_S_001\"," +
                "    \"terminal_id\":\"NJ_T_001\"" +*/
                "  }");
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }
}
