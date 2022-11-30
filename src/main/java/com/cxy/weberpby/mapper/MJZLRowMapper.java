package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.MJZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具資料(含MJZLS資料)
 */
public class MJZLRowMapper implements RowMapper<MJZL> {
    @Override
    public MJZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        MJZL mjzl = new MJZL();
        mjzl.setMjbh(rs.getString("mjbh"));    // 模具編號(MJZLS & MJZLS)
        mjzl.setLbdh(rs.getString("lbdh"));    // 類別代號(MJZLS & MJZLS)
        mjzl.setSize(rs.getString("size"));    // 尺寸(MJZLS)
        mjzl.setMjsl(rs.getInt("mjsl"));   // 模具數量(MJZLS)
        mjzl.setUSERID(rs.getString("USERID"));  // (MJZLS & MJZLS)
        mjzl.setUSERDATE(rs.getString("USERDATE"));    // (MJZLS & MJZLS)

        return mjzl;
    }
}
