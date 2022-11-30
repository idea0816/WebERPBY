package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.bwBuild;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/6
 * @Description 部位规格建立1_部位B模具M顏色C
 */
public class bwBuildRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        bwBuild bwBuild = new bwBuild();
        bwBuild.setNO(rs.getString("NO"));
        bwBuild.setBwlb(rs.getString("bwlb"));
        bwBuild.setLbdh(rs.getString("lbdh"));
        bwBuild.setMjbh(rs.getString("mjbh"));
        bwBuild.setHw(rs.getString("hw"));
        bwBuild.setWidth(rs.getString("width"));
        bwBuild.setThickness(rs.getString("thickness"));
        bwBuild.setYSSM(rs.getString("YSSM"));
        return bwBuild;
    }
}
