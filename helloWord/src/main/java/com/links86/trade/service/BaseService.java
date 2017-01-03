package com.links86.trade.service;

import com.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import com.links86.trade.repo.*;
import com.links86.trade.utils.Snowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通用类
 * Project name is links86-system
 * Created by xuli on 2016-09-12 12:07
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Component
public abstract class BaseService {

    /**
     * 使用定制logger
     */
    protected Logger logger;

    @Autowired
    protected Snowflake snowflake;

    public BaseService() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }

    @Autowired
    protected TUserRepo tUserRepo;

    @Autowired
    protected TProductRepo tProductRepo;

    @Autowired
    protected TServerRepo tServerRepo;

    @Autowired
    protected TAccountRepo tAccountRepo;

    @Autowired
    protected TAccountDetailRepo tAccountDetailRepo;

    @Autowired
    protected TAccountAddressRepo tAccountAddressRepo;

    @Autowired
    protected TAccountItemRepo tAccountItemRepo;

    @Autowired
    protected TLoanRepo tLoanRepo;

    @Autowired
    protected TLoanDetailRepo tLoanDetailRepo;

}
