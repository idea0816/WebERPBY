package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description PGZLS - 配方+派工手數(左窗格)
 */
public class PGZLS {

    private String PGDATE;  // 派工編號
    private String CQDH;    // 廠區
    private String cldh;    // 粗胚配方代號
    private Double KGS; // 派工重量
    private Double PGSS;    // 派工手數
    private Double KGS_RKS;
    private String GSSM;    // 替代為班別
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

    public Double getKGS() {
        return KGS;
    }

    public void setKGS(Double KGS) {
        this.KGS = KGS;
    }

    public Double getPGSS() {
        return PGSS;
    }

    public void setPGSS(Double PGSS) {
        this.PGSS = PGSS;
    }

    public Double getKGS_RKS() {
        return KGS_RKS;
    }

    public void setKGS_RKS(Double KGS_RKS) {
        this.KGS_RKS = KGS_RKS;
    }

    public String getGSSM() {
        return GSSM;
    }

    public void setGSSM(String GSSM) {
        this.GSSM = GSSM;
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
