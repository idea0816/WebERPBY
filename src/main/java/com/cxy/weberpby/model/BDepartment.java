package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time: 2022/5/31
 * @Description 鞋廠部門資料
 */
public class BDepartment {
    private String ID;
    private String DepName;
    private String DepMemo;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDepName() {
        return DepName;
    }

    public void setDepName(String depName) {
        DepName = depName;
    }

    public String getDepMemo() {
        return DepMemo;
    }

    public void setDepMemo(String depMemo) {
        DepMemo = depMemo;
    }
}
