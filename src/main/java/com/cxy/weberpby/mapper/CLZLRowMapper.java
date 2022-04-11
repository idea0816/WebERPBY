package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.CLZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 *
 * List<CLZL> getAllVofCLZL();   // Get All V of CLZL
 */

public class CLZLRowMapper implements RowMapper<CLZL> {
    @Override
    public CLZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        CLZL clzl = new CLZL();
        clzl.setCldh(rs.getString("cldh"));    // 材料代号
        clzl.setCllb(rs.getString("cllb"));    // 材料类别
        clzl.setZwpm(rs.getString("zwpm"));    // 中文品名
        clzl.setYwpm(rs.getString("ywpm"));    // Desc
        clzl.setDwbh(rs.getString("dwbh"));    // 单位
        clzl.setZsdh(rs.getString("zsdh"));    // 厂商代号
        clzl.setCldj(rs.getDouble("cldj"));    // 单价
        clzl.setClsl(rs.getDouble("clsl"));    // 库存数量
        clzl.setTotKgs(rs.getDouble("TotKgs"));  // 一手重量

        return clzl;
    }
}
