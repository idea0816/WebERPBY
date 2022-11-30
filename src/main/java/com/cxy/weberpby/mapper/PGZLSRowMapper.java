package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.PGZLS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料明細
 */
public class PGZLSRowMapper implements RowMapper<PGZLS> {
    @Override
    public PGZLS mapRow(ResultSet rs, int rowNum) throws SQLException {
        PGZLS pgzls = new PGZLS();
        pgzls.setPGDATE(rs.getString("PGDATE"));  // 派工編號
        pgzls.setCQDH(rs.getString("CQDH"));    // 廠區
        pgzls.setCldh(rs.getString("cldh"));    // 粗胚配方代號
        pgzls.setKGS(rs.getDouble("KGS")); // 派工重量
        pgzls.setPGSS(rs.getDouble("PGSS"));    // 派工手數
        pgzls.setKGS_RKS(rs.getDouble("KGS_RKS"));
        pgzls.setGSSM(rs.getString("GSSM"));    // 替代為班別
        pgzls.setUSERID(rs.getString("USERID"));
        pgzls.setUSERDATE(rs.getString("USERDATE"));
        return pgzls;
    }
}
