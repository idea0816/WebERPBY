package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.LLZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/6/7
 * @Description LLZL - 粗胚申領作業(A3D)
 */
public class LLZLRowMapper implements RowMapper<LLZL> {
    @Override
    public LLZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        LLZL llzl = new LLZL();
        llzl.setLLBH(rs.getString("LLBH"));    // 單據編號 E220600001
        llzl.setCQDH(rs.getString("CQDH"));
        llzl.setLLRQ(rs.getString("LLRQ"));    // 領料日期 20100330
        llzl.setCQDH1(rs.getString("CQDH1"));   // 倉庫
        llzl.setCFM(rs.getString("CFM"));
        llzl.setUSERID(rs.getString("USERID"));
        llzl.setUSERDATE(rs.getString("USERDATE"));
        return llzl;
    }
}
