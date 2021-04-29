package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

public class Active implements Serializable {
    private Integer acId;
    private String acName;
    private String acAddress;
    private Date acTime;
    private String acForm;
    private String acSponsor;
    private String acDetails;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcAddress() {
        return acAddress;
    }

    public void setAcAddress(String acAddress) {
        this.acAddress = acAddress;
    }

    public Date getAcTime() {
        return acTime;
    }

    public void setAcTime(Date acTime) {
        this.acTime = acTime;
    }

    public String getAcForm() {
        return acForm;
    }

    public void setAcForm(String acForm) {
        this.acForm = acForm;
    }

    public String getAcSponsor() {
        return acSponsor;
    }

    public void setAcSponsor(String acSponsor) {
        this.acSponsor = acSponsor;
    }

    public String getAcDetails() {
        return acDetails;
    }

    public void setAcDetails(String acDetails) {
        this.acDetails = acDetails;
    }
}
