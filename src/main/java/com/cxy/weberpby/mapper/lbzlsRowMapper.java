package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.lbzls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 代号类别明细资料 - 上一层是 lbzl
 *
 */

public class lbzlsRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        lbzls lbzls = new lbzls();
        lbzls.setLb(rs.getString("lb"));
        lbzls.setLbdh(rs.getString("lbdh"));
        lbzls.setZwsm(rs.getString("zwsm"));
        lbzls.setYwsm(rs.getString("ywsm"));
        lbzls.setBz(rs.getString("bz"));
        lbzls.setBz1(rs.getString("bz1"));
        lbzls.setUSERID(rs.getString("USERID"));
        lbzls.setUSERDATE(rs.getString("USERDATE"));

        return lbzls;
    }
}
