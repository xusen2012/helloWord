package com.links86.trade.service.impl;

import com.links86.trade.domain.TProduct;
import com.links86.trade.domain.TServer;
import com.links86.trade.domain.TUser;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.domain.em.ProductStatus;
import com.links86.trade.domain.em.ServerStatus;
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

import static org.junit.Assert.*;

/**
 * Created by leoxu on 2016/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TProductServiceTest {

    @Autowired
    Snowflake snowflake;

    @Autowired
    TProductService tProductService;

    @Autowired
    TUserService tUserService;

    @Autowired
    TServerSev tServerSev;

    @Test
    public void save() throws Exception {
        //新建公司
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        //新建产品
        TProduct tProduct = new TProduct();
        tProduct.settUser(company);
        tProduct.setId(snowflake.next());
        tProduct.setName("人民币");
        tProduct.setStatus(ProductStatus.ON);
        tProductService.save(tProduct);


//        for (int i = 0; i < 2; i++) {
//            TServer server = new TServer();
//            server.setId(snowflake.next());
//            server.setServerStatus(ServerStatus.ON);
//            server.setBeginTime(new Date());
//            server.setName("server" + i);
//            server.setBalance(new BigDecimal(1000));
//            server.setEndTime(new Date());
//            server.setProduct(tProduct);
//            tServerSev.add(server);
//        }

        System.out.println("1111111111111111111111");
    }

    @Test
    public void upAndDown() throws Exception {

        //新建公司
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        //新建产品
        TProduct tProduct = new TProduct();
        tProduct.settUser(company);
        tProduct.setId(snowflake.next());
        tProduct.setName("人民币");
        tProduct.setStatus(ProductStatus.ON);
        tProductService.save(tProduct);

        tProductService.upAndDown(tProduct.getId());
        System.out.println("11111111111111111111");
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findByName() throws Exception {
        //新建公司
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        for (int i = 0; i < 10; i++) {
            TProduct tProduct = new TProduct();
            tProduct.settUser(company);
            tProduct.setId(snowflake.next());
            if (i % 2 == 0)
                tProduct.setName("人民币");
            else
                tProduct.setName("美元");
            tProduct.setStatus(ProductStatus.ON);
            tProductService.save(tProduct);
        }
        List<TProduct> productList = tProductService.findByName("美", new PageRequest(0, 5)).getContent();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getName());
        }
    }

    @Test
    public void findByCompanyId() throws Exception {
        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        for (int i = 0; i < 10; i++) {
            TProduct tProduct = new TProduct();
            tProduct.settUser(company);
            tProduct.setId(snowflake.next());
            tProduct.setName("美元");
            tProduct.setStatus(ProductStatus.ON);
            tProductService.save(tProduct);
        }
        List<TProduct> productList = tProductService.findByCompanyId(company.getId(), new PageRequest(0, 10)).getContent();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).gettUser().getId());
        }
    }

    @Test
    public void exists() throws Exception {

    }

    @Test
    public void delete1() throws Exception {

    }

}