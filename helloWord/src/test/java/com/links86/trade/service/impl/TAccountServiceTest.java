package com.links86.trade.service.impl;

import com.links86.trade.domain.*;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.domain.em.ProductStatus;
import com.links86.trade.utils.Snowflake;
import com.links86.util.UKUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by leoxu on 2016/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TAccountServiceTest {
    @Autowired
    Snowflake snowflake;

    @Autowired
    TProductService tProductService;

    @Autowired
    TUserService tUserService;

    @Autowired
    TServerSev tServerSev;

    @Autowired
    TAccountService tAccountService;

    @Test
    public void bindAccount() throws Exception {
        TUser user = new TUser();
        user.setId(snowflake.next());
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);
        TAccount tAccount = new TAccount();
        tAccount.setFreeze(false);
        tAccount.setId(snowflake.next());
        tAccount.setName("用户账户01");
        tAccount.settUser(user);
        tAccountService.bindAccount(tAccount);


        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        TAccount tAccount2 = new TAccount();
        tAccount2.setFreeze(false);
        tAccount2.setId(snowflake.next());
        tAccount2.setName("用户账户02");
        tAccount2.settUser(company);
        tAccountService.bindAccount(tAccount2);

        System.out.println("===============================");

    }

    @Test
    public void unpackAccount() throws Exception {
        TUser user = new TUser();
        user.setId(snowflake.next());
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);
        TAccount tAccount = new TAccount();
        tAccount.setFreeze(false);
        tAccount.setId(snowflake.next());
        tAccount.setName("用户账户01");
        tAccount.settUser(user);
        tAccountService.bindAccount(tAccount);


        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        TAccount tAccount2 = new TAccount();
        tAccount2.setFreeze(false);
        tAccount2.setId(snowflake.next());
        tAccount2.setName("用户账户02");
        tAccount2.settUser(company);
        tAccountService.bindAccount(tAccount2);

        System.out.println("=============================");

        tAccountService.unpackAccount(user.getId());
        tAccountService.unpackAccount(company.getId());

        System.out.println("=============================");

    }

    @Test
    public void updateAccount() throws Exception {

    }

    @Test
    public void findById() throws Exception {
        TUser user = new TUser();
        user.setId(snowflake.next());
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);
        TAccount tAccount = new TAccount();
        tAccount.setFreeze(false);
        tAccount.setId(snowflake.next());
        tAccount.setName("用户账户01");
        tAccount.settUser(user);
        tAccountService.bindAccount(tAccount);

        TAccount tAccount1 = tAccountService.findById(tAccount.getId());
        System.out.println(tAccount1.getId());
    }

    @Test
    public void findByUserId() throws Exception {
        TUser user = new TUser();
        user.setId(snowflake.next());
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);

        TAccount tAccount = new TAccount();
        tAccount.setFreeze(false);
        tAccount.setId(snowflake.next());
        tAccount.setName("用户账户01");
        tAccount.settUser(user);
        tAccountService.bindAccount(tAccount);
        TAccount tAccount1 = tAccountService.findByUserId(user.getId(),AccountType.COMPANY);
        System.out.println(tAccount1.getId());

    }

    @Test
    public void findByCompanyId() throws Exception {
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        TAccount tAccount2 = new TAccount();
        tAccount2.setFreeze(false);
        tAccount2.setId(snowflake.next());
        tAccount2.setName("用户账户02");
        tAccount2.settUser(company);
        tAccountService.bindAccount(tAccount2);

        TAccount tAccount = tAccountService.findByUserId(company.getId(),AccountType.COMPANY);
        System.out.println(tAccount.getId());
    }

    @Test
    public void findAddressByAId() throws Exception {

    }

    @Test
    public void addAddress() throws Exception {

    }

    @Test
    public void defaultAddress() throws Exception {

    }

    @Test
    public void updateAddress() throws Exception {

    }

    @Test
    public void deleteAddress() throws Exception {

    }

    @Test
    public void findAddressById() throws Exception {

    }

    @Test
    public void addAccountDetail() throws Exception {

    }

    @Test
    public void findDetailByAId() throws Exception {
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("1haosongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        TAccount tAccount2 = new TAccount();
        tAccount2.setFreeze(false);
        tAccount2.setId(snowflake.next());
        tAccount2.setName("用户账户02");
        tAccount2.settUser(company);
        tAccountService.bindAccount(tAccount2);

        TUser company2 = new TUser();
        company2.setId(snowflake.next());
        company2.setName("2haogongsi");
        company2.setType(AccountType.COMPANY);
        company2.setUpdateTime(new Date());
        tUserService.save(company2);

        TAccount tAccount3 = new TAccount();
        tAccount3.setFreeze(false);
        tAccount3.setId(snowflake.next());
        tAccount3.setName("用户账户03");
        tAccount3.settUser(company2);
        tAccountService.bindAccount(tAccount3);

        for (int i = 0; i < 10; i++) {
            TAccountDetail tAccountDetail = new TAccountDetail();
            tAccountDetail.setId(snowflake.next());
            tAccountDetail.setAmount(new BigDecimal(1000));
            tAccountDetail.settAccount(tAccount2);
            tAccountDetail.setUpdatedTime(new Date());
            tAccountDetail.setNumber(snowflake.next());
            tAccountService.addAccountDetail(tAccountDetail);

            TAccountDetail tAccountDetail2 = new TAccountDetail();
            tAccountDetail2.setId(snowflake.next());
            tAccountDetail2.setAmount(new BigDecimal(1000));
            tAccountDetail2.settAccount(tAccount2);
            tAccountDetail2.setUpdatedTime(new Date());
            tAccountDetail2.setNumber(snowflake.next());
            tAccountService.addAccountDetail(tAccountDetail2);
        }


        List<TAccountDetail> tAccountDetails = tAccountService.findDetailByAId(tAccount2.getId(), new PageRequest(0, 10)).getContent();

        for (int i = 0; i < 10; i++) {
            System.out.println(tAccountDetails.get(i).getId());
        }
    }

    @Test
    public void findDetailByAId1() throws Exception {

    }

    @Test
    public void findDetailByNumber() throws Exception {

    }

    @Test
    public void computeAccountAmount() throws Exception {

    }

    @Test
    public void addAccountItem() throws Exception {
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        company.setName("gongsiyihao");
        tUserService.save(company);

        TAccount tAccount = new TAccount();
        tAccount.setId(snowflake.next());
        tAccount.setFreeze(false);
        tAccount.setName("gogogogogogo");
        tAccount.settUser(company);
        tAccountService.bindAccount(tAccount);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tmp.setName("product" + i);
            tProductService.save(tmp);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }
        List<TAccountItem> accountItems = tAccountService.findItemByAccountId(tAccount.getId(),new PageRequest(0,3)).getContent();

        for(int i=0;i<accountItems.size();i++){
            System.out.println(accountItems.get(i).getId()+"!!!!!");
        }

    }

    @Test
    public void findItemByAccountId() throws Exception {

    }

    @Test
    public void exists() throws Exception {

    }

    @Test
    public void existAddress() throws Exception {

    }

    @Test
    public void existDetail() throws Exception {

    }

    @Test
    public void existItem() throws Exception {

    }

}