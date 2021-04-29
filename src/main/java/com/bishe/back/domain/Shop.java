package com.bishe.back.domain;

import java.io.Serializable;
import java.util.Date;

public class Shop implements Serializable {
    private Integer shopId;
    private String shopName;
    private String shopHttp;
    private String pic;
    private Date shopYear;
    private Integer shopFans;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopHttp() {
        return shopHttp;
    }

    public void setShopHttp(String shopHttp) {
        this.shopHttp = shopHttp;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getShopYear() {
        return shopYear;
    }

    public void setShopYear(Date shopYear) {
        this.shopYear = shopYear;
    }

    public Integer getShopFans() {
        return shopFans;
    }

    public void setShopFans(Integer shopFans) {
        this.shopFans = shopFans;
    }
}
