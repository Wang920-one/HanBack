package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

public class Suggest implements Serializable {
    private Integer sugId;
    private Integer useId;
    private String sugTitle;
    private String sugDetail;
    private Date sugTime;

    public Integer getSugId() {
        return sugId;
    }

    public void setSugId(Integer sugId) {
        this.sugId = sugId;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public String getSugTitle() {
        return sugTitle;
    }

    public void setSugTitle(String sugTitle) {
        this.sugTitle = sugTitle;
    }

    public String getSugDetail() {
        return sugDetail;
    }

    public void setSugDetail(String sugDetail) {
        this.sugDetail = sugDetail;
    }

    public Date getSugTime() {
        return sugTime;
    }

    public void setSugTime(Date sugTime) {
        this.sugTime = sugTime;
    }
}
