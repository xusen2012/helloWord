package com.links86.trade.domain.out;

import java.util.Date;

/**
 * 重新定义输出类
 * Created by leoxu on 2016/11/25.
 */
public class RespUser {
    private String id;
    private String name;
    private String aid;
    private String aname;
    private String type;
    private Date updateTime;

    public RespUser(String id, String name, String aid, String aname, String type, Date updateTime) {
        this.id = id;
        this.name = name;
        this.aid = aid;
        this.aname = aname;
        this.type = type;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
