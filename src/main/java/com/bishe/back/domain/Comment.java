package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer id;
    private Integer useId;
    private Integer videoid;
    private Integer articleid;
    private Integer parentId;
    private Integer up;
    private Byte type;
    private Date comtTime;
    private String comtContent;

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

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getComtTime() {
        return comtTime;
    }

    public void setComtTime(Date comtTime) {
        this.comtTime = comtTime;
    }

    public String getComtContent() {
        return comtContent;
    }

    public void setComtContent(String comtContent) {
        this.comtContent = comtContent;
    }
}
