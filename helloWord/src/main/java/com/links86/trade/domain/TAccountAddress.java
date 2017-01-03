package com.links86.trade.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project name is links86-system
 * Created by xuli on 2016-10-10 11:45
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Entity
@Table(name = "t_account_address")
public class TAccountAddress implements Serializable {

    @JsonSerialize(using=ToStringSerializer.class)
    @Id
    private long id;

    @Column(columnDefinition = "char(1)")
    private boolean isDefault;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    /*@JsonIgnore*/
    private TAccount tAccount;

    private String address;

    private String recipientsName;

    private String phone;

    private String email;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TAccount gettAccount() {
        return tAccount;
    }

    public void settAccount(TAccount tAccount) {
        this.tAccount = tAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }
}
