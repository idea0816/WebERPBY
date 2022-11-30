package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.SMDD;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description 鞋廠生管訂單資料(部份)
 */
public class SMDDRowMapper implements RowMapper<SMDD> {
    @Override
    public SMDD mapRow(ResultSet rs, int rowNum) throws SQLException {
        SMDD smdd = new SMDD();
        smdd.setDDBH(rs.getString("DDBH"));    // 訂單編號
        smdd.setXH(rs.getString("XH"));  // 輪次
        smdd.setPlanDate(rs.getString("PlanDate"));    // 成型上線日--成型投入(B)
        smdd.setQty(rs.getInt("Qty"));    // 雙數
        smdd.setDepNO(rs.getString("DepNO"));   // 成型單位
        return smdd;
    }
}
