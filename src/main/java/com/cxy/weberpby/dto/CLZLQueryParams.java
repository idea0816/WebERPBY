package com.cxy.weberpby.dto;

/**
 * @author CXY
 * @version Create Time: 2022/4/27
 * @Description 配方類查詢條件設定(CLZL, clzlsl, clzlsz)
 */
public class CLZLQueryParams {
    private String cldh; // 配方代號
    private String lb;  // 類別
    private String cldhz;   // 材料代號

    public String getCldh() {
        return cldh;
    }

    public void setCldh(String cldh) {
        this.cldh = cldh;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getCldhz() {
        return cldhz;
    }

    public void setCldhz(String cldhz) {
        this.cldhz = cldhz;
    }
}
