package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description PGZLS1 - 原料用量(右窗格)
 */

public class PGZLS1 {
    private String PGDATE;   // 派工編號
    private String CQDH;    // 廠區
    private String cldh;    // 材料代號
    private Double clyl;    // 用量
    private Double clll;    // 超領數量
    private String USERID;
    private String USERDATE;

    public String getPGDATE() {
        return PGDATE;
    }

    public void setPGDATE(String PGDATE) {
        this.PGDATE = PGDATE;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public Double getClyl() {
        return clyl;
    }

    public void setClyl(Double clyl) {
        this.clyl = clyl;
    }

    public Double getClll() {
        return clll;
    }

    public void setClll(Double clll) {
        this.clll = clll;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERDATE() {
        return USERDATE;
    }

    public void setUSERDATE(String USERDATE) {
        this.USERDATE = USERDATE;
    }
}
