package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.KSYD;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/11/30
 * @Description 入出庫作業表頭
 */
public class KSYDRowMapper implements RowMapper<KSYD> {
    @Override
    public KSYD mapRow(ResultSet rs, int rowNum) throws SQLException {
        KSYD ksyd = new KSYD();
        ksyd.setDGLB(rs.getString("DGLB"));    // 單據類別	I:入庫  O:出庫 P:盤點 J:模具進 K:模具出 L:模具送修 M:模具返修
        ksyd.setCQDH(rs.getString("CQDH"));
        ksyd.setKSDH(rs.getString("KSDH"));    // 異動單號	來源類別(1)+年(2)+月(2)+流水碼(5) = 10 ex:B120500053
        ksyd.setKSRQ(rs.getString("KSRQ"));    // 異動日期
        ksyd.setLYLB(rs.getString("LYLB"));    // 來源類別	A:入庫 B:出庫 C:盤點 E:A2K CFM F:A1W CFM J:my java
        ksyd.setLYDH(rs.getString("LYDH"));    // 來源單號	模具維修商對照zszl.zsdh
        ksyd.setBZ(rs.getString("BZ"));  // 備註
        ksyd.setUSERID(rs.getString("USERID"));
        ksyd.setUSERDATE(rs.getString("USERDATE"));
        ksyd.setZSDH(rs.getString("ZSDH"));    // 模具SIZE

        return ksyd;
    }
}
