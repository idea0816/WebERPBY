package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.XXZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体基本資料
 */

public class XXZLRowMapper implements RowMapper<XXZL> {
    @Override
    public XXZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        XXZL xxzl = new XXZL();
        xxzl.setXieXing(rs.getString("XieXing"));
        xxzl.setSheHao(rs.getString("SheHao"));
        xxzl.setCQDH(rs.getString("CQDH"));
        xxzl.setYSSM(rs.getString("YSSM"));
        xxzl.setARTICLE(rs.getString("ARTICLE"));
        xxzl.setMjbh(rs.getString("mjbh"));
        xxzl.setLbdh(rs.getString("lbdh"));
        xxzl.setKHDH(rs.getString("KHDH"));
        xxzl.setHZS(rs.getDouble("HZS"));
        xxzl.setLOSS(rs.getDouble("LOSS"));
        xxzl.setUSERID(rs.getString("USERID"));
        xxzl.setUSERDATE(rs.getString("USERDATE"));
        xxzl.setJBYX(rs.getInt("JbYX"));
        xxzl.setJBMS(rs.getInt("JBMS"));
        xxzl.setCCBT(rs.getInt("CCBT"));

        return xxzl;
    }
}
