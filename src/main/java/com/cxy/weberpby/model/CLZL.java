package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料格式
 *
 */

public class CLZL {

    private String cldh;    // 材料代号
    private String cllb;    // 材料类别
    private String zwpm;    // 中文品名
    private String ywpm;    // Description
    private String dwbh;    // 单位
    private String zsdh;    // 厂商代号
    private Double cldj;    // 单价
    private Double clsl;    // 库存数量
//    Double sf_qty;  // 安全库存量
//    String cgdwbh;  // 采购单位
//    Double cgdwbl;  // 单位比例
    private Double TotKgs;  // 一手重量
//    String USERID;  // USERID
//    String USERDATE;    // USERDATE
//    String cgcqdh;  // 采购区域
//    Double mrxq;    // 每日需求
//    String CGLB;    // 采购类别


    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public String getCllb() {
        return cllb;
    }

    public void setCllb(String cllb) {
        this.cllb = cllb;
    }

    public String getZwpm() {
        return zwpm;
    }

    public void setZwpm(String zwpm) {
        this.zwpm = zwpm;
    }

    public String getYwpm() {
        return ywpm;
    }

    public void setYwpm(String ywpm) {
        this.ywpm = ywpm;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getZsdh() {
        return zsdh;
    }

    public void setZsdh(String zsdh) {
        this.zsdh = zsdh;
    }

    public Double getCldj() {
        return cldj;
    }

    public void setCldj(Double cldj) {
        this.cldj = cldj;
    }

    public Double getClsl() {
        return clsl;
    }

    public void setClsl(Double clsl) {
        this.clsl = clsl;
    }

    public Double getTotKgs() {
        return TotKgs;
    }

    public void setTotKgs(Double totKgs) {
        TotKgs = totKgs;
    }
}
