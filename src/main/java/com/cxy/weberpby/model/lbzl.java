package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/4/27
 * @Description 代号类别资料
 */
public class lbzl {
    String lb;  // 類別
    String zwsm;    // 中文說明
    String ywsm;    // 英文說明
    String bz;  // 備註
    String USERID;
    String USERDATE;

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getZwsm() {
        return zwsm;
    }

    public void setZwsm(String zwsm) {
        this.zwsm = zwsm;
    }

    public String getYwsm() {
        return ywsm;
    }

    public void setYwsm(String ywsm) {
        this.ywsm = ywsm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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
