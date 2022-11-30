package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.XXZLS1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/24
 * @Description XXZLS1 Size & Gram 資料
 */
public class XXZLS1RowMapper implements RowMapper<XXZLS1> {

    @Override
    public XXZLS1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        XXZLS1 xxzls1 = new XXZLS1();
        xxzls1.setXieXing(rs.getString("XieXing")); // 鞋型
        xxzls1.setSheHao(rs.getString("SheHao"));  // 色號
        xxzls1.setCQDH(rs.getString("CQDH"));
        xxzls1.setCC(rs.getString("CC"));  // 尺寸
        xxzls1.setG01(rs.getDouble("G01")); // 部位重量1
        xxzls1.setG02(rs.getDouble("G02")); // 部位重量2
        xxzls1.setG03(rs.getDouble("G03")); // 部位重量3
        xxzls1.setG04(rs.getDouble("G04")); // 部位重量4
        xxzls1.setG05(rs.getDouble("G05")); // 部位重量5
        xxzls1.setG06(rs.getDouble("G06")); // 部位重量6
        xxzls1.setG07(rs.getDouble("G07")); // 部位重量7
        xxzls1.setG08(rs.getDouble("G08")); // 部位重量8
        xxzls1.setG09(rs.getDouble("G09")); // 部位重量9
        xxzls1.setG10(rs.getDouble("G10")); // 部位重量10
        xxzls1.setG11(rs.getDouble("G11")); // 部位重量11
        xxzls1.setG12(rs.getDouble("G12")); // 部位重量12
        xxzls1.setG13(rs.getDouble("G13")); // 部位重量13
        xxzls1.setG14(rs.getDouble("G14")); // 部位重量14
        xxzls1.setG15(rs.getDouble("G15")); // 部位重量15
        xxzls1.setMjsl(rs.getDouble("mjsl"));
        xxzls1.setUSERID(rs.getString("USERID"));
        xxzls1.setUSERDATE(rs.getString("USERDATE"));
        return xxzls1;
    }
}
