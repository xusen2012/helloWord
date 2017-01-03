package com.links86.trade.service.impl;

import com.links86.trade.domain.*;
import com.links86.trade.domain.em.*;
import com.links86.trade.utils.Snowflake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by leoxu on 2016/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TLoanServiceTest {

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

    @Autowired
    TLoanService tLoanService;

    @Test
    public void newLoan() throws Exception {

        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        TLoanDetail tLoanDetail = new TLoanDetail();
        tLoanDetail.settLoan(tLoan);
        tLoanDetail.setId(snowflake.next());
        tLoanDetail.settProduct(tProduct.get(0));
        tLoanDetail.setCount(3);
        tLoanDetail.setSellerAccount(tAccount);
        tLoanDetail.setCommodityAmount(new BigDecimal(1000));
        tLoanService.newLoan(tLoanDetail);

//        System.out.println("============================================");
    }

    @Test
    public void updateLoan() throws Exception {

        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);
    }

    @Test
    public void updateLoanDetail() throws Exception {
        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            tLoanDetail.settLoan(tLoan);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tProduct.get(0));
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        System.out.println("1111111111111111111111111111111111");

        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = tLoanService.findByLId(id);
            tLoanDetail.setCommodityAmount(new BigDecimal(912));
            tLoanService.updateLoanDetail(tLoanDetail);
        }

        System.out.println("hahahahahahahaahahahahahaha");
    }

    @Test
    public void findById() throws Exception {
        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            tLoanDetail.settLoan(tLoan);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tProduct.get(0));
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        System.out.println("1111111111111111111111111111111111");

        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = tLoanService.findByLId(id);
            tLoanDetail.setCommodityAmount(new BigDecimal(912));
            tLoanService.updateLoanDetail(tLoanDetail);
        }

        System.out.println("hahahahahahahaahahahahahaha");

        TLoan tLoan1 = tLoanService.findById(tLoan.getId());
        System.out.println(tLoan1.getId());
    }

    @Test
    public void findByLId() throws Exception {
        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            tLoanDetail.settLoan(tLoan);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tProduct.get(0));
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        System.out.println("1111111111111111111111111111111111");

        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = tLoanService.findByLId(id);
            tLoanDetail.setCommodityAmount(new BigDecimal(912));
            tLoanService.updateLoanDetail(tLoanDetail);
        }

        System.out.println("hahahahahahahaahahahahahaha");

        TLoan tLoan1 = tLoanService.findById(tLoan.getId());
        System.out.println(tLoan1.getId());

    }

    @Test
    public void findByLoanId() throws Exception {
        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            tLoanDetail.settLoan(tLoan);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tProduct.get(0));
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        System.out.println("1111111111111111111111111111111111");

        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = tLoanService.findByLId(id);
            tLoanDetail.setCommodityAmount(new BigDecimal(912));
            tLoanService.updateLoanDetail(tLoanDetail);
        }

        System.out.println("hahahahahahahaahahahahahaha");

        TLoan tLoan1 = tLoanService.findById(tLoan.getId());
        System.out.println(tLoan1.getId());

        List<TLoanDetail> loanDetails = tLoanService.findByLoanId(tLoan1.getId(), new PageRequest(0, 10)).getContent();
        for (int i = 0; i < loanDetails.size(); i++) {
            System.out.println(loanDetails.get(i).getCommodityAmount());
        }
    }

    @Test
    public void findDetailByBuyer() throws Exception {

        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            tLoanDetail.settLoan(tLoan);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tProduct.get(0));
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        System.out.println("1111111111111111111111111111111111");

        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = tLoanService.findByLId(id);
            tLoanDetail.setCommodityAmount(new BigDecimal(912));
            tLoanService.updateLoanDetail(tLoanDetail);
        }

        System.out.println("hahahahahahahaahahahahahaha");

        TLoan tLoan1 = tLoanService.findById(tLoan.getId());
        System.out.println(tLoan1.getId());

        List<TLoanDetail> loanDetails = tLoanService.findByLoanId(tLoan1.getId(), new PageRequest(0, 10)).getContent();
        for (int i = 0; i < loanDetails.size(); i++) {
            System.out.println(loanDetails.get(i).getCommodityAmount());
        }
    }

    @Test
    public void findDetailByBuyer1() throws Exception {
        TUser buyer = new TUser();
        buyer.setId(snowflake.next());
        buyer.setType(AccountType.USER);
        buyer.setName("某神经!!!");
        buyer.setUpdateTime(new Date());
        tUserService.save(buyer);

        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.settUser(buyer);
        tAccount2.setFreeze(false);
        tAccount2.setName("aaaaaaaaaa");
        tAccountService.bindAccount(tAccount2);


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

        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount2);
        tAccountAddress.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress);

        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("hahahahahahahahah");
        tAccountService.addAddress(tAccountAddress2);

        for (int i = 0; i < 3; i++) {
            TProduct tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);

            TAccountItem tAccountItem = new TAccountItem();
            tAccountItem.setId(snowflake.next());
            tAccountItem.setInUse(true);
            tAccountItem.settAccount(tAccount);
            tAccountItem.settProduct(tmp);
            tAccountItem.setCount(3);

            tAccountService.addAccountItem(tAccountItem);
        }

        List<TProduct> tProduct = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 3)).getContent();

        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("支付某特殊物体!");
//        tLoan.setBuyerAccount(tAccount2);
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            tLoanDetail.settLoan(tLoan);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tProduct.get(0));
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        System.out.println("1111111111111111111111111111111111");

        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = tLoanService.findByLId(id);
            tLoanDetail.setCommodityAmount(new BigDecimal(912));
            tLoanService.updateLoanDetail(tLoanDetail);
        }

        System.out.println("hahahahahahahaahahahahahaha");

        TLoan tLoan1 = tLoanService.findById(tLoan.getId());
        System.out.println(tLoan1.getId());

        List<TLoanDetail> loanDetails = tLoanService.findDetailByBuyer(buyer.getId(), new PageRequest(0, 10)).getContent();
        for (int i = 0; i < loanDetails.size(); i++) {
            System.out.println(loanDetails.get(i).getCommodityAmount());
        }

    }

    @Test
    public void findByLoanId1() throws Exception {

    }

    @Test
    public void findDetailByBuyer2() throws Exception {

    }

    @Test
    public void findByLoanId2() throws Exception {

    }

    @Test
    public void findDetailByBuyer3() throws Exception {

    }

    @Test
    public void findByLoanId3() throws Exception {
        //新建用户1
        TUser user = new TUser();
        user.setId(snowflake.next());
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        user.setName("财神1号");
        tUserService.save(user);

        // 新建账户1
        TAccount tAccount1 = new TAccount();
        tAccount1.setId(snowflake.next());
        tAccount1.setFreeze(false);
        tAccount1.setName("账户01");
        tAccount1.settUser(user);
        tAccountService.bindAccount(tAccount1);

        //新建用户2
        TUser user2 = new TUser();
        user2.setId(snowflake.next());
        user2.setType(AccountType.USER);
        user2.setUpdateTime(new Date());
        user2.setName("财神2号");
        tUserService.save(user2);


        //新建账户2
        TAccount tAccount2 = new TAccount();
        tAccount2.setId(snowflake.next());
        tAccount2.setFreeze(false);
        tAccount2.setName("账户02");
        tAccount2.settUser(user2);
        tAccountService.bindAccount(tAccount2);

        //新建公司1
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        company.setName("公司001");
        tUserService.save(company);

        //账户和公司绑定
        TAccount tAccount = new TAccount();
        tAccount.setId(snowflake.next());
        tAccount.setFreeze(false);
        tAccount.setName("账户03");
        tAccount.settUser(company);
        tAccountService.bindAccount(tAccount);

        //增加3个产品
        TProduct tmp = null;
        for (int i = 0; i < 3; i++) {
            tmp = new TProduct();
            tmp.setName("product" + i);
            tmp.setId(snowflake.next());
            tmp.setStatus(ProductStatus.ON);
            tmp.settUser(company);
            tProductService.save(tmp);

            TServer s = new TServer();
            s.setProduct(tmp);
            s.setEndTime(new Date());
            s.setBalance(new BigDecimal(1000));
            s.setName("server" + i);
            s.setBeginTime(new Date());
            s.setId(snowflake.next());
            s.setServerStatus(ServerStatus.ON);
            tServerSev.add(s);
        }

        //账户地址1 绑定账号1
        TAccountAddress tAccountAddress = new TAccountAddress();
        tAccountAddress.setId(snowflake.next());
        tAccountAddress.setDefault(true);
        tAccountAddress.setPhone("15171494727");
        tAccountAddress.setEmail("leoricxu@gmail.com");
        tAccountAddress.settAccount(tAccount1);
        tAccountAddress.setAddress("1号账户的收件地址");
        tAccountService.addAddress(tAccountAddress);

        //账户地址2 绑定账号2
        TAccountAddress tAccountAddress2 = new TAccountAddress();
        tAccountAddress2.setId(snowflake.next());
        tAccountAddress2.setDefault(false);
        tAccountAddress2.setPhone("15171494727");
        tAccountAddress2.setEmail("leoricxu@gmail.com");
        tAccountAddress2.settAccount(tAccount2);
        tAccountAddress2.setAddress("2号账户的收件地址");
        tAccountService.addAddress(tAccountAddress2);


        TLoan tLoan = new TLoan();
        tLoan.setId(snowflake.next());
        tLoan.setUpdatedTime(new Date());
        tLoan.setStatus(LoanStatus.SLEEP);
        tLoan.setPaidChannel(PaidChannel.ZFB);
        tLoan.setDescription("主订单1号!!!");
        tLoan.setBuyerAddress(tAccountAddress);
        tLoan.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan);

        TLoan tLoan2 = new TLoan();
        tLoan2.setId(snowflake.next());
        tLoan2.setUpdatedTime(new Date());
        tLoan2.setStatus(LoanStatus.SLEEP);
        tLoan2.setPaidChannel(PaidChannel.ZFB);
        tLoan2.setDescription("主订单2号!!!");
        tLoan2.setBuyerAddress(tAccountAddress);
        tLoan2.setCreatedTime(new Date());
        tLoanService.updateLoan(tLoan2);


        long id = snowflake.next();
        for (int i = 0; i < 10; i++) {
            TLoanDetail tLoanDetail = new TLoanDetail();
            if (i % 2 == 0)
                tLoanDetail.settLoan(tLoan);
            else
                tLoanDetail.settLoan(tLoan2);
            tLoanDetail.setId(id + i);
            tLoanDetail.settProduct(tmp);
            tLoanDetail.setCount(3);
            tLoanDetail.setSellerAccount(tAccount);
            tLoanDetail.setCommodityAmount(new BigDecimal(1000));
            tLoanService.newLoan(tLoanDetail);
        }

        List<TLoanDetail> tLoanDetails = tLoanService.findByLoanId(tLoan.getId(), null, null, new PageRequest(0, 10)).getContent();

        for (int i = 0; i < tLoanDetails.size(); i++) {
            System.out.println(tLoanDetails.get(i).gettLoan().getDescription());
        }
        System.out.println("hahahahahahahahh");
    }

    @Test
    public void deleteDetail() throws Exception {

    }

    @Test
    public void exists() throws Exception {

    }

    @Test
    public void existDetail() throws Exception {

    }

    @Test
    public void computeTotalAmount() throws Exception {

    }

    @Test
    public void findSellerId() throws Exception {

    }

}