package com.cxy.weberpby.dto;

import com.cxy.weberpby.model.lbzl;
import com.cxy.weberpby.model.lbzls;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/28
 * @Description 接收類別頁面前端傳來3組的JSON
 */
public class lbzlUpdateParams {
    private List<lbzl> oldlbzl;
    private List<lbzl> newlbzl;
    private List<lbzls> newlbzls;

    public List<lbzl> getOldlbzl() {
        return oldlbzl;
    }

    public void setOldlbzl(List<lbzl> oldlbzl) {
        this.oldlbzl = oldlbzl;
    }

    public List<lbzl> getNewlbzl() {
        return newlbzl;
    }

    public void setNewlbzl(List<lbzl> newlbzl) {
        this.newlbzl = newlbzl;
    }

    public List<lbzls> getNewlbzls() {
        return newlbzls;
    }

    public void setNewlbzls(List<lbzls> newlbzls) {
        this.newlbzls = newlbzls;
    }
}
