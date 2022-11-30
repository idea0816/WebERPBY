package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/6/7
 * @Description LLZLS - 粗胚申領(領料明細)
 */
public class LLZLS {
    private String LLBH;    // 單據編號
    private String CQDH;
    private String DDBH;    // 訂單編號(這裡寫入PGDATE、大車領料為了多單合併領料、要回寫鞋廠出貨單時、再去PGZLS找訂單編號
    private String cldh;    // 胚料代號
    private Double KGS_LL;  // 胚料重量	YL+CL
    private Double KGS_YL;  // 已領重量
    private Double KGS_CL;  // 超領重量
    private String CLSM;    // 超領原因
    private String USERID;
    private String USERDATE;
    private String LB;  // WML(萬馬力)、DC(大車)、YY(油壓)
    private String lbdh;

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

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public Double getKGS_LL() {
        return KGS_LL;
    }

    public void setKGS_LL(Double KGS_LL) {
        this.KGS_LL = KGS_LL;
    }

    public Double getKGS_YL() {
        return KGS_YL;
    }

    public void setKGS_YL(Double KGS_YL) {
        this.KGS_YL = KGS_YL;
    }

    public Double getKGS_CL() {
        return KGS_CL;
    }

    public void setKGS_CL(Double KGS_CL) {
        this.KGS_CL = KGS_CL;
    }

    public String getCLSM() {
        return CLSM;
    }

    public void setCLSM(String CLSM) {
        this.CLSM = CLSM;
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

    public String getLbdh() {
        return lbdh;
    }

    public void setLbdh(String lbdh) {
        this.lbdh = lbdh;
    }
}
