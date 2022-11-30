package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/11
 * @Description 部位规格建立2_配方P,Size,Gram
 */
public class bwBuild2 {
    private String NO;  // 項次
    private String YSSM;    // 部位說明
    private String cldh;    // 配方代號
    private String CC;  // 尺寸
    private Double G01; // 部位重量
    private Double G02; // 長度

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getYSSM() {
        return YSSM;
    }

    public void setYSSM(String YSSM) {
        this.YSSM = YSSM;
    }

    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public Double getG01() {
        return G01;
    }

    public void setG01(Double g01) {
        G01 = g01;
    }

    public Double getG02() {
        return G02;
    }

    public void setG02(Double g02) {
        G02 = g02;
    }
}
