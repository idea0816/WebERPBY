package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.XXZLSERP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 鞋厂型体明细資料
 */

public class XXZLSERPRowMapper implements RowMapper<XXZLSERP> {
    @Override
    public XXZLSERP mapRow(ResultSet rs, int rowNum) throws SQLException {
        XXZLSERP xxzlErp = new XXZLSERP();
        xxzlErp.setXieXing(rs.getString("XieXing"));
        xxzlErp.setSheHao(rs.getString("SheHao"));
        xxzlErp.setYSSM(rs.getString("YSSM"));
        xxzlErp.setXTMH(rs.getString("XTMH"));
        xxzlErp.setDDMH(rs.getString("DDMH"));
        xxzlErp.setDAOMH(rs.getString("DAOMH"));
        xxzlErp.setBWBH(rs.getString("BWBH"));
        xxzlErp.setBwZwsm(rs.getString("zwsm"));
        xxzlErp.setCLBH(rs.getString("CLBH"));
        xxzlErp.setClZwpm(rs.getString("zwpm"));
        // xxzlErp.setCSBH(rs.getString("CSBH"));
        xxzlErp.setCCQQ(rs.getString("CCQQ"));
        xxzlErp.setCCQZ(rs.getString("CCQZ"));
        return xxzlErp;
    }
}
