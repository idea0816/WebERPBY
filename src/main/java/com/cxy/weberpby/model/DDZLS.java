package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/19
 * @Description 訂單明細資料(尺寸、數量)
 */
public class DDZLS {

    private String DDBH;    // 訂單編號
    private String CQDH;    // 廠區別
    private String CC;   // 尺寸
    private Integer Qty;    // 訂單數量
    private Double Price;   // 單價
    private Double Iprice;
    private Integer QtySC;  // 已生產數量
    private Integer QtyCH;  // 已出貨數量
    private String USERID;
    private String USERDATE;
    private Integer QtyLOSS;    // 可LOSS雙數
    private String xh;

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

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getIprice() {
        return Iprice;
    }

    public void setIprice(Double iprice) {
        Iprice = iprice;
    }

    public Integer getQtySC() {
        return QtySC;
    }

    public void setQtySC(Integer qtySC) {
        QtySC = qtySC;
    }

    public Integer getQtyCH() {
        return QtyCH;
    }

    public void setQtyCH(Integer qtyCH) {
        QtyCH = qtyCH;
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

    public Integer getQtyLOSS() {
        return QtyLOSS;
    }

    public void setQtyLOSS(Integer qtyLOSS) {
        QtyLOSS = qtyLOSS;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }
}
