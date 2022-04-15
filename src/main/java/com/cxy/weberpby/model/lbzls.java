package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 代号类别明细资料 - 上一层是 lbzl
 */

public class lbzls {
    String lb;  // 類別(这里有埋资料处理要小心)
    String lbdh;    // 代號
    String zwsm;    // 中文說明
    String ywsm;    // 英文說明
    String bz;  // 備註
    String bz1; // 備註1
    String USERID;
    String USERDATE;

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getLbdh() {
        return lbdh;
    }

    public void setLbdh(String lbdh) {
        this.lbdh = lbdh;
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

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
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
