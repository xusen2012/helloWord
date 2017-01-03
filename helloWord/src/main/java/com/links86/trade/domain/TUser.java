package com.links86.trade.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.links86.trade.domain.em.AccountType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 因为我也不知道用户模块的结构所以暂时放空
 * Project name is links86-system
 * Created by xuli on 2016-09-12 11:30
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Entity
@Table(name = "t_user")
public class TUser implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    private String name;

    private Date updateTime;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @OneToOne(mappedBy = "tUser", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    private TAccount tAccount;

    @Column(unique = true)
    private long outUid;

    public long getOutUid() {
        return outUid;
    }

    public void setOutUid(long outUid) {
        this.outUid = outUid;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public TAccount gettAccount() {
        return tAccount;
    }

    public void settAccount(TAccount tAccount) {
        this.tAccount = tAccount;
    }
}
