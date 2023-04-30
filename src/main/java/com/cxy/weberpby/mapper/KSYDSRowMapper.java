package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.KSYDS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/11/30
 * @Description 入出庫作業表身
 */
public class KSYDSRowMapper implements RowMapper<KSYDS> {
    @Override
    public KSYDS mapRow(ResultSet rs, int rowNum) throws SQLException {
        KSYDS ksyds = new KSYDS();
        ksyds.setDGLB(rs.getString("DGLB"));    // 單據類別	I:入庫  O:出庫 P:盤點 J:模具進 K:模具出 L:模具送修 M:模具返修
        ksyds.setKSDH(rs.getString("KSDH"));    // 異動單號
        ksyds.setCQDH(rs.getString("CQDH"));
        ksyds.setSH(rs.getString("SH"));  // 序號(001)	模具碼(EX:A.B.C模)
        ksyds.setCLDH(rs.getString("CLDH"));    // 材料代號	模具代號
        ksyds.setMSBZ(rs.getString("MSBZ"));    // 明細備註
        ksyds.setSL(rs.getDouble("SL"));  // 入庫數量
        ksyds.setDJ(rs.getDouble("DJ"));
        ksyds.setGR(rs.getDouble("GR"));
        ksyds.setSL1(rs.getDouble("SL1")); // 出庫數量
        ksyds.setGR1(rs.getDouble("GR1"));
        ksyds.setNY(rs.getString("NY"));  // 年月	1002
        ksyds.setUSERID(rs.getString("USERID"));
        ksyds.setUSERDATE(rs.getString("USERDATE"));
        return ksyds;
    }
}
