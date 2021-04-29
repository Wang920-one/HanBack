package com.bishe.back.domain;

import java.io.Serializable;

public class Manage implements Serializable {
    private Integer manageId;
    private String manageName;
    private String managePassword;
    private String manageEmail;
    private Byte manageSex;
    private String managePhone;

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getManagePassword() {
        return managePassword;
    }

    public void setManagePassword(String managePassword) {
        this.managePassword = managePassword;
    }

    public String getManageEmail() {
        return manageEmail;
    }

    public void setManageEmail(String manageEmail) {
        this.manageEmail = manageEmail;
    }

    public Byte getManageSex() {
        return manageSex;
    }

    public void setManageSex(Byte manageSex) {
        this.manageSex = manageSex;
    }

    public String getManagePhone() {
        return managePhone;
    }

    public void setManagePhone(String managePhone) {
        this.managePhone = managePhone;
    }
}
