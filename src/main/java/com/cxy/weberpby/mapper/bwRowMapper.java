package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.lbzls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/10
 * @Description 鞋廠部位資料
 */
public class bwRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        lbzls bwData = new lbzls();
        bwData.setLbdh(rs.getString("lbdh"));   // 代號
        bwData.setZwsm(rs.getString("zwsm"));   // 中文說明

        return bwData;
    }
}
