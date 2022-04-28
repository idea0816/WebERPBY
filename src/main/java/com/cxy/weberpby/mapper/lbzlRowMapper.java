package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.lbzl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/4/27
 * @Description 代号类别资料
 */
public class lbzlRowMapper implements RowMapper<lbzl> {
    @Override
    public lbzl mapRow(ResultSet rs, int rowNum) throws SQLException {
        lbzl lbzl = new lbzl();
        lbzl.setLb(rs.getString("lb"));
        lbzl.setZwsm(rs.getString("zwsm"));
        lbzl.setYwsm(rs.getString("ywsm"));
        lbzl.setBz(rs.getString("bz"));
        lbzl.setUSERID(rs.getString("USERID"));
        lbzl.setUSERDATE(rs.getString("USERDATE"));

        return lbzl;
    }
}
