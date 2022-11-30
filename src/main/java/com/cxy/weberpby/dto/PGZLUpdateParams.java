package com.cxy.weberpby.dto;

import com.cxy.weberpby.model.PGZL;
import com.cxy.weberpby.model.PGZLS;
import com.cxy.weberpby.model.PGZLSdc;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 接收派工頁面前端傳來的3組JSON
 */
public class PGZLUpdateParams {

    private List<PGZL> pgzl;
    private List<PGZLS> pgzls;

    public List<PGZL> getPgzl() {
        return pgzl;
    }

    public void setPgzl(List<PGZL> pgzl) {
        this.pgzl = pgzl;
    }

    public List<PGZLS> getPgzls() {
        return pgzls;
    }

    public void setPgzls(List<PGZLS> pgzls) {
        this.pgzls = pgzls;
    }

}
