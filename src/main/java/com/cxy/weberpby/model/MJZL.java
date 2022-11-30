package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具資料(含MJZLS資料)
 */
public class MJZL {
    private String mjbh;    // 模具編號(MJZLS & MJZLS)
    private String lbdh;    // 類別代號(MJZLS & MJZLS)
    private String size;    // 尺寸(MJZLS)
    private Integer mjsl;   // 模具數量(MJZLS)
    private String USERID;  // (MJZLS & MJZLS)
    private String USERDATE;    // (MJZLS & MJZLS)

    public String getMjbh() {
        return mjbh;
    }

    public void setMjbh(String mjbh) {
        this.mjbh = mjbh;
    }

    public String getLbdh() {
        return lbdh;
    }

    public void setLbdh(String lbdh) {
        this.lbdh = lbdh;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getMjsl() {
        return mjsl;
    }

    public void setMjsl(Integer mjsl) {
        this.mjsl = mjsl;
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
}
