package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

//收藏
public class Collect implements Serializable {
    private Integer id;
    private Integer useId;
    private Byte type;
    private Integer atcId;
    private Integer videoId;
    private Date cltTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getAtcId() {
        return atcId;
    }

    public void setAtcId(Integer atcId) {
        this.atcId = atcId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Date getCltTime() {
        return cltTime;
    }

    public void setCltTime(Date cltTime) {
        this.cltTime = cltTime;
    }
}
