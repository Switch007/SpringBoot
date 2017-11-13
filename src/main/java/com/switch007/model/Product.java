package com.switch007.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private String title;

    private String href;

    private String pic;

    private Integer type;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String price;

    private String trueUrl;

    private BigDecimal eee;

    private BigDecimal pricessss;

    private String taobaoUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTrueUrl() {
        return trueUrl;
    }

    public void setTrueUrl(String trueUrl) {
        this.trueUrl = trueUrl;
    }

    public BigDecimal getEee() {
        return eee;
    }

    public void setEee(BigDecimal eee) {
        this.eee = eee;
    }

    public BigDecimal getPricessss() {
        return pricessss;
    }

    public void setPricessss(BigDecimal pricessss) {
        this.pricessss = pricessss;
    }

    public String getTaobaoUrl() {
        return taobaoUrl;
    }

    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }
}