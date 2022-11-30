package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.DDZLS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/19
 * @Description 訂單明細資料(尺寸、數量)
 */
public class DDZLSRowMapper implements RowMapper<DDZLS> {
    @Override
    public DDZLS mapRow(ResultSet rs, int rowNum) throws SQLException {

        DDZLS ddzls = new DDZLS();
        ddzls.setDDBH(rs.getString("DDBH"));    // 訂單編號
        ddzls.setCQDH(rs.getString("CQDH"));    // 廠區別
        ddzls.setCC(rs.getString("CC"));   // 尺寸
        ddzls.setQty(rs.getInt("Qty"));    // 訂單數量
        ddzls.setPrice(rs.getDouble("Price"));   // 單價
        ddzls.setIprice(rs.getDouble("Iprice"));
        ddzls.setQtySC(rs.getInt("QtySC"));  // 已生產數量
        ddzls.setQtyCH(rs.getInt("QtyCH"));  // 已出貨數量
        ddzls.setUSERID(rs.getString("USERID"));
        ddzls.setUSERDATE(rs.getString("USERDATE"));
        ddzls.setQtyLOSS(rs.getInt("QtyLOSS"));    // 可LOSS雙數
        ddzls.setXh(rs.getString("xh"));
        return ddzls;
    }
}
