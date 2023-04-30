package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/11/30
 * @Description 入出庫作業表身
 */
public class KSYDS {
    String DGLB;    // 單據類別	I:入庫  O:出庫 P:盤點 J:模具進 K:模具出 L:模具送修 M:模具返修
    String KSDH;    // 異動單號
    String CQDH;
    String SH;  // 序號(001)	模具碼(EX:A.B.C模)
    String CLDH;    // 材料代號	模具代號
    String MSBZ;    // 明細備註
    Double SL;  // 入庫數量
    Double DJ;
    Double GR;
    Double SL1; // 出庫數量
    Double GR1;
    String NY;  // 年月	1002
    String USERID;
    String USERDATE;

    public String getDGLB() {
        return DGLB;
    }

    public void setDGLB(String DGLB) {
        this.DGLB = DGLB;
    }

    public String getKSDH() {
        return KSDH;
    }

    public void setKSDH(String KSDH) {
        this.KSDH = KSDH;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getSH() {
        return SH;
    }

    public void setSH(String SH) {
        this.SH = SH;
    }

    public String getCLDH() {
        return CLDH;
    }

    public void setCLDH(String CLDH) {
        this.CLDH = CLDH;
    }

    public String getMSBZ() {
        return MSBZ;
    }

    public void setMSBZ(String MSBZ) {
        this.MSBZ = MSBZ;
    }

    public Double getSL() {
        return SL;
    }

    public void setSL(Double SL) {
        this.SL = SL;
    }

    public Double getDJ() {
        return DJ;
    }

    public void setDJ(Double DJ) {
        this.DJ = DJ;
    }

    public Double getGR() {
        return GR;
    }

    public void setGR(Double GR) {
        this.GR = GR;
    }

    public Double getSL1() {
        return SL1;
    }

    public void setSL1(Double SL1) {
        this.SL1 = SL1;
    }

    public Double getGR1() {
        return GR1;
    }

    public void setGR1(Double GR1) {
        this.GR1 = GR1;
    }

    public String getNY() {
        return NY;
    }

    public void setNY(String NY) {
        this.NY = NY;
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
