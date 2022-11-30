package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.SMDDS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description 鞋廠生管訂單明細資料(輪次表)
 */
public class SMDDSRowMapper implements RowMapper<SMDDS> {
    @Override
    public SMDDS mapRow(ResultSet rs, int rowNum) throws SQLException {
        SMDDS smdds = new SMDDS();
        smdds.setDDBH(rs.getString("DDBH"));    // 訂單編號
        smdds.setXXCC(rs.getString("XXCC"));    // 鞋型尺寸
        smdds.setQty(rs.getInt("Qty"));    // 雙數
        smdds.setUSERDate(rs.getString("USERDate"));
        smdds.setUSERID(rs.getString("USERID"));
        smdds.setYN(rs.getString("YN"));
        return smdds;
    }
}
