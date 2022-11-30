package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.LLZLDao;
import com.cxy.weberpby.mapper.LLZLRowMapper;
import com.cxy.weberpby.mapper.LLZLSRowMapper;
import com.cxy.weberpby.model.LLZL;
import com.cxy.weberpby.model.LLZLS;
import com.cxy.weberpby.model.LLZLS_temp;
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
 * @version Create Time: 2022/6/7
 * @Description 申領資料(LLZL & LLZLS)
 * <p>
 * List<String> getVersion(String LLBH);  // 取得領料最新單號
 * void insertLLZL(LLZL llzl);   // Insert LLZL
 * void insertLLZLS(LLZLS llzls);   // Inseert LLZLS
 * void insertLLZLS_temp(LLZLS_temp llzls_temp);   // Inseert LLZLS_temp
 * List<LLZL> getLLZL(String CFM);   // getLLZL(取得領料單)
 * List<LLZLS> getLLZLS(String LLBH);   // getLLZLS
 * List<LLZLS> checkLLZLS(String PGDATE);   // checkLLZLS
 */
@Component
public class LLZLDaoImpl implements LLZLDao {
    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得領料最新單號
    @Override
    public List<String> getVersion(String LLBH) {
        String sqlgetVersion = "SELECT TOP 1 LLBH " +
                "FROM LLZL " +
                "WHERE LLBH LIKE :LLBH " +
                "ORDER BY LLBH DESC ";
        map = new HashMap<>();
        map.put("LLBH", LLBH);

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("LLBH");
        List<String> getVersion = lbyddJdbcTemplate.query(sqlgetVersion, map, rowMapper);

        return getVersion;
    }

    // Insert LLZL
    @Override
    public void insertLLZL(LLZL llzl) {
        String sqlinserLLZL = "INSERT INTO LLZL " +
                "VALUES (:LLBH, :CQDH, :LLRQ, :CQDH1, :CFM, :USERID, :USERDATE)";

        map = new HashMap<>();
        map.put("LLBH", llzl.getLLBH());
        map.put("CQDH", llzl.getCQDH());
        map.put("LLRQ", llzl.getLLRQ());
        map.put("CQDH1", llzl.getCQDH1());
        map.put("CFM", llzl.getCFM());
        map.put("USERID", llzl.getUSERID());
        map.put("USERDATE", llzl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinserLLZL, map);
    }

    // Inseert LLZLS
    @Override
    public void insertLLZLS(LLZLS llzls) {
        String sqlinserLLZLS = "INSERT INTO LLZLS " +
                "VALUES (:LLBH, :CQDH, :DDBH, :cldh, :LB, :KGS_LL, :KGS_YL, :KGS_CL, :CLSM, :USERID, :USERDATE, :lbdh)";

        map = new HashMap<>();
        map.put("LLBH", llzls.getLLBH());
        map.put("CQDH", llzls.getCQDH());
        map.put("DDBH", llzls.getDDBH());
        map.put("cldh", llzls.getCldh());
        map.put("LB", llzls.getLB());
        map.put("KGS_LL", llzls.getKGS_LL());
        map.put("KGS_YL", llzls.getKGS_YL());
        map.put("KGS_CL", llzls.getKGS_CL());
        map.put("CLSM", llzls.getCLSM());
        map.put("USERID", llzls.getUSERID());
        map.put("USERDATE", llzls.getUSERDATE());
        map.put("lbdh", llzls.getLbdh());

        lbyddJdbcTemplate.update(sqlinserLLZLS, map);
    }

    // Inseert LLZLS_temp
    @Override
    public void insertLLZLS_temp(LLZLS_temp llzls_temp) {
        String sqlinserLLZLS_temp = "INSERT INTO LLZLS_temp " +
                "VALUES (:LLBH, :PGDATE1, :PGDATE2, :PGDATE3, :PGDATE4, :PGDATE5)";

        map = new HashMap<>();
        map.put("LLBH", llzls_temp.getLLBH());
        map.put("PGDATE1", llzls_temp.getPGDATE1());
        map.put("PGDATE2", llzls_temp.getPGDATE2());
        map.put("PGDATE3", llzls_temp.getPGDATE3());
        map.put("PGDATE4", llzls_temp.getPGDATE4());
        map.put("PGDATE5", llzls_temp.getPGDATE5());

        lbyddJdbcTemplate.update(sqlinserLLZLS_temp, map);

    }

    // getLLZL(取得領料單表頭)
    @Override
    public List<LLZL> getLLZL(String CFM) {
        String sqlgetLLZL = "SELECT LLBH, CQDH, LLRQ, CQDH1, CFM, USERID, USERDATE " +
                "FROM LLZL " +
                "WHERE CFM = :CFM";

        map = new HashMap<>();
        map.put("CFM", CFM);

        List<LLZL> getLLZL = lbyddJdbcTemplate.query(sqlgetLLZL, map, new LLZLRowMapper());
        if (getLLZL.size() > 0) {
            return getLLZL;
        } else {
            return null;
        }
    }

    // getLLZLS
    @Override
    public List<LLZLS> getLLZLS(String LLBH) {
        String sqlgetLLZLS = "SELECT LLBH, CLZL.zwpm AS CQDH, DDBH, LLZLS.cldh, LB, KGS_LL, KGS_YL, KGS_CL, CLSM, LLZLS.USERID, LLZLS.USERDATE, lbdh " +
                "FROM LLZLS " +
                "LEFT JOIN CLZL ON LLZLS.cldh = CLZL.cldh " +
                "WHERE LLBH = :LLBH ";

        map = new HashMap<>();
        map.put("LLBH", LLBH);

        List<LLZLS> getLLZLS = lbyddJdbcTemplate.query(sqlgetLLZLS, map, new LLZLSRowMapper());
        if (getLLZLS.size() > 0) {
            return getLLZLS;
        } else {
            return null;
        }
    }

    // checkLLZLS
    @Override
    public List<LLZLS> checkLLZLS(String PGDATE) {
        String sqlcheckLLZLS = "SELECT LLBH, CLZL.zwpm AS CQDH, DDBH, LLZLS.cldh, LB, KGS_LL, KGS_YL, KGS_CL, CLSM, LLZLS.USERID, LLZLS.USERDATE, lbdh " +
                "FROM LLZLS " +
                "LEFT JOIN CLZL ON LLZLS.cldh = CLZL.cldh " +
                "WHERE DDBH = :DDBH ";

        map = new HashMap<>();
        map.put("DDBH", PGDATE);

        List<LLZLS> checkLLZLS = lbyddJdbcTemplate.query(sqlcheckLLZLS, map, new LLZLSRowMapper());
        if (checkLLZLS.size() > 0) {
            return checkLLZLS;
        } else {
            return null;
        }
    }
}
