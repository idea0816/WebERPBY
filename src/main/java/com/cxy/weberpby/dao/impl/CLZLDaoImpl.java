package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.CLZLDao;
import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.mapper.CLZLRowMapper;
import com.cxy.weberpby.model.CLZL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 * <p>
 * List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams); // CLZL Data  // Get V(传回的值要考虑 NULL 的状态吗？) // Get A
 * void insertCLZL(CLZL CLZL);  // 新增CLZL資料
 * void updateCLZL(CLZL CLZL);  // Update CLZL
 * void deleteCLZL(String cldh)    // Delete CLZL
 * List<CLZL> getOldDdCLZLa();  // 取得小于本月的底厂材料基本资料
 * List<String> getVersion(String cldh)  // 取得配方最新版次
 */

@Component
public class CLZLDaoImpl implements CLZLDao {

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // CLZL Data  // Get V(传回的值要考虑 NULL 的状态吗？) // Get A
    @Override
    public List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams) {
        String sqlCLZL = "SELECT cldh, cllb, zwpm, ywpm, dwbh, zsdh, cldj, clsl, TotKgs, USERID, USERDATE, cgcqdh, CGLB " +
                "FROM CLZL WHERE 1=1 ";

        map = new HashMap<>();

        if (clzlQueryParams.getLb() != null) {
            sqlCLZL = sqlCLZL + " AND cllb = :cllb";
            map.put("cllb", clzlQueryParams.getLb());
        }
        if (clzlQueryParams.getCldh() != null) {
            sqlCLZL = sqlCLZL + " AND cldh = :cldh";
            map.put("cldh", clzlQueryParams.getCldh());
        }

        List<CLZL> list = lbyddJdbcTemplate.query(sqlCLZL, map, new CLZLRowMapper());

        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    // 新增CLZL資料
    @Override
    public void insertCLZL(CLZL CLZL) {
        String sqlinsertCLZL = "INSERT INTO CLZL (cldh, cllb, zwpm, dwbh, cldj, TotKgs, USERID, USERDATE, cgcqdh) " +
                "VALUES (:cldh, :cllb, :zwpm, :dwbh, :cldj, :TotKgs, :USERID, :USERDATE, :cgcqdh)";
        map = new HashMap<>();
        map.put("cldh", CLZL.getCldh());
        map.put("cllb", CLZL.getCllb());
        map.put("zwpm", CLZL.getZwpm());
        map.put("dwbh", CLZL.getDwbh());
        map.put("cldj", CLZL.getCldj());
        map.put("TotKgs", CLZL.getTotKgs());
        map.put("USERID", CLZL.getUSERID());
        map.put("USERDATE", CLZL.getUSERDATE());
        map.put("cgcqdh", CLZL.getCgcqdh());

        lbyddJdbcTemplate.update(sqlinsertCLZL, map);
    }

    // Update CLZL
    @Override
    public void updateCLZL(CLZL CLZL) {
        String sqlupdateCLZL = "UPDATE CLZL " +
                "SET cllb = :cllb, zwpm = :zwpm, dwbh= :dwbh, cldj = :cldj, TotKgs = :TotKgs, USERID = :USERID, USERDATE = :USERDATE, cgcqdh = :cgcqdh " +
                "WHERE cldh = :cldh";
        map = new HashMap<>();
        map.put("cllb", CLZL.getCllb());
        map.put("cldh", CLZL.getCldh());
        map.put("zwpm", CLZL.getZwpm());
        map.put("dwbh", CLZL.getDwbh());
        map.put("cldj", CLZL.getCldj());
        map.put("TotKgs", CLZL.getTotKgs());
        map.put("USERID", CLZL.getUSERID());
        map.put("USERDATE", CLZL.getUSERDATE());
        map.put("cgcqdh", CLZL.getCgcqdh());

        lbyddJdbcTemplate.update(sqlupdateCLZL, map);
    }

    // Delete CLZL
    @Override
    public void deleteCLZL(String cldh) {
        String sqldeleteCLZL = "DELETE FROM CLZL WHERE cldh = :cldh";
        map = new HashMap<>();
        map.put("cldh", cldh);

        lbyddJdbcTemplate.update(sqldeleteCLZL, map);

    }

    // 取得小于本月的底厂材料基本资料
    @Override
    public List<CLZL> getOldDdCLZLa(String yyyyMM) {
        String sqlOldCLZLa = "SELECT cldh, cllb, zwpm, ywpm, dwbh, zsdh, cldj, clsl, TotKgs, USERID, USERDATE " +
                "FROM CLZL " +
                "WHERE cldh LIKE 'AW%' " +
                "AND USERDATE < :yyyyMM";

        map = new HashMap<>();
        map.put("yyyyMM", yyyyMM);

        List<CLZL> list = lbyddJdbcTemplate.query(sqlOldCLZLa, map, new CLZLRowMapper());

        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    // 取得配方最新版次
    @Override
    public List<String> getVersion(String cldh) {
        String sqlgetVersion = "SELECT TOP 1 cldh " +
                "FROM CLZL " +
                "WHERE cldh LIKE :cldh " +
                "ORDER BY cldh DESC ";
        map = new HashMap<>();
        map.put("cldh", cldh);

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("cldh");
        List<String> getVersion = lbyddJdbcTemplate.query(sqlgetVersion, map, rowMapper);

        return getVersion;
    }
}
