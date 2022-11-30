package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.PGZLDao;
import com.cxy.weberpby.mapper.BDepartmentRowMapper;
import com.cxy.weberpby.mapper.PGZLRowMapper;
import com.cxy.weberpby.mapper.PGZLS1RowMapper;
import com.cxy.weberpby.mapper.PGZLSRowMapper;
import com.cxy.weberpby.model.BDepartment;
import com.cxy.weberpby.model.PGZL;
import com.cxy.weberpby.model.PGZLS;
import com.cxy.weberpby.model.PGZLS1;
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
 * @version Create Time: 2022/5/25
 * @Description 派工資料
 * <p>
 * String getVersion(String PGDATE);  // 取得派工最新單號
 * List<PGZL> getPGZL(String BZ);   // 取得派工表頭
 * List<PGZLS> getPGZLS(String PGDATE); // Get PGZLS
 * List<PGZLS> getPGZLSdc(String PGDATE); // Get PGZLSdc
 * void insertPGZL(PGZL pgzl);    // Insert Data to PGZL
 * void insertPGZLS(PGZLS pgzls);    // Insert Data to PGZLS
 * void insertPGZLS1(PGZLS1 pgzls1);    // Insert Data to PGZLS1
 * List<PGZLS1> getPGZLS1(String PGDATE); // Get PGZLS1
 * List<BDepartment> getdepList();    // 取得鞋廠部門資料
 *
 * void inserPGZLS1_temp(PGZLS1 pgzls1);    // Insert Data to PGZLS1_temp(以後要刪)
 * List<PGZLS1> getPGZLS1_temp(); // Get PGZLS1_temp(以後要刪)
 * void deletePGZLS1_temp();  // 刪除 PGZLS1_temp(以後要刪)
 */

@Component
public class PGZLDaoImpl implements PGZLDao {

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得派工最新單號
    @Override
    public List<String> getVersion(String PGDATE) {
        String sqlgetVersion = "SELECT TOP 1 PGDATE " +
                "FROM PGZL " +
                "WHERE PGDATE LIKE :PGDATE " +
                "ORDER BY PGDATE DESC ";
        map = new HashMap<>();
        map.put("PGDATE", PGDATE);

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("PGDATE");
        List<String> getVersion = lbyddJdbcTemplate.query(sqlgetVersion, map, rowMapper);

        return getVersion;
    }

    // 取得派工表頭
    @Override
    public List<PGZL> getPGZL(String BZ) {
        String sqlgetPGZL = "SELECT PGDATE, CQDH, DATE1, BZ, CFM, USERID, USERDATE " +
                "FROM PGZL " +
                "WHERE CFM = 'N' ";

        map = new HashMap<>();
        if (BZ != "") {
            sqlgetPGZL = sqlgetPGZL + " AND BZ = :BZ";
            map.put("BZ", BZ);
        }

        List<PGZL> getPGZL = lbyddJdbcTemplate.query(sqlgetPGZL, map, new PGZLRowMapper());
        if (getPGZL.size() > 0) {
            return getPGZL;
        } else {
            return null;
        }
    }

    // Get PGZLS
    @Override
    public List<PGZLS> getPGZLS(String PGDATE) {
        String sqlgetPGZLS = "SELECT PGDATE, CLZL.zwpm AS CQDH, PGZLS.cldh, KGS, PGSS, KGS_RKS, GSSM, PGZLS.USERID, PGZLS.USERDATE " +
                "FROM PGZLS " +
                "LEFT JOIN CLZL ON PGZLS.cldh = CLZL.cldh " +
                "WHERE PGDATE = :PGDATE";

        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        List<PGZLS> getPGZLS = lbyddJdbcTemplate.query(sqlgetPGZLS, map, new PGZLSRowMapper());
        if (getPGZLS.size() > 0) {
            return getPGZLS;
        } else {
            return null;
        }
    }

    // Get PGZLSdc
    @Override
    public List<PGZLS> getPGZLSdc(String PGDATE) {
        String sqlgetPGZLSdc = "SELECT PGDATE, CQDH, cldh, KGS, PGSS, KGS_RKS, GSSM, USERID, USERDATE " +
                "FROM PGZLS " +
                "WHERE PGDATE = :PGDATE";

        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        List<PGZLS> getPGZLSdc = lbyddJdbcTemplate.query(sqlgetPGZLSdc, map, new PGZLSRowMapper());
        if (getPGZLSdc.size() > 0) {
            return getPGZLSdc;
        } else {
            return null;
        }
    }

    // Insert Data to PGZL
    @Override
    public void insertPGZL(PGZL pgzl) {
        String sqlinserPGZL = "INSERT INTO PGZL " +
                "VALUES (:PGDATE, :CQDH, :DATE1, :BZ, :CFM, :USERID, :USERDATE)";
        map = new HashMap<>();
        map.put("PGDATE", pgzl.getPGDATE());
        map.put("CQDH", pgzl.getCQDH());
        map.put("DATE1", pgzl.getDATE1());
        map.put("BZ", pgzl.getBZ());
        map.put("CFM", pgzl.getCFM());
        map.put("USERID", pgzl.getUSERID());
        map.put("USERDATE", pgzl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinserPGZL, map);
    }

    // Insert Data to PGZLS
    @Override
    public void insertPGZLS(PGZLS pgzls) {
        String sqlinserPGZLS = "INSERT INTO PGZLS " +
                "VALUES (:PGDATE, :CQDH, :cldh, :KGS, :PGSS, :KGS_RKS, :GSSM, :USERID, :USERDATE)";
        map = new HashMap<>();
        map.put("PGDATE", pgzls.getPGDATE());
        map.put("CQDH", pgzls.getCQDH());
        map.put("cldh", pgzls.getCldh());
        map.put("KGS", pgzls.getKGS());
        map.put("PGSS", pgzls.getPGSS());
        map.put("KGS_RKS", pgzls.getKGS_RKS());
        map.put("GSSM", pgzls.getGSSM());
        map.put("USERID", pgzls.getUSERID());
        map.put("USERDATE", pgzls.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinserPGZLS, map);
    }

    // Insert Data to PGZLS1
    @Override
    public void insertPGZLS1(PGZLS1 pgzls1) {
        String sqlinserPGZLS1 = "INSERT INTO PGZLS1 " +
                "VALUES (:PGDATE, :CQDH, :cldh, :clyl, :clll, :USERID, :USERDATE)";
        map = new HashMap<>();
        map.put("PGDATE", pgzls1.getPGDATE());
        map.put("CQDH", pgzls1.getCQDH());
        map.put("cldh", pgzls1.getCldh());
        map.put("clyl", pgzls1.getClyl());
        map.put("clll", pgzls1.getClll());
        map.put("USERID", pgzls1.getUSERID());
        map.put("USERDATE", pgzls1.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinserPGZLS1, map);
    }

    // Get PGZLS1
    @Override
    public List<PGZLS1> getPGZLS1(String PGDATE) {
        String sqlgetPGZLS1 = "SELECT PGDATE, CLZL.zwpm AS CQDH, PGZLS1.cldh, clyl, clll, PGZLS1.USERID, PGZLS1.USERDATE " +
                "FROM PGZLS1 " +
                "LEFT JOIN CLZL ON PGZLS1.cldh = CLZL.cldh " +
                "WHERE PGDATE = :PGDATE ";

        map = new HashMap<>();
        map.put("PGDATE", PGDATE);

        List<PGZLS1> getPGZLS1 = lbyddJdbcTemplate.query(sqlgetPGZLS1, map, new PGZLS1RowMapper());
        if (getPGZLS1.size() > 0) {
            return getPGZLS1;
        } else {
            return null;
        }
    }

    // 取得鞋廠部門資料
    @Override
    public List<BDepartment> getdepList() {
        String sqlgetdepList = "SELECT ID, DepName, DepMemo " +
                "FROM LBY_ERP.dbo.BDepartment " +
                "WHERE GXLB = 'A'";

        map = new HashMap<>();
        List<BDepartment> getdepList = lbyddJdbcTemplate.query(sqlgetdepList, map, new BDepartmentRowMapper());
        if (getdepList.size() > 0) {
            return getdepList;
        } else {
            return null;
        }
    }

    // Insert Data to PGZLS1_temp(以後要刪)
    @Override
    public void inserPGZLS1_temp(PGZLS1 pgzls1) {
        String sqlinserPGZLS1_temp = "INSERT INTO PGZLS1_temp " +
                "VALUES (:cldh, :clyl)";
        map = new HashMap<>();
        map.put("cldh", pgzls1.getCldh());
        map.put("clyl", pgzls1.getClyl());

        lbyddJdbcTemplate.update(sqlinserPGZLS1_temp, map);
    }

    // Get PGZLS1_temp(以後要刪)
    @Override
    public List<PGZLS1> getPGZLS1_temp() {
        String sqlgetPGZLS1_temp = "SELECT '' AS PGDATE, '' AS CQDH, cldh, sum(clyl) AS clyl, 0.0 AS clll, '' AS USERID, '' AS USERDATE " +
                "FROM PGZLS1_temp " +
                "GROUP BY cldh " +
                "ORDER BY cldh";

        map = new HashMap<>();
        List<PGZLS1> getPGZLS1_temp = lbyddJdbcTemplate.query(sqlgetPGZLS1_temp, map, new PGZLS1RowMapper());
        if (getPGZLS1_temp.size() > 0) {
            return getPGZLS1_temp;
        } else {
            return null;
        }
    }

    // 刪除 PGZLS1_temp(以後要刪)
    @Override
    public void deletePGZLS1_temp() {
        String sqldeletePGZLS1_temp = "DELETE FROM PGZLS1_temp";
        lbyddJdbcTemplate.update(sqldeletePGZLS1_temp, map);
    }
}
