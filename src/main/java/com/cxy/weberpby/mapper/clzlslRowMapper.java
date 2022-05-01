package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.clzlsl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成資料(clzlsl & clzlsz 共用)
 */
public class clzlslRowMapper implements RowMapper<clzlsl> {
    @Override
    public clzlsl mapRow(ResultSet rs, int rowNum) throws SQLException {
        clzlsl clzlsl = new clzlsl();
        clzlsl.setCldh(rs.getString("cldh"));
        clzlsl.setLb(rs.getString("lb"));
        clzlsl.setCldhz(rs.getString("cldhz"));
        clzlsl.setClyl(rs.getDouble("clyl"));
        clzlsl.setPhr(rs.getDouble("phr"));
        clzlsl.setCldj(rs.getDouble("cldj"));
        clzlsl.setUSERID(rs.getString("USERID"));
        clzlsl.setUSERDATE(rs.getString("USERDATE"));

        return clzlsl;
    }
}
