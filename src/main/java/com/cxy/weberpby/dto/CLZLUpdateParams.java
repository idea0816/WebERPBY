package com.cxy.weberpby.dto;

import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.clzlsl;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/29
 * @Description 接收配方頁面前端傳來的3組JSON
 */
public class CLZLUpdateParams {
    private List<CLZL> oldCLZL;
    private List<CLZL> newCLZL;
    private List<clzlsl> newclzlsl;

    public List<CLZL> getOldCLZL() {
        return oldCLZL;
    }

    public void setOldCLZL(List<CLZL> oldCLZL) {
        this.oldCLZL = oldCLZL;
    }

    public List<CLZL> getNewCLZL() {
        return newCLZL;
    }

    public void setNewCLZL(List<CLZL> newCLZL) {
        this.newCLZL = newCLZL;
    }

    public List<clzlsl> getNewclzlsl() {
        return newclzlsl;
    }

    public void setNewclzlsl(List<clzlsl> newclzlsl) {
        this.newclzlsl = newclzlsl;
    }
}
