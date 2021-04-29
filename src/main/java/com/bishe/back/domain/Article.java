package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Integer id;
    private Integer useId;
    private String bioati;
    private String pic;
    private String atcDescribe;
    private String atcType;
    private String atcLabel;
    private Integer atcBrowse;
    private Integer atcThumes;
    private Integer atcSc;
    private Date atcTime;
    private Integer actShare;
    private String actText;
    private Byte actReview;

    public Integer getAtcSc() {
        return atcSc;
    }

    public void setAtcSc(Integer atcSc) {
        this.atcSc = atcSc;
    }

    public String getAtcType() {
        return atcType;
    }

    public void setAtcType(String atcType) {
        this.atcType = atcType;
    }

    public Byte getActReview() {
        return actReview;
    }

    public void setActReview(Byte actReview) {
        this.actReview = actReview;
    }

    public String getBioati() {
        return bioati;
    }

    public void setBioati(String bioati) {
        this.bioati = bioati;
    }

    public String getActText() {
        return actText;
    }

    public void setActText(String actText) {
        this.actText = actText;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAtcDescribe() {
        return atcDescribe;
    }

    public void setAtcDescribe(String atcDescribe) {
        this.atcDescribe = atcDescribe;
    }


    public String getAtcLabel() {
        return atcLabel;
    }

    public void setAtcLabel(String atcLabel) {
        this.atcLabel = atcLabel;
    }

    public Integer getAtcBrowse() {
        return atcBrowse;
    }

    public void setAtcBrowse(Integer atcBrowse) {
        this.atcBrowse = atcBrowse;
    }

    public Integer getAtcThumes() {
        return atcThumes;
    }

    public void setAtcThumes(Integer atcThumes) {
        this.atcThumes = atcThumes;
    }

    public Date getAtcTime() {
        return atcTime;
    }

    public void setAtcTime(Date atcTime) {
        this.atcTime = atcTime;
    }

    public Integer getActShare() {
        return actShare;
    }

    public void setActShare(Integer actShare) {
        this.actShare = actShare;
    }
}
