package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成 & 基礎配方組成(clzlsl & clzlsz 共用)
 */
public class clzlsl {

    String cldh;    // 配方代號
    String lb;  // 類別
    String cldhz;   // 材料代號
    Double clyl;    // 配方用量kg
    Double phr; // 配方量%
    Double cldj;    // 單價
    String USERID;
    String USERDATE;

    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getCldhz() {
        return cldhz;
    }

    public void setCldhz(String cldhz) {
        this.cldhz = cldhz;
    }

    public Double getClyl() {
        return clyl;
    }

    public void setClyl(Double clyl) {
        this.clyl = clyl;
    }

    public Double getPhr() {
        return phr;
    }

    public void setPhr(Double phr) {
        this.phr = phr;
    }

    public Double getCldj() {
        return cldj;
    }

    public void setCldj(Double cldj) {
        this.cldj = cldj;
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
