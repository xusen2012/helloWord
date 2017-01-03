package com.links86.trade.controller;

import com.alipay.config.AlipayConfig;
import com.ebay.config.PayPalConfig;
import com.links86.trade.domain.em.*;
import com.links86.trade.domain.to.CResponse;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.service.*;
import com.links86.trade.utils.Snowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;


/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 15:21
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class BaseController {
    protected Logger logger;

    //使用父类级别的调用避免每个方法都使用Header注解
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected AlipayConfig alipayConfig;

    /**
     * 修改了 logger 的生成方式,让打印时,根据具体情况来生成打印类
     */
    BaseController() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }

    public PageRequest pageRequest;

    @Autowired
    protected Snowflake snowflake;

    @Autowired
    protected ITAccountService itAccountService;

    @Autowired
    protected ITUserService itUserService;

    @Autowired
    protected ITProductService itProductService;

    @Autowired
    protected ITServerSev itServerSev;

    @Autowired
    protected ITLoanService itLoanService;

    @Autowired
    protected ITBillDetailService itBillDetailService;

    //paypal 配置类
    @Autowired
    protected PayPalConfig payPalConfig;
    //end
    /**
     * 使用 spring 默认的分页方式
     * 返回一个分页控制对象默认用id 顺序
     */
    void defaultRequest() {
        pageRequest = new PageRequest(0, 10, Sort.Direction.ASC, "id");
    }

    /**
     * 返回一个附带分页属性的分页对象
     *
     * @param page       分页页码
     * @param pagesize   页面总数
     * @param direction  排序方式
     * @param properties 排序字段簇
     */
    int setPageRequest(int page, int pagesize, String direction, String... properties) {
        if (page <= 0)
            page = 0;
        if (pagesize <= 0)
            pagesize = 8;
        if (direction != null && properties == null) {
            pageRequest = new PageRequest(page, pagesize, new Sort(Sort.Direction.valueOf(direction)));
        }
        if (direction != null && properties != null) {
            pageRequest = new PageRequest(page, pagesize, new Sort(Sort.Direction.valueOf(direction), properties));
        }
        if (direction == null && properties == null) {
            pageRequest = new PageRequest(page, pagesize);
        }
        return 0;
    }

    CResponse buildSuccess() {
        CResponse response = new CResponse();
        response.setCode("200");
        response.setType("TRADE");
        response.setMessage("success");
        return response;
    }

    CResponse buildFailed() {
        CResponse response = new CResponse();
        response.setCode("230");
        response.setType("TRADE");
        response.setMessage("error");
        return response;
    }

    boolean checkAccount(Long id) {
        if (id == null)
            throw new Links86Exception("账户id不能为空!");
        if (!itAccountService.exists(id))
            return false;
        return true;
    }

    //
    boolean checkAccountAddress(Long id) {
        if (id == null)
            throw new Links86Exception("账户地址id不能为空!");
        if (!itAccountService.existAddress(id))
            return false;
        return true;
    }

    //
    boolean checkAccountDetail(Long id) {
        if (id == null)
            throw new Links86Exception("账户流水id不能为空!");
        if (!itAccountService.existDetail(id))
            return false;
        return true;
    }

    boolean checkCompany(Long id) {
        if (id == null)
            throw new Links86Exception("企业id不能为空!");
        if (itUserService.exists(id)) {
            if (itUserService.findById(id).getType().equals(AccountType.COMPANY)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    boolean checkAccountItem(Long id) {
        if (id == null)
            throw new Links86Exception("商品id不能为空!");
        if (!itAccountService.existItem(id))
            return false;
        return true;
    }

    //
    boolean checkUser(Long id) {
        if (id == null)
            throw new Links86Exception("用户id不能为空!");
        if (!itUserService.exists(id))
            return false;
        return true;
    }

    //
    boolean checkProduct(Long id) {
        if (id == null)
            throw new Links86Exception("产品id不能为空!");
        if (!itProductService.exists(id))
            return false;
        return true;
    }

    //
    boolean checkServer(Long id) {
        if (id == null)
            throw new Links86Exception("服务id不能为空!");
        if (!itServerSev.exists(id))
            return false;
        return true;
    }

    //
    boolean checkLoan(Long id) {
        if (id == null)
            throw new Links86Exception("订单id不能为空!");
        if (!itLoanService.exists(id))
            return false;
        return true;
    }

    boolean checkLoanDetail(Long id) {
        if (id == null)
            throw new Links86Exception("子订单id不能为空!");
        if (!itLoanService.existDetail(id))
            return false;
        return true;
    }


    boolean checkLoanStatus(String status) {
        if (status == null)
            return false;
        LoanStatus loanStatus = LoanStatus.valueOf(status);
        switch (loanStatus) {
            case DELETE:
                return true;
            case PAID_OFF:
                return true;
            case PAY_FAIL:
                return true;
            case REFUNDING:
                return true;
            case UNPAID:
                return true;
            case REFUNDED:
                return true;
            case SLEEP:
                return true;
            default:
                return false;
        }
    }

    boolean checkAccountType(String type) throws Links86Exception {
        try {
            if (type == null)
                return false;
            AccountType accountType = AccountType.valueOf(type);
            switch (accountType) {
                case USER:
                    return true;
                case COMPANY:
                    return true;
                default:
                    return false;
            }
        } catch (Links86Exception e) {
            throw new Links86Exception("用户类型输入有错误!");
        }
    }

    boolean checkPaidChannel(String channel) {
        if (channel == null)
            return false;
        PaidChannel paidChannel = PaidChannel.valueOf(channel);
        switch (paidChannel) {
            case ZFB:
                return true;
            case ACT:
                return true;
            case WX:
                return true;
            case YL:
                return true;
            default:
                return false;
        }
    }

    boolean checkProductStatus(String status) {
        if (status == null)
            return false;
        ProductStatus productStatus = ProductStatus.valueOf(status);
        switch (productStatus) {
            case ON:
                return true;
            case OFF:
                return true;
            default:
                return false;
        }
    }

    boolean checkServerstatus(String status) {
        if (status == null)
            return false;
        ServerStatus serverStatus = ServerStatus.valueOf(status);
        switch (serverStatus) {
            case OFF:
                return true;
            case ON:
                return true;
            default:
                return false;
        }
    }
}
