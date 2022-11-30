package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料表頭
 */
public class PGZL {
    private String PGDATE;  // 派工編號
    private String CQDH;    // 廠區
    private String DATE1;   // 派工日期
    private String BZ;  // 備註(替代成單位：ex~WML(萬馬力)
    private String CFM; // CFM
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

    public String getDATE1() {
        return DATE1;
    }

    public void setDATE1(String DATE1) {
        this.DATE1 = DATE1;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
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
