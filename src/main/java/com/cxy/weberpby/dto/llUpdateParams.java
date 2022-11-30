package com.cxy.weberpby.dto;

import com.cxy.weberpby.model.PGZLS1;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/31
 * @Description 領料作業前端傳來的資料
 */
public class llUpdateParams {
    private List<PGZLS1> updatepgzls1;

    public List<PGZLS1> getUpdatepgzls1() {
        return updatepgzls1;
    }

    public void setUpdatepgzls1(List<PGZLS1> updatepgzls1) {
        this.updatepgzls1 = updatepgzls1;
    }
}
