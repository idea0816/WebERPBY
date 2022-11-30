package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.bwBuild2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/11
 * @Description 部位规格建立2_配方P,Size,Gram
 */
public class bwBuild2RowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        bwBuild2 bwBuild2 = new bwBuild2();
        bwBuild2.setNO(rs.getString("NO")); // 項次
        bwBuild2.setYSSM(rs.getString("YSSM")); // 部位說明
        bwBuild2.setCldh(rs.getString("cldh")); // 配方代號
        bwBuild2.setCC(rs.getString("CC")); // 尺寸
        bwBuild2.setG01(rs.getDouble("G01"));   // 部位重量
        bwBuild2.setG02(rs.getDouble("G02"));   // 長度
        return bwBuild2;
    }
}
