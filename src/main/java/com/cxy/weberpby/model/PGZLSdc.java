package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/28
 * @Description PGZLSdc - 大車派工輪次 & Size & 雙數
 */
public class PGZLSdc {
    private String PGDATE;  // 派工單號
    private String CQDH;
    private String cldh;    // 訂單號碼
    private String lc;  // 輪次
    private String xc;  // 尺寸
    private String qty; // 雙數
    private String bb;  // 班別
    private String zb;  // 鞋廠組別
    private String time;    // 時間段

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

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getXc() {
        return xc;
    }

    public void setXc(String xc) {
        this.xc = xc;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getZb() {
        return zb;
    }

    public void setZb(String zb) {
        this.zb = zb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
