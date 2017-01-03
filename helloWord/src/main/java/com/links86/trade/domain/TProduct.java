package com.links86.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.links86.trade.domain.em.ProductStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 17:02
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 * 商品映射表
 */
@Entity
@Table(name = "t_product")
public class TProduct {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(fetch = FetchType.LAZY)
    private List<TServer> servers;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private TUser tUser;

    public TProduct(){}

    public TProduct(long id,String name,ProductStatus status,long server_id,long user_id){
        this.id = id;
        this.name = name;
        this.status = status;
        TServer tServer = new TServer();
        tServer.setId(server_id);
        this.servers.add(tServer);
        TUser tUser = new TUser();
        tUser.setId(user_id);
        this.tUser = tUser;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }

    public List<TServer> getServers() {
        return servers;
    }

    public void setServers(List<TServer> servers) {
        this.servers = servers;
    }
}
