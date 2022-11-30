package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/6/7
 * @Description LLZL - 粗胚申領作業(A3D)
 */
public class LLZL {
    private String LLBH;    // 單據編號 E220600001
    private String CQDH;
    private String LLRQ;    // 領料日期 20100330
    private String CQDH1;   // 倉庫
    private String CFM;
    private String USERID;
    private String USERDATE;

    public String getLLBH() {
        return LLBH;
    }

    public void setLLBH(String LLBH) {
        this.LLBH = LLBH;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getLLRQ() {
        return LLRQ;
    }

    public void setLLRQ(String LLRQ) {
        this.LLRQ = LLRQ;
    }

    public String getCQDH1() {
        return CQDH1;
    }

    public void setCQDH1(String CQDH1) {
        this.CQDH1 = CQDH1;
    }

    public String getCFM() {
        return CFM;
    }

    public void setCFM(String CFM) {
        this.CFM = CFM;
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
