package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.BDepartment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/31
 * @Description 資料
 */
public class BDepartmentRowMapper implements RowMapper<BDepartment> {
    @Override
    public BDepartment mapRow(ResultSet rs, int rowNum) throws SQLException {
        BDepartment bDepartment = new BDepartment();
        bDepartment.setID(rs.getString("ID"));
        bDepartment.setDepName(rs.getString("DepName"));
        bDepartment.setDepMemo(rs.getString("DepMemo"));
        return bDepartment;
    }
}
