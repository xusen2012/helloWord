package com.links86.trade.controller;

import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.ebay.api.PayPalAPI.*;
import com.ebay.apis.CoreComponentTypes.BasicAmountType;
import com.ebay.apis.eBLBaseComponents.*;
import com.links86.trade.domain.*;
import com.links86.trade.domain.em.LoanStatus;
import com.links86.trade.domain.em.PaidChannel;
import com.links86.trade.domain.to.*;
import com.links86.trade.exception2.Links86Exception;
import com.paypal.exception.*;
import com.paypal.sdk.exceptions.OAuthException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单支付模块
 * Project name is links86-system
 * Created by xuli on 2016-09-15 14:07
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Controller
public class LoanController extends BaseController {

    //创建或者修改订单
    @RequestMapping(value = "/trade/new_loan", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse newLoan(@RequestBody RsaveLoan loan) {
        Long LoanId = snowflake.next();
        try {
            TLoanDetail tLoanDetail = new TLoanDetail();
            //设置 loan 部分
            TLoan tLoan = new TLoan();
            if (checkLoan(loan.getLid())) {
                tLoan = itLoanService.findById(loan.getLid());
            } else {
                tLoan.setId(LoanId);
                if (loan.getBuyerAddress() == 0) {
                    System.out.println("买家账户id" + loan.getBuyerAccount());
                    TAccountAddress address = itAccountService.getDefaultAddress(loan.getBuyerAccount());
                    if (address != null) {
                        tLoan.setBuyerAddress(address);
                    } else {
                        throw new Links86Exception("此买家账户未设置默认地址!");
                    }
                } else {
                    if (checkAccountAddress(loan.getBuyerAddress())) {
                        tLoan.setBuyerAddress(itAccountService.findAddressById(loan.getBuyerAddress()));
                    } else {
                        throw new Links86Exception("卖家地址不存在!");
                    }
                }
                tLoan.setCreatedTime(new Date());
                tLoan.setDescription(loan.getDescription());
                tLoan.setStatus(LoanStatus.UNPAID);
                tLoan.setUpdatedTime(new Date());
                //因为 cascade=all 所以此处不需要存储tloan 直接放入到loanDetail中一起存储
                itLoanService.updateLoan(tLoan);
            }
            if (checkLoanDetail(loan.getLoanDetail())) {
                tLoanDetail.setId(loan.getLoanDetail());
            } else {
                tLoanDetail.setId(snowflake.next());
            }
            tLoanDetail.setCommodityAmount(loan.getCommodityAmount());
            tLoanDetail.setCount(loan.getCount());
            if (checkAccount(loan.getSellerAccount()))
                tLoanDetail.setSellerAccount(itAccountService.findById(loan.getSellerAccount()));
            if (checkProduct(loan.getProductId()))
                tLoanDetail.settProduct(itProductService.findById(loan.getProductId()));
            tLoanDetail.settLoan(tLoan);
            itLoanService.newLoan(tLoanDetail);
            BigDecimal totalAmount = itAccountService.computeAccountAmount(tLoan.getBuyerAddress().gettAccount().getId());
            return buildSuccess().addObject("data", LoanId.toString()).addObject("totalAmount", totalAmount);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }
    @RequestMapping(value = "/trade/delete_loan", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse deleteLoan(@RequestBody RsaveLoan loan) {
        try{
            if (checkLoan(loan.getLid())) {
                itLoanService.deleteLoan(loan.getLid());
                return buildSuccess();
            }else{
                throw new Links86Exception("订单id不存在!");
            }
        }
        catch (Links86Exception e){
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }

    }

    @RequestMapping(value = "/trade/update_loan", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse updateLoan(@RequestBody RsaveLoan loan) {
        try {
            //设置 loan 部分
            TLoan tLoan;
            if (checkLoan(loan.getLid())) {
                tLoan = itLoanService.findById(loan.getLid());
                if (checkAccountAddress(loan.getBuyerAddress())) {
                    tLoan.setBuyerAddress(itAccountService.findAddressById(loan.getBuyerAddress()));
                } else {
                    throw new Links86Exception("卖家地址不存在!");
                }
                tLoan.setDescription(loan.getDescription()); //设置地址
                if (checkPaidChannel(loan.getPaidChannel()))
                    tLoan.setPaidChannel(PaidChannel.valueOf(loan.getPaidChannel()));
                if (checkLoanStatus(loan.getLoanStatus()))
                    tLoan.setStatus(LoanStatus.valueOf(loan.getLoanStatus()));
                tLoan.setUpdatedTime(new Date());
                itLoanService.updateLoan(tLoan);
                return buildSuccess();
            } else {
                throw new Links86Exception("订单id不存在!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    @RequestMapping(value = "/trade/update_loan_detail", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse updateLoanDetail(@RequestBody RsaveLoan loan) {
        try {
            //设置 loan 部分
            TLoanDetail tLoanDetail;
            if (checkLoanDetail(loan.getLoanDetail())) {
                tLoanDetail = itLoanService.findByLId(loan.getLoanDetail());
                tLoanDetail.setCommodityAmount(loan.getCommodityAmount());
                tLoanDetail.setCount(loan.getCount());
                itLoanService.updateLoanDetail(tLoanDetail);
                return buildSuccess();
            } else {
                throw new Links86Exception("子订单id不存在!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    //查询订单详情
    @RequestMapping(value = "/trade/get_loan", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getLoan(@RequestBody RgetLoan loan) {
        try {
            if (checkLoan(loan.getId()))
                return buildSuccess().addObject("data", itLoanService.findById(loan.getId()));
            else
                throw new Links86Exception("订单信息不存在!");
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //查询订单详情
    @RequestMapping(value = "/trade/get_loan_detail", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getLoanDetail(@RequestBody RgetLoan loan) {
        setPageRequest(loan.getPageIndex(), loan.getPageSize(), loan.getDirection(), loan.getOrderFields());
        try {
            if (checkLoan(loan.getId())) {
                Page page= itLoanService.findByLoanId(loan.getId(), pageRequest);
                TLoanDetail tLoanDetail= (TLoanDetail) page.getContent().get(0);
                return buildSuccess().addObject("data",tLoanDetail );
            } else {
                throw new Links86Exception("订单信息不存！");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //查询通过买家账户订单详情
    @RequestMapping(value = "/trade/get_loan_detail_by_buyer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getLoanDetailByBuyer(@RequestBody RgetLoan loan) {
        setPageRequest(loan.getPageIndex(), loan.getPageSize(), loan.getDirection(), loan.getOrderFields());
        try {
            Page<TLoanDetail> tLoanDetails;
            if (checkAccount(loan.getBuyerAccount())) {
                if (checkLoanStatus(loan.getLoanStatus())) {
                    if (loan.getBeginCTime() != null && loan.getEndCTime() != null) {
                        tLoanDetails = itLoanService.findDetailByBuyer(loan.getBuyerAccount(), loan.getBeginCTime(), loan.getEndCTime(), loan.getLoanStatus(), pageRequest);
                    } else if (loan.getEndCTime() != null) {
                        tLoanDetails = itLoanService.findDetailByBuyer(loan.getBuyerAccount(), null, loan.getEndCTime(), loan.getLoanStatus(), pageRequest);
                    } else if (loan.getBeginCTime() != null) {
                        tLoanDetails = itLoanService.findDetailByBuyer(loan.getBuyerAccount(), loan.getBeginCTime(), null, loan.getLoanStatus(), pageRequest);
                    } else {
                        tLoanDetails = itLoanService.findDetailByBuyer(loan.getBuyerAccount(), loan.getLoanStatus(), pageRequest);
                    }
                } else {
                    tLoanDetails = itLoanService.findDetailByBuyer(loan.getBuyerAccount(), pageRequest);
                }
                return buildSuccess().addObject("data", tLoanDetails);
            } else {
                throw new Links86Exception("买家账户id输入有错误");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //查询子订单详情通过子订单
    @RequestMapping(value = "/trade/get_loan_detail_by_id", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getLoanDetailById(@RequestBody RgetLoan loan) {
        setPageRequest(loan.getPageIndex(), loan.getPageSize(), loan.getDirection(), loan.getOrderFields());
        try {
            Page<TLoanDetail> tLoanDetails;
            if (checkLoan(loan.getId())) {
                if (checkLoanStatus(loan.getLoanStatus())) {
                    if (loan.getBeginCTime() != null && loan.getEndCTime() != null) {
                        tLoanDetails = itLoanService.findByLoanId(loan.getId(), loan.getBeginCTime(), loan.getEndCTime(), loan.getLoanStatus(), pageRequest);
                    } else if (loan.getEndCTime() != null) {
                        tLoanDetails = itLoanService.findByLoanId(loan.getId(), null, loan.getEndCTime(), loan.getLoanStatus(), pageRequest);
                    } else if (loan.getBeginCTime() != null) {
                        tLoanDetails = itLoanService.findByLoanId(loan.getId(), loan.getBeginCTime(), null, loan.getLoanStatus(), pageRequest);
                    } else {
                        tLoanDetails = itLoanService.findByLoanId(loan.getId(), loan.getLoanStatus(), pageRequest);
                    }
                } else {
                    tLoanDetails = itLoanService.findByLoanId(loan.getId(), pageRequest);
                }
                return buildSuccess().addObject("data", tLoanDetails);
            } else {
                throw new Links86Exception("买家账户id输入有错误");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    //支付订单
    @RequestMapping(value = "/trade/paid_loan", method = {RequestMethod.POST}, consumes = "application/json")
    @Transactional
//    @ResponseBody
    public String paidLoan(@RequestBody RpaidLoan rpaidLoan, final Map<String, Object> model,HttpServletRequest request) {
        try {
            if (itLoanService.exists(rpaidLoan.getId())) {
                TLoan tloan = itLoanService.findById(rpaidLoan.getId());
                if (tloan.getStatus().equals(LoanStatus.UNPAID)) {
                    BigDecimal totalAmount = itLoanService.computeTotalAmount(tloan.getId());
                    String paidChannel = rpaidLoan.getPaidChannel();
                    if (PaidChannel.YL.equals(paidChannel)) {
                        //银联支付，暂时略
                    }else if(PaidChannel.PP.getCode().equals(paidChannel)){
                        try {

                        SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
                        SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();


                       /* request.getSession().setAttribute("paymentType","Sale");*/
                        List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
                        PaymentDetailsItemType item=new PaymentDetailsItemType();
                        BasicAmountType amt=new BasicAmountType();
                        amt.setCurrencyID(CurrencyCodeType.fromValue("USD")); //币种需要配置
                        amt.setValue(String.valueOf(totalAmount));
                        item.setQuantity(1);//默认数量为一个 以后再调整
                        item.setName(tloan.getDescription());
                        item.setAmount(amt);
                        item.setItemCategory(ItemCategoryType.fromValue("Physical"));//虚拟产品或者实物产品
                        lineItems.add(item);

                        details.setReturnURL(payPalConfig.return_url+"?currencyCodeType=USD");
                        details.setCancelURL(payPalConfig.cancel_url);

                        List<PaymentDetailsType> payDetails = new ArrayList<PaymentDetailsType>();
                        PaymentDetailsType paydtl = new PaymentDetailsType();
                        BasicAmountType itemsTotal = new BasicAmountType();
                        itemsTotal.setValue(String.valueOf(totalAmount));
                        itemsTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
                        paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType.fromValue("USD"),String.valueOf(totalAmount)));
                        paydtl.setPaymentDetailsItem(lineItems);
                        paydtl.setItemTotal(itemsTotal);
                        paydtl.setPaymentRequestID(String.valueOf(tloan.getId()));

                        AddressType shipToAddress=new AddressType();
                        shipToAddress.setCountry(CountryCodeType.fromValue("US"));
                        paydtl.setShipToAddress(shipToAddress);
                        paydtl.setPaymentAction(PaymentActionCodeType.fromValue("Sale"));

                        payDetails.add(paydtl);
                        details.setPaymentDetails(payDetails);


                        setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);
                        SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
                        expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
                        SetExpressCheckoutResponseType setExpressCheckoutResponse = getService().setExpressCheckout(expressCheckoutReq);
                            if (setExpressCheckoutResponse != null) {
                                if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
                                    String token=setExpressCheckoutResponse.getToken();
                                    model.put("sHtmlText",rebulidRequest(token));
                                    return "page/paypal";
                                }
                            }

                        } catch (SSLConfigurationException e) {
                            e.printStackTrace();
                        } catch (InvalidCredentialException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (HttpErrorException e) {
                            e.printStackTrace();
                        } catch (InvalidResponseDataException e) {
                            e.printStackTrace();
                        } catch (ClientActionRequiredException e) {
                            e.printStackTrace();
                        } catch (MissingCredentialException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (OAuthException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }

                    } else if (PaidChannel.ZFB.getCode().equals(paidChannel)) {
                        Map<String, String> sParaTemp = new HashMap<>();
                        sParaTemp.put("service", alipayConfig.service);
                        sParaTemp.put("partner", alipayConfig.partner);
                        sParaTemp.put("seller_id", alipayConfig.seller_id);
                        sParaTemp.put("_input_charset", alipayConfig.input_charset);
                        sParaTemp.put("payment_type", alipayConfig.payment_type);
                        sParaTemp.put("notify_url", alipayConfig.notify_url);
                        sParaTemp.put("return_url", alipayConfig.return_url);
                        sParaTemp.put("anti_phishing_key", alipayConfig.anti_phishing_key);
                        sParaTemp.put("exter_invoke_ip", alipayConfig.exter_invoke_ip);
                        sParaTemp.put("out_trade_no", String.valueOf(tloan.getId()));
                        sParaTemp.put("subject", tloan.getDescription());
                        sParaTemp.put("total_fee", String.valueOf(totalAmount));
                        sParaTemp.put("body", tloan.getDescription());
                        String  sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认", alipayConfig);

                        model.put("sHtmlText", sHtmlText);
                        return "page/alipayapi";
                    } else if (PaidChannel.WX.getCode().equals(paidChannel)) {
                        //微信支付,暂时略
                    } else if (PaidChannel.ACT.getCode().equals(paidChannel)) {
                        //账户支付
                        BigDecimal accountAmount = itAccountService.computeAccountAmount(tloan.getBuyerAddress().gettAccount().getId()); //获取账户总金额
                        if (accountAmount.compareTo(totalAmount) > 0) {
                            //设置账户金额变更
                            TAccountDetail payDetail = new TAccountDetail();
                            payDetail.setId(snowflake.next());
                            payDetail.setNumber(snowflake.next());
                            payDetail.setAmount(totalAmount.negate());
                            payDetail.settAccount(tloan.getBuyerAddress().gettAccount());
                            payDetail.setUpdatedTime(new Date(System.currentTimeMillis()));
                            itAccountService.addAccountDetail(payDetail);

                            //设置账户金额变更
                            TAccountDetail sellDetail = new TAccountDetail();
                            sellDetail.setId(snowflake.next());
                            sellDetail.setNumber(snowflake.next());
                            sellDetail.setAmount(totalAmount);
                            //通过订单id获取卖家账户
                            long id = itLoanService.findSellerId(tloan.getId());
                            sellDetail.settAccount(itAccountService.findById(id)); //获取企业id
                            sellDetail.setUpdatedTime(new Date(System.currentTimeMillis()));
                            itAccountService.addAccountDetail(sellDetail);

                            //支付订单
                            tloan.setStatus(LoanStatus.PAID_OFF);
                            itLoanService.updateLoan(tloan);
                            return "page/act_pay";
                        } else {
                            throw new Links86Exception("账户余额不够，请选择其他支付方式!");
                        }
                    }
                } else {
                    throw new Links86Exception("订单状态无效，不能完成支付!");
                }
            } else {
                throw new Links86Exception("订单号码无效!");
            }
            return "page/error";
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return "page/error";
        }
    }

    /**
     * paypal 回调方法
     *
     */
    @RequestMapping(value = "/trade/paypal_return_url", method = {RequestMethod.POST, RequestMethod.GET})
    public String ppReturnUrl(HttpServletRequest request,HttpServletResponse response) {

        GetExpressCheckoutDetailsReq req = new GetExpressCheckoutDetailsReq();
        GetExpressCheckoutDetailsRequestType reqType = new GetExpressCheckoutDetailsRequestType(request.getParameter("token"));
        req.setGetExpressCheckoutDetailsRequest(reqType);

        try {
            GetExpressCheckoutDetailsResponseType resp = getService().getExpressCheckoutDetails(req);
        if (resp != null) {
            if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {

                DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
                DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();

                details.setToken(resp.getGetExpressCheckoutDetailsResponseDetails().getToken());
                details.setPayerID(resp.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
                PaymentDetailsType paymentDetails = new PaymentDetailsType();
                BasicAmountType orderTotal = new BasicAmountType();
                orderTotal.setValue(resp.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails().get(0).getOrderTotal().getValue());
                orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
                paymentDetails.setOrderTotal(orderTotal);

                List<PaymentDetailsType> payDetailType = new ArrayList<PaymentDetailsType>();
                payDetailType.add(paymentDetails);

                details.setPaymentDetails(payDetailType);

                doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(details);
                DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
                doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);
                DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = getService().doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
                if (doCheckoutPaymentResponseType != null) {
                    if (doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
                        String transactionID=resp.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails().get(0).getPaymentRequestID();

                        if (checkLoan(Long.valueOf(transactionID))) {
                            TLoan tLoan = itLoanService.findById(Long.valueOf(transactionID));
                           /* tLoan.setTaobaoTradeNo(trade_no);*/
                            tLoan.setStatus(LoanStatus.PAID_OFF);
                            itLoanService.updateLoan(tLoan);

                            //以下添加订单支付完成后修改账单信息中的状态
                            setPageRequest(0, 5, null, null);
                            TLoanDetail tLoanDetail= itLoanService.findByLoanId(tLoan.getBuyerAddress().gettAccount().getId(),null,null,null,pageRequest).getContent().get(0);
                            Date date=new Date();
                            TBillDetail tBillDetail=new TBillDetail();
                            tBillDetail.setAccountId(tLoan.getBuyerAddress().gettAccount().getId());
                            tBillDetail.setId(snowflake.next());
                            tBillDetail.setStatus(LoanStatus.PAID_OFF.getDescription());
                            tBillDetail.setTitle(tLoanDetail.gettProduct().getName());
                            tBillDetail.setAmount(tLoanDetail.getCommodityAmount().multiply(new BigDecimal(-1)));
                            tBillDetail.setPayType(PaidChannel.PP.getDescription());
                            tBillDetail.setTime(date);
                            itBillDetailService.save(tBillDetail);

                            TBillDetail tBillDetail1=new TBillDetail();
                            tBillDetail1.setAccountId(tLoanDetail.getSellerAccount().getId());
                            tBillDetail1.setId(snowflake.next());
                            tBillDetail1.setStatus(LoanStatus.PAID_OFF.getDescription());
                            tBillDetail1.setTitle(tLoanDetail.gettProduct().getName());
                            tBillDetail1.setAmount(tLoanDetail.getCommodityAmount());
                            tBillDetail1.setPayType(PaidChannel.PP.getDescription());
                            tBillDetail1.setTime(date);
                            itBillDetailService.save(tBillDetail1);
                            //结束

                          /*  //给购买产品的用户添加该商品的拥有数据 begin
                            TAccountItem tAccountItem=new TAccountItem();
                            tAccountItem.setId(snowflake.next());
                            tAccountItem.setInUse(true);
                            tAccountItem.setCount(1);
                            tAccountItem.settProduct(tLoanDetail.gettProduct());
                            tAccountItem.settAccount(tLoan.getBuyerAddress().gettAccount());
                            itAccountService.addAccountItem(tAccountItem);
                            //end*/
                            /*long uid=itUserService.findById(tLoan.getBuyerAddress().gettAccount().getId()).getOutUid();*/
                        }


                        return "redirect:"+payPalConfig.success_return_url+"?orderInfo="+transactionID;
                    }else{
                        return "redirect:"+payPalConfig.fail_return_url;
                    }
                }else{
                    return "redirect:"+payPalConfig.fail_return_url;
                }
            } else {
                return "回调失败";

            }
        }
        } catch (SSLConfigurationException e) {
            e.printStackTrace();
        } catch (InvalidCredentialException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpErrorException e) {
            e.printStackTrace();
        } catch (InvalidResponseDataException e) {
            e.printStackTrace();
        } catch (ClientActionRequiredException e) {
            e.printStackTrace();
        } catch (MissingCredentialException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (OAuthException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;

    }


    // notify_url 使用注意：
    // 1. 程序执行完后必须打印输出“success”（不包含引号）。如果商户反馈给支付宝的字符不是success这7个字符，支付宝服务器会不断重发通知，直到超过24小时22分钟。
    //    一般情况下，25小时以内完成8次通知（通知的间隔频率一般是：4m,10m,10m,1h,2h,6h,15h）；
    // 2. 支付宝每一次退款成功，都会向商户的即时到账异步通知地址notify_url发送一条交易成功的通知，其中状态有refund_status、refund_success。
    // ref: https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.AsuJIp&treeId=62&articleId=104745&docType=1 
    //
    //订单支付回调接口，内容回调 ,consumes = {"application/json; charset=utf8", "application/xml; charset=utf8"}
    @RequestMapping(value = "/trade/notify_url", method = {RequestMethod.POST})
    @ResponseBody
    @Transactional
    public String outNotifyUrl(HttpServletRequest request) {
        try {
            logger.debug("notify url works...");
            Map requestParams = request.getParameterMap(); //请求参数集合
            Map<String, String> params = new HashMap<String, String>();  //中途存储集合
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }

            //参数列表
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            if (AlipayNotify.verify(params)) { //验证成功
                if (trade_status.equals("TRADE_FINISHED")) {
                } else if (trade_status.equals("TRADE_SUCCESS")) {

                }

                if (checkLoan(Long.valueOf(out_trade_no))) {
                    logger.debug("notify in");
                    TLoan tLoan = itLoanService.findById(Long.valueOf(out_trade_no));
                    if(!tLoan.getStatus().equals(LoanStatus.PAID_OFF)){
                        logger.debug("notify save");
                        tLoan.setTaobaoTradeNo(trade_no);
                        tLoan.setStatus(LoanStatus.PAID_OFF);
                        itLoanService.updateLoan(tLoan);

                        //以下添加订单支付完成后修改账单信息中的状态
                        setPageRequest(0, 5, null, null);
                        TLoanDetail tLoanDetail= itLoanService.findByLoanId(tLoan.getId(),null,null,null,pageRequest).getContent().get(0);
                        Date date=new Date();
                        TBillDetail tBillDetail=new TBillDetail();
                        tBillDetail.setAccountId(tLoan.getBuyerAddress().gettAccount().getId());
                        tBillDetail.setId(snowflake.next());
                        tBillDetail.setStatus(LoanStatus.PAID_OFF.getDescription());
                        tBillDetail.setTitle(tLoanDetail.gettProduct().getName());
                        tBillDetail.setAmount(tLoanDetail.getCommodityAmount().multiply(new BigDecimal(-1)));
                        tBillDetail.setPayType(PaidChannel.ZFB.getDescription());
                        tBillDetail.setTime(date);
                        itBillDetailService.save(tBillDetail);

                        TBillDetail tBillDetail1=new TBillDetail();
                        tBillDetail1.setAccountId(tLoanDetail.getSellerAccount().getId());
                        tBillDetail1.setId(snowflake.next());
                        tBillDetail1.setStatus(LoanStatus.PAID_OFF.getDescription());
                        tBillDetail1.setTitle(tLoanDetail.gettProduct().getName());
                        tBillDetail1.setAmount(tLoanDetail.getCommodityAmount());
                        tBillDetail1.setPayType(PaidChannel.ZFB.getDescription());
                        tBillDetail1.setTime(date);
                        itBillDetailService.save(tBillDetail1);
                    }
                    else {
                    	logger.debug("notify already paid off by return");
                    }
                    //结束

              /*  //给购买产品的用户添加该商品的拥有数据 begin
                TAccountItem tAccountItem=new TAccountItem();
                tAccountItem.setId(snowflake.next());
                tAccountItem.setInUse(true);
                tAccountItem.setCount(1);
                tAccountItem.settProduct(tLoanDetail.gettProduct());
                tAccountItem.settAccount(tLoan.getBuyerAddress().gettAccount());
                itAccountService.addAccountItem(tAccountItem);
                //end*/
                /*long uid=itUserService.findById(tLoan.getBuyerAddress().gettAccount().getId()).getOutUid();*/

                }
                return "success";
            } else { //验证失败
                return "fail";
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return "fail";
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return "fail";
        }
    }

    //订单支付回调接口，内容回调 ,consumes = {"application/json; charset=utf8", "application/xml; charset=utf8"}
    @RequestMapping(value = "/trade/return_url", method = {RequestMethod.POST, RequestMethod.GET})
    @Transactional
    public String outReturnUrl(HttpServletRequest request) {
        String out_trade_no_fail=null;
        try {
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            if (checkLoan(Long.valueOf(out_trade_no))) {
                out_trade_no_fail=out_trade_no;
                logger.debug("return in");
                TLoan tLoan = itLoanService.findById(Long.valueOf(out_trade_no));
                if(!tLoan.getStatus().equals(LoanStatus.PAID_OFF)){
                    tLoan.setTaobaoTradeNo(trade_no);
                    tLoan.setStatus(LoanStatus.PAID_OFF);
                    itLoanService.updateLoan(tLoan);

                    //以下添加订单支付完成后修改账单信息中的状态
                    setPageRequest(0, 5, null, null);
                    TLoanDetail tLoanDetail= itLoanService.findByLoanId(tLoan.getId(),null,null,null,pageRequest).getContent().get(0);
                                      
                    Date date=new Date();
                    TBillDetail tBillDetail=new TBillDetail();
                    tBillDetail.setAccountId(tLoan.getBuyerAddress().gettAccount().getId());
                    tBillDetail.setId(snowflake.next());
                    tBillDetail.setStatus(LoanStatus.PAID_OFF.getDescription());
                    tBillDetail.setTitle(tLoanDetail.gettProduct().getName());
                    tBillDetail.setAmount(tLoanDetail.getCommodityAmount().multiply(new BigDecimal(-1)));
                    tBillDetail.setPayType(PaidChannel.ZFB.getDescription());
                    tBillDetail.setTime(date);
                    itBillDetailService.save(tBillDetail);

                    TBillDetail tBillDetail1=new TBillDetail();
                    tBillDetail1.setAccountId(tLoanDetail.getSellerAccount().getId());
                    tBillDetail1.setId(snowflake.next());
                    tBillDetail1.setStatus(LoanStatus.PAID_OFF.getDescription());
                    tBillDetail1.setTitle(tLoanDetail.gettProduct().getName());
                    tBillDetail1.setAmount(tLoanDetail.getCommodityAmount());
                    tBillDetail1.setPayType(PaidChannel.ZFB.getDescription());
                    tBillDetail1.setTime(date);
                    itBillDetailService.save(tBillDetail1);
                    
                    logger.debug("return save==="+
                    		";loadid:"  + tLoan.getId() +
                    		";buyerid:" + tLoan.getBuyerAddress().gettAccount().getId() +
                    		";buyamnt:" + tLoanDetail.getCommodityAmount().multiply(new BigDecimal(-1)) +
                    		";sellerid:"+ tLoanDetail.getSellerAccount().getId());
                

                }
                else {
                	logger.debug("return already paid off by notify");
                }
                //结束

              /*  //给购买产品的用户添加该商品的拥有数据 begin
                TAccountItem tAccountItem=new TAccountItem();
                tAccountItem.setId(snowflake.next());
                tAccountItem.setInUse(true);
                tAccountItem.setCount(1);
                tAccountItem.settProduct(tLoanDetail.gettProduct());
                tAccountItem.settAccount(tLoan.getBuyerAddress().gettAccount());
                itAccountService.addAccountItem(tAccountItem);
                //end*/
                /*long uid=itUserService.findById(tLoan.getBuyerAddress().gettAccount().getId()).getOutUid();*/
                return "redirect:"+alipayConfig.success_return_url+"?orderInfo="+out_trade_no;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "redirect:"+alipayConfig.fail_return_url+"?orderInfo="+out_trade_no_fail;
        }
        return "redirect:"+alipayConfig.fail_return_url+"?orderInfo="+out_trade_no_fail;
    }

    /**
     * 该方法是用于查询订单和订单明细（没有订单和订单明细概念）
     * 及订单和订单明细的请求都是该方法
     *
     * @return
     * @author xusen
     */
    @RequestMapping(value = "/trade/getLoanAndDetail", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse getLoanAndDetail(@RequestBody RgetLoanDetail rgetLoanDetail) {
        if(!"".equals(rgetLoanDetail.getBuyerId())||!"".equals(rgetLoanDetail.getSellerId())){
            setPageRequest(rgetLoanDetail.getPageIndex(), rgetLoanDetail.getPageSize(), rgetLoanDetail.getDirection(), rgetLoanDetail.getOrderFields());
            Page list = itLoanService.findLoanAndDetail(rgetLoanDetail.getBeginTime(), rgetLoanDetail.getEndTime(), rgetLoanDetail.getSelectParam(), rgetLoanDetail.getBuyerId(), rgetLoanDetail.getSellerId(), pageRequest);
            return buildSuccess().addObject("data", list);
        }else{
            return buildFailed().addObject("exception","账户id不存在");
        }
    }

    //set get do 三个api的service调用
    public  PayPalAPIInterfaceServiceService getService(){
        Map<String,String> configMap = new HashMap<String,String>();
        configMap.put("mode", payPalConfig.mode);
        configMap.put("acct1.UserName", payPalConfig.userName);
        configMap.put("acct1.Password", payPalConfig.password);
        configMap.put("acct1.Signature", payPalConfig.signature);
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configMap);
        return service;
    }

    //拼接form表单请求
    public String rebulidRequest(String token){
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<form id=\"paypalsubmit\" name=\"paypalsubmit\" action=\"" + payPalConfig.login_url+"\">");
        sbHtml.append("<input type=\"hidden\" name=\""+"cmd"+"\" value=\"" + "_express-checkout" + "\"/>");
        sbHtml.append("<input type=\"hidden\" name=\""+"token"+"\" value=\"" + token + "\"/>");
        sbHtml.append("<input type=\"submit\" value=\"" +"提交"+ "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['paypalsubmit'].submit();</script>");
        return sbHtml.toString();
    }

//
//    //模糊查询订单列表
//
//    //订单删除,放到错误表,完成的订单放到完成表
//
//    //查询用户的订购包的内容
}
