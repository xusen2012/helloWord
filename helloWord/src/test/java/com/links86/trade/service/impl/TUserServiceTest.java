package com.links86.trade.service.impl;

import com.links86.trade.domain.TUser;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.utils.Snowflake;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leoxu on 2016/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TUserServiceTest {

    @Autowired
    Snowflake snowflake;

    @Autowired
    TUserService tUserService;

    @Test
    public void save() throws Exception {
        // user
        // company

        TUser user = new TUser();
        user.setId(snowflake.next());
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);

        TUser company = new TUser();
        company.setId(snowflake.next());
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        System.out.println("hahahahahahah");

    }

    @Test
    public void findById() throws Exception {

        long id = snowflake.next();
        TUser user = new TUser();
        user.setId(id);
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);

        long id2 = snowflake.next();
        TUser company = new TUser();
        company.setId(id2);
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        System.out.println("==================================================");
        System.out.println(tUserService.findById(id));
        System.out.println(tUserService.findById(id));
        System.out.println(tUserService.findByIdType(id,AccountType.COMPANY));
        System.out.println(tUserService.findByIdType(id,AccountType.USER));
        System.out.println("==================================================");
    }

    @Test
    public void delete() throws Exception {

        long id = snowflake.next();
        TUser user = new TUser();
        user.setId(id);
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);

        long id2 = snowflake.next();
        TUser company = new TUser();
        company.setId(id2);
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        tUserService.delete(user);
        tUserService.delete(company);

        System.out.println("wait wait wait wait wait wait wait !!!!!!!");

    }

    @Test
    public void findAll() throws Exception {
        long id = snowflake.next();
        TUser user = new TUser();
        user.setId(id);
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);

        long id2 = snowflake.next();
        TUser company = new TUser();
        company.setId(id2);
        company.setName("wangbadangongsi");
        company.setType(AccountType.COMPANY);
        company.setUpdateTime(new Date());
        tUserService.save(company);

        System.out.println(tUserService.findAll(new PageRequest(0,2)).getContent());
    }

    @Test
    public void exists() throws Exception {
        long id = snowflake.next();
        TUser user = new TUser();
        user.setId(id);
        user.setName("wangxiaoming1");
        user.setType(AccountType.USER);
        user.setUpdateTime(new Date());
        tUserService.save(user);

        System.out.println(tUserService.exists(id));
    }

    @Test
    public void testHttpClient(){
        HttpClient httpClient = new DefaultHttpClient();
        //URL 可以拼接,i建议做成配置项 用@value注入
        String url = "http://118.178.227.18/auth/users/6205237575632818176/roles/2?timeLimit=12";
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpResponse response =  httpClient.execute(httpPost);
            //得到httpResponse的实体数据
            HttpEntity httpEntity=response.getEntity();
            StringBuilder entityStringBuilder=new StringBuilder();
            if (httpEntity!=null) {
                try {
                    BufferedReader bufferedReader=new BufferedReader
                            (new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8*1024);
                    String line=null;
                    while ((line=bufferedReader.readLine())!=null) {
                        entityStringBuilder.append(line+"/n");
                    }
                    if(entityStringBuilder.toString().contains("error")){
                        System.out.println("失败");
                    } else {
                        System.out.println("成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String registerTime = sdf.format(new Date());
        System.out.println(registerTime);
    }
}