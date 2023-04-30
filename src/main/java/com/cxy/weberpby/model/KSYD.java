package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/11/30
 * @Description 入出庫作業表頭
 */
public class KSYD {
    String DGLB;    // 單據類別	I:入庫  O:出庫 P:盤點 J:模具進 K:模具出 L:模具送修 M:模具返修
    String CQDH;
    String KSDH;    // 異動單號	來源類別(1)+年(2)+月(2)+流水碼(5) = 10 ex:B120500053
    String KSRQ;    // 異動日期
    String LYLB;    // 來源類別	A:入庫 B:出庫 C:盤點 E:A2K CFM F:A1W CFM J:my java
    String LYDH;    // 來源單號	模具維修商對照zszl.zsdh
    String BZ;  // 備註
    String USERID;
    String USERDATE;
    String ZSDH;    // 模具SIZE

    public String getDGLB() {
        return DGLB;
    }

    public void setDGLB(String DGLB) {
        this.DGLB = DGLB;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getKSDH() {
        return KSDH;
    }

    public void setKSDH(String KSDH) {
        this.KSDH = KSDH;
    }

    public String getKSRQ() {
        return KSRQ;
    }

    public void setKSRQ(String KSRQ) {
        this.KSRQ = KSRQ;
    }

    public String getLYLB() {
        return LYLB;
    }

    public void setLYLB(String LYLB) {
        this.LYLB = LYLB;
    }

    public String getLYDH() {
        return LYDH;
    }

    public void setLYDH(String LYDH) {
        this.LYDH = LYDH;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
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

    public String getZSDH() {
        return ZSDH;
    }

    public void setZSDH(String ZSDH) {
        this.ZSDH = ZSDH;
    }
}
