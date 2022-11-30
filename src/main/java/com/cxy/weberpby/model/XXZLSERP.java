package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 鞋厂型体明细資料
 */

public class XXZLSERP {
    String XieXing; // 鞋型
    String SheHao;  // 色號
    String YSSM;    // 顏色說明
    String XTMH;    // 楦頭模號
    String DDMH;    // 大底模號
    String DAOMH;   // 刀模製具
    String BWBH;    // 部位編號
    String bwZwsm;  // 部位中文名稱
    String CLBH;    // 材料編號
    String clZwpm;  // 材料中文名稱
    // String CSBH;    // 廠商編號
    String CCQQ;    // 尺寸起
    String CCQZ;    // 尺寸迄

//    select xxzl.XieXing, xxzl.SheHao, xxzl.YSSM, xxzl.XTMH, xxzl.DDMH, xxzl.DAOMH,
//    xxzls.BWBH, bwzl.zwsm, xxzls.CLBH, clzl.zwpm, xxzls.CCQQ, xxzls.CCQZ
//    from xxzl xxzl
//    left join xxzls xxzls on xxzls.XieXing+xxzls.SheHao = xxzl.XieXing+xxzl.SheHao
//    left join bwzl bwzl on xxzls.BWBH = bwzl.bwdh
//    left join clzl clzl on xxzls.CLBH = clzl.cldh
//    where xxzl.ARTICLE = '162050C'
//    and xxzls.CLBH like 'J%'

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

    public String getYSSM() {
        return YSSM;
    }

    public void setYSSM(String YSSM) {
        this.YSSM = YSSM;
    }

    public String getXTMH() {
        return XTMH;
    }

    public void setXTMH(String XTMH) {
        this.XTMH = XTMH;
    }

    public String getDDMH() {
        return DDMH;
    }

    public void setDDMH(String DDMH) {
        this.DDMH = DDMH;
    }

    public String getDAOMH() {
        return DAOMH;
    }

    public void setDAOMH(String DAOMH) {
        this.DAOMH = DAOMH;
    }

    public String getBwZwsm() {
        return bwZwsm;
    }

    public void setBwZwsm(String bwZwsm) {
        this.bwZwsm = bwZwsm;
    }

    public String getClZwpm() {
        return clZwpm;
    }

    public void setClZwpm(String clZwpm) {
        this.clZwpm = clZwpm;
    }
}
