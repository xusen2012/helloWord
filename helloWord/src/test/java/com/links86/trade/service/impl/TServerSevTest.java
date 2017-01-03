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
public class TServerSevTest {

    @Autowired
    Snowflake snowflake;

    @Autowired
    TUserService tUserService;

    @Autowired
    TProductService tProductService;

    @Autowired
    TServerSev tServerSev;

    @Test
    public void findByProductId() throws Exception {
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

        for (int i = 0; i < 10; i++) {
            TServer server = new TServer();
            server.setId(snowflake.next());
            server.setServerStatus(ServerStatus.ON);
            server.setBeginTime(new Date());
            server.setName("server" + i);
            server.setBalance(new BigDecimal(1000));
            server.setEndTime(new Date());
            server.setProduct(tProduct);
            tServerSev.add(server);
        }

        List<TServer> serverList = tServerSev.findByProductId(tProduct.getId(),
                ServerStatus.ON, new PageRequest(0, 10)).getContent();
        for(int i=0;i<serverList.size();i++){
            System.out.println(serverList.get(i).getProduct().getId());
        }

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

        //新建产品
        TProduct tProduct = new TProduct();
        tProduct.settUser(company);
        tProduct.setId(snowflake.next());
        tProduct.setName("人民币");
        tProduct.setStatus(ProductStatus.ON);
        tProductService.save(tProduct);

        for (int i = 0; i < 10; i++) {
            TServer server = new TServer();
            server.setId(snowflake.next());
            server.setServerStatus(ServerStatus.ON);
            server.setBeginTime(new Date());
            server.setName("server" + i);
            server.setBalance(new BigDecimal(1000));
            server.setEndTime(new Date());
            server.setProduct(tProduct);
            tServerSev.add(server);
        }

        List<TServer> serverList = tServerSev.findByName("server",new PageRequest(0,10)).getContent();
        for(int i=0;i<serverList.size();i++){
            System.out.println(serverList.get(i).getProduct().getId());
        }
    }

}