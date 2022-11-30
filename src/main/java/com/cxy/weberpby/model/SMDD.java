package com.cxy.weberpby.model;

import java.util.Date;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description 鞋廠生管訂單資料(部份)
 */
public class SMDD {
    // DDZL.DDBH, SMDD.XH,SMDD.PlanDate, SMDD.Qty, SMDD.DepNO
    private String DDBH;    // 訂單編號
    private String XH;  // 輪次
    private String PlanDate;    // 成型上線日--成型投入(B)
    private Integer Qty;    // 雙數
    private String DepNO;   // 成型單位

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getXH() {
        return XH;
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public String getPlanDate() {
        return PlanDate;
    }

    public void setPlanDate(String planDate) {
        PlanDate = planDate;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public String getDepNO() {
        return DepNO;
    }

    public void setDepNO(String depNO) {
        DepNO = depNO;
    }
}
