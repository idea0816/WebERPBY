package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体基本資料
 *
 */

public class XXZL {
    private String XieXing; // 鞋型
    private String SheHao;  // 色號
    private String CQDH;    // 厂别
    private String YSSM;    // 說明
    private String ARTICLE; // ARTICLE
    private String mjbh;    // 模具編號
    private String lbdh;    // 模具類別
    private String KHDH;    // 客戶代號
    private Double HZS; // 迴轉數
    private Double LOSS;    // LOSS%
    private String USERID;
    private String USERDATE;    // 使用者更新日期
    private Integer JBYX;   // 打包數量(?雙/1包)
    private Integer JBMS;
    private Integer CCBT;

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

    public String getYSSM() {
        return YSSM;
    }

    public void setYSSM(String YSSM) {
        this.YSSM = YSSM;
    }

    public String getARTICLE() {
        return ARTICLE;
    }

    public void setARTICLE(String ARTICLE) {
        this.ARTICLE = ARTICLE;
    }

    public String getMjbh() {
        return mjbh;
    }

    public void setMjbh(String mjbh) {
        this.mjbh = mjbh;
    }

    public String getLbdh() {
        return lbdh;
    }

    public void setLbdh(String lbdh) {
        this.lbdh = lbdh;
    }

    public String getKHDH() {
        return KHDH;
    }

    public void setKHDH(String KHDH) {
        this.KHDH = KHDH;
    }

    public Double getHZS() {
        return HZS;
    }

    public void setHZS(Double HZS) {
        this.HZS = HZS;
    }

    public Double getLOSS() {
        return LOSS;
    }

    public void setLOSS(Double LOSS) {
        this.LOSS = LOSS;
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

    public Integer getJBYX() {
        return JBYX;
    }

    public void setJBYX(Integer JBYX) {
        this.JBYX = JBYX;
    }

    public Integer getJBMS() {
        return JBMS;
    }

    public void setJBMS(Integer JBMS) {
        this.JBMS = JBMS;
    }

    public Integer getCCBT() {
        return CCBT;
    }

    public void setCCBT(Integer CCBT) {
        this.CCBT = CCBT;
    }
}
