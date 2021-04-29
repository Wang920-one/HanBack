package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

//关注
public class Subscribe implements Serializable {
    private Integer id;
    private Integer beSubscribe;
    private Integer subscribe;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeSubscribe() {
        return beSubscribe;
    }

    public void setBeSubscribe(Integer beSubscribe) {
        this.beSubscribe = beSubscribe;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
