package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.LLZLS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/6/7
 * @Description LLZLS - 粗胚申領(領料明細)
 */
public class LLZLSRowMapper implements RowMapper<LLZLS> {
    @Override
    public LLZLS mapRow(ResultSet rs, int rowNum) throws SQLException {
        LLZLS llzls = new LLZLS();
        llzls.setLLBH(rs.getString("LLBH"));    // 單據編號
        llzls.setCQDH(rs.getString("CQDH"));
        llzls.setDDBH(rs.getString("DDBH"));    // 訂單編號(這裡寫入PGDATE、大車領料為了多單合併領料、要回寫鞋廠出貨單時、再去PGZLS找訂單編號
        llzls.setCldh(rs.getString("cldh"));    // 胚料代號
        llzls.setKGS_LL(rs.getDouble("KGS_LL"));  // 胚料重量	YL+CL
        llzls.setKGS_YL(rs.getDouble("KGS_YL"));  // 已領重量
        llzls.setKGS_CL(rs.getDouble("KGS_CL"));  // 超領重量
        llzls.setCLSM(rs.getString("CLSM"));    // 超領原因
        llzls.setUSERID(rs.getString("USERID"));
        llzls.setUSERDATE(rs.getString("USERDATE"));
        return llzls;
    }
}
