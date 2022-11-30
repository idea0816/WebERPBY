package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description 鞋廠生管訂單掃描(輪次表)
 */
public class SMDDS {

    private String DDBH;    // 訂單編號
    private String XXCC;    // 鞋型尺寸
    private Integer Qty;    // 雙數
    private String USERDate;
    private String USERID;
    private String YN;

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getXXCC() {
        return XXCC;
    }

    public void setXXCC(String XXCC) {
        this.XXCC = XXCC;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public String getUSERDate() {
        return USERDate;
    }

    public void setUSERDate(String USERDate) {
        this.USERDate = USERDate;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getYN() {
        return YN;
    }

    public void setYN(String YN) {
        this.YN = YN;
    }
}
