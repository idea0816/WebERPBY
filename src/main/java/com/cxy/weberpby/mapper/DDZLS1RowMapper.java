package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.DDZLS1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/24
 * @Description DDZLS1 - 訂單(配方資料)
 */
public class DDZLS1RowMapper implements RowMapper<DDZLS1> {
    @Override
    public DDZLS1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        DDZLS1 ddzls1 = new DDZLS1();
        ddzls1.setDDBH(rs.getString("DDBH"));    // 訂單編號
        ddzls1.setCQDH(rs.getString("CQDH"));    // 廠區別
        ddzls1.setXh(rs.getString("xh"));  // 序號
        ddzls1.setCldh(rs.getString("cldh"));    // 配方代號
        ddzls1.setYSSM(rs.getString("YSSM"));    // 部位
        ddzls1.setKGS(rs.getDouble("KGS")); // 配方用量
        ddzls1.setLOSS(rs.getDouble("LOSS"));    // LOSS(KGS)
        ddzls1.setUSERID(rs.getString("USERID"));
        ddzls1.setUSERDATE(rs.getString("USERDATE"));
        ddzls1.setLB(rs.getString("LB"));  // 顏色(預設00)
        return ddzls1;
    }
}
