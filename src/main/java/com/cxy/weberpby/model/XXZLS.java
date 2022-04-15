package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 底厂型体基本資料 - 部位 & 配方
 *
 */

public class XXZLS {
    private String XieXing; // 鞋型
    private String SheHao;  // 色號
    private String CQDH;
    private String xh;  // 序號A~O
    private String cldh;    // 配方代號
    private String YSSM;    // 部位說明
    private String USERID;
    private String USERDATE;
    private String LB;  // 类别
    private Double HZS; // 迴轉數
    private Double DZ;
    private Double RS;

    public String getXieXing() {
        return XieXing;
    }

    public void setXieXing(String xieXing) {
        XieXing = xieXing;
    }

    public String getSheHao() {
        return SheHao;
    }

    public void setSheHao(String sheHao) {
        SheHao = sheHao;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public String getYSSM() {
        return YSSM;
    }

    public void setYSSM(String YSSM) {
        this.YSSM = YSSM;
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

    public String getLB() {
        return LB;
    }

    public void setLB(String LB) {
        this.LB = LB;
    }

    public Double getHZS() {
        return HZS;
    }

    public void setHZS(Double HZS) {
        this.HZS = HZS;
    }

    public Double getDZ() {
        return DZ;
    }

    public void setDZ(Double DZ) {
        this.DZ = DZ;
    }

    public Double getRS() {
        return RS;
    }

    public void setRS(Double RS) {
        this.RS = RS;
    }
}
