package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.xxgjs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/31
 * @Description 鞋廠降碼對照表
 */
public class xxgjsRowMapper implements RowMapper<xxgjs> {
    @Override
    public xxgjs mapRow(ResultSet rs, int rowNum) throws SQLException {
        xxgjs xxgjs = new xxgjs();
        xxgjs.setXieXing(rs.getString("XieXing"));
        xxgjs.setGJLB(rs.getString("GJLB"));
        xxgjs.setLineNum(rs.getString("LineNum"));
        xxgjs.setXXCC(rs.getString("XXCC"));
        xxgjs.setGJCC(rs.getString("GJCC"));
        xxgjs.setUSERID(rs.getString("USERID"));
        xxgjs.setUSERDATE(rs.getString("USERDATE"));
        xxgjs.setYN(rs.getString("YN"));
        return xxgjs;
    }
}
