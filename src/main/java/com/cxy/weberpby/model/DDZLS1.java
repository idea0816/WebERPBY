package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/24
 * @Description DDZLS1 - 訂單(配方資料)
 */
public class DDZLS1 {

    private String DDBH;    // 訂單編號
    private String CQDH;    // 廠區別
    private String xh;  // 序號
    private String cldh;    // 配方代號
    private String YSSM;    // 部位
    private Double KGS; // 配方用量
    private Double LOSS;    // LOSS(KGS)
    private String USERID;
    private String USERDATE;
    private String LB;  // 顏色(預設00)

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
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

    public Double getKGS() {
        return KGS;
    }

    public void setKGS(Double KGS) {
        this.KGS = KGS;
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

    public String getLB() {
        return LB;
    }

    public void setLB(String LB) {
        this.LB = LB;
    }
}
