package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {
    private Integer id;
    private Integer useId;
    private String videoTitle;
    private String pic;
    private String videoDes;
    private String videoType;
    private String videoLabel;
    private Integer videoBrowse;
    private Integer videoThumes;
    private Date videoTime;
    private Integer videoShare;
    private String videoFile;
    private Byte videoReview;
    private Integer videoSc;

    public Integer getVideoSc() {
        return videoSc;
    }

    public void setVideoSc(Integer videoSc) {
        this.videoSc = videoSc;
    }

    public Byte getVideoReview() {
        return videoReview;
    }

    public void setVideoReview(Byte videoReview) {
        this.videoReview = videoReview;
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

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVideoDes() {
        return videoDes;
    }

    public void setVideoDes(String videoDes) {
        this.videoDes = videoDes;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoLabel() {
        return videoLabel;
    }

    public void setVideoLabel(String videoLabel) {
        this.videoLabel = videoLabel;
    }

    public Integer getVideoBrowse() {
        return videoBrowse;
    }

    public void setVideoBrowse(Integer videoBrowse) {
        this.videoBrowse = videoBrowse;
    }

    public Integer getVideoThumes() {
        return videoThumes;
    }

    public void setVideoThumes(Integer videoThumes) {
        this.videoThumes = videoThumes;
    }

    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
    }

    public Integer getVideoShare() {
        return videoShare;
    }

    public void setVideoShare(Integer videoShare) {
        this.videoShare = videoShare;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }
}
