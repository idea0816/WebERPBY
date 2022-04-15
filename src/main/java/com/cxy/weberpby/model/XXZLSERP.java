package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 鞋厂型体明细資料
 *
 */

public class XXZLSERP {
    String XieXing; // 鞋型
    String SheHao;  // 色號
    String BWBH;    // 部位編號
    String CLBH;    // 材料編號
    String CSBH;    // 廠商編號
    String CCQQ;    // 尺寸起
    String CCQZ;    // 尺寸迄

    public String getXieXing() {
        return XieXing;
    }

    public void setXieXing(String xieXing) {
        XieXing = xieXing;
    }

    public String getSheHao() {
        return SheHao;
    }

    public void setSheHao(String sheHao) {
        SheHao = sheHao;
    }

    public String getBWBH() {
        return BWBH;
    }

    public void setBWBH(String BWBH) {
        this.BWBH = BWBH;
    }

    public String getCLBH() {
        return CLBH;
    }

    public void setCLBH(String CLBH) {
        this.CLBH = CLBH;
    }

    public String getCSBH() {
        return CSBH;
    }

    public void setCSBH(String CSBH) {
        this.CSBH = CSBH;
    }

    public String getCCQQ() {
        return CCQQ;
    }

    public void setCCQQ(String CCQQ) {
        this.CCQQ = CCQQ;
    }

    public String getCCQZ() {
        return CCQZ;
    }

    public void setCCQZ(String CCQZ) {
        this.CCQZ = CCQZ;
    }
}
