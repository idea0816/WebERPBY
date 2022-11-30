package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.XXZLDao;
import com.cxy.weberpby.dto.XXZL3RowsMapper;
import com.cxy.weberpby.mapper.XXZLS1RowMapper;
import com.cxy.weberpby.mapper.XXZLSERPRowMapper;
import com.cxy.weberpby.mapper.XXZLSRowMapper;
import com.cxy.weberpby.mapper.xxgjsRowMapper;
import com.cxy.weberpby.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体資料
 * <p>
 * List<XXZL> getErpXSA();  // 取得鞋廠型体资料(XieXing, SheHao, ARTICLE)(*注意、是已放行的订单*)
 * List<XXZL> getXSA();  // 取得型体资料(XieXing, SheHao, ARTICLE)
 * String XXZLInsert(XXZL xxzl);    // 写入型体资料 From ERP
 * void XXZLSInsert(XXZLS xxzls); // 写入部位 & 配方资料
 * void XXZLSDelete(String XS); // 刪除部位 & 配方资料
 * String XXZLS1Insert(XXZLS1 xxzls1);  // 写入 Size 资料 From ERP
 * void XXZLS1Update(String sql);  // 写入重量资料
 * <p>
 * List<String> getErpBWBH(); // 取得鞋厂部位編號(BOM、部位編號)(先取得全部編號、再比對)
 * public List<String> countNoXXZLData()  // 檢查有多少型體沒有部位資料
 * public List<String> countNoXXZLData();    // 列出沒有部位的ARTICLE
 * Integer checkARTICLE(ARTICLE);   // 檢查底廠有沒有型體資料
 * List<String> getARTICLE();   // 取得ARTICLE
 * List<XXZLSERP> getXXZLSErp(String ARTICLE);    // 取得鞋廠BOM表
 * List<XXZLS> getXXZLS(String ARTICLE);    // 取得底廠部位資料
 * List<XXZLS1> getXXZLS1(String XS);    // 取得底廠部位Size & Gram資料
 * List<xxgjs> getxxgjs(String XieXing);    // 取得鞋廠降碼對照表
 */

@Component
public class XXZLDaoImpl implements XXZLDao {

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    @Autowired
    @Qualifier("myJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    Map<String, Object> map;

    // 取得鞋廠型体资料(XieXing, SheHao, ARTICLE)(*注意、是已放行的订单*)
    @Override
    public List<XXZL> getErpXSA() {
        String sqlgetErpXSA = "SELECT XieXing, SheHao, ARTICLE " +
                "FROM LBY_ERP.dbo.DDZL " +
                "WHERE GSBH = 'B07U' " +
                "GROUP BY XieXing, SheHao, ARTICLE";

        map = new HashMap<>();
        List<XXZL> getErpXSA = lbyddJdbcTemplate.query(sqlgetErpXSA, map, new XXZL3RowsMapper());

        if (getErpXSA.size() > 0) {
            return getErpXSA;
        } else {
            return null;
        }
    }

    // 取得型体资料(XieXing, SheHao, ARTICLE)
    @Override
    public List<XXZL> getXSA() {
        String sqlgetXSA = "SELECT XieXing, SheHao, ARTICLE " +
                "FROM XXZL " +
                "WHERE CQDH = 'B7U' " +
                "GROUP BY XieXing, SheHao, ARTICLE";

        map = new HashMap<>();
        List<XXZL> getXSA = lbyddJdbcTemplate.query(sqlgetXSA, map, new XXZL3RowsMapper());

        if (getXSA.size() > 0) {
            return getXSA;
        } else {
            return null;
        }
    }

    // 写入型体资料 From ERP
    @Override
    public String XXZLInsert(XXZL xxzl) {
        String XXZLInsert = "INSERT INTO XXZL (XieXing, SheHao, CQDH, ARTICLE, KHDH, USERID, USERDATE) " +
                "VALUES (:XieXing, :SheHao, 'B7U', :ARTICLE, 'B07U', 'SUPER', :USERDATE)";

        map = new HashMap<>();
        map.put("XieXing", xxzl.getXieXing());
        map.put("SheHao", xxzl.getSheHao());
        map.put("ARTICLE", xxzl.getARTICLE());
        map.put("USERDATE", xxzl.getUSERDATE());

        lbyddJdbcTemplate.update(XXZLInsert, map);
        return null;
    }

    // 写入重量资料
    @Override
    public void XXZLS1Update(String sql) {
        String sqlXXZLS1Update = sql;

        map = new HashMap<>();

        lbyddJdbcTemplate.update(sqlXXZLS1Update, map);
    }

    // 写入 Size 资料 From ERP
    @Override
    public String XXZLS1Insert(XXZLS1 xxzls1) {
        String XXZLS1Insert = "INSERT INTO XXZLS1 (XieXing, SheHao, CQDH, CC, USERID, USERDATE) " +
                "SELECT :XieXing, :SheHao, 'B7U', size, 'SUPER', :USERDATE " +
                "FROM FSIZES WHERE bh = 'US' AND size BETWEEN '02' AND '18.0' ORDER BY size ";

        map = new HashMap<>();
        map.put("XieXing", xxzls1.getXieXing());
        map.put("SheHao", xxzls1.getSheHao());
        map.put("USERDATE", xxzls1.getUSERDATE());

        lbyddJdbcTemplate.update(XXZLS1Insert, map);
        return null;
    }

    // 刪除部位 & 配方资料
    @Override
    public void XXZLSDelete(String XS) {
        String sqlXXZLSDelete = "DELETE FROM XXZLS " +
                "WHERE XieXing+SheHao = :XS ";
        map = new HashMap<>();
        map.put("XS", XS);

        lbyddJdbcTemplate.update(sqlXXZLSDelete, map);

    }

    // 写入部位 & 配方资料 From ERP
    @Override
    public void XXZLSInsert(XXZLS xxzls) {
        String XXZLSInsert = "INSERT INTO XXZLS (XieXing, SheHao, CQDH, xh, cldh, YSSM, USERID, USERDATE, LB) " +
                "VALUES (:XieXing, :SheHao, 'B7U', :xh, :cldh, :YSSM, 'SUPER', :USERDATE, :LB)";

        map = new HashMap<>();
        map.put("XieXing", xxzls.getXieXing());
        map.put("SheHao", xxzls.getSheHao());
        map.put("xh", xxzls.getXh());
        map.put("cldh", xxzls.getCldh());
        map.put("YSSM", xxzls.getYSSM());
        map.put("USERDATE", xxzls.getUSERDATE());
        map.put("LB", xxzls.getLB());
        lbyddJdbcTemplate.update(XXZLSInsert, map);
    }

    // 取得鞋厂部位編號(BOM、部位編號)(先取得全部編號、再比對)
    @Override
    public List<String> getErpBWBH() {
        // 在鞋廠的xxzls(BOM)裡供應商是寶億(CSBH = 'BAO') & 材料代號J開頭的(CLBH LIKE 'J%')視為底廠的部位
        String sqlgetErpBw = "SELECT BWBH FROM LBY_ERP.dbo.xxzls " +
                "WHERE CSBH = 'BAO' AND CLBH LIKE 'J%' GROUP BY BWBH";

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("BWBH");

        return lbyddJdbcTemplate.query(sqlgetErpBw, map, rowMapper);
    }

    // 檢查有多少型體沒有部位資料
    @Override
    public String countNoXXZLData() {
        String sqlCount = "SELECT COUNT(*) CountN " +
                "FROM XXZL " +
                "WHERE XieXing + SheHao " +
                "NOT IN (SELECT XieXing + SheHao FROM XXZLS WHERE CQDH= 'B7U') " +
                "AND CQDH = 'B7U'";

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        // RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("CountN");

        //JDBC Template
        return jdbcTemplate.queryForObject(sqlCount, String.class);
    }

    // 列出沒有部位的ARTICLE
    @Override
    public List<XXZL> getXSANA() {
        String sqlgetXSANA = "SELECT XieXing, SheHao, ARTICLE " +
                "FROM XXZL " +
                "WHERE CQDH = 'B7U' " +
                "AND XieXing + SheHao " +
                "NOT IN (SELECT XieXing + SheHao FROM XXZLS WHERE CQDH= 'B7U') " +
                "AND CQDH = 'B7U' " +
                "GROUP BY XieXing, SheHao, ARTICLE";

        map = new HashMap<>();
        List<XXZL> getXSANA = lbyddJdbcTemplate.query(sqlgetXSANA, map, new XXZL3RowsMapper());

        if (getXSANA.size() > 0) {
            return getXSANA;
        } else {
            return null;
        }
    }

    // 檢查底廠有沒有型體資料
    @Override
    public List<Integer> checkARTICLE(String ARTICLE) {
        String sqlcheckARTICLE = "SELECT COUNT(*) " +
                "FROM xxzls xxzls " +
                "LEFT JOIN xxzl xxzl ON xxzl.XieXing+xxzl.SheHao = xxzls.XieXing+xxzls.SheHao " +
                "WHERE xxzl.ARTICLE = :ARTICLE " +
                "AND xxzl.CQDH = 'B7U' ";

        map = new HashMap<>();
        map.put("ARTICLE", ARTICLE);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<Integer> rowMapper = (rs, rowNum) -> rs.getInt(1);

        return lbyddJdbcTemplate.query(sqlcheckARTICLE, map, rowMapper);
    }

    // 取得ARTICLE
    @Override
    public List<String> getARTICLE() {
        String sqlGetARTICLE = "SELECT ARTICLE FROM XXZL WHERE CQDH = 'B7U' GROUP BY ARTICLE";
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("ARTICLE");

        return lbyddJdbcTemplate.query(sqlGetARTICLE, map, rowMapper);
    }

    // 取得鞋廠BOM表
    @Override
    public List<XXZLSERP> getXXZLSErp(String ARTICLE) {
        String sqlgetXXZLSErp = "SELECT xxzl.XieXing, xxzl.SheHao, xxzl.YSSM, xxzl.XTMH, xxzl.DDMH, xxzl.DAOMH, " +
                "       xxzls.BWBH, bwzl.zwsm, xxzls.CLBH, clzl.zwpm, xxzls.CCQQ, xxzls.CCQZ " +
                "FROM LBY_ERP.dbo.xxzl xxzl " +
                "LEFT JOIN LBY_ERP.dbo.xxzls xxzls ON xxzls.XieXing+xxzls.SheHao = xxzl.XieXing+xxzl.SheHao " +
                "LEFT JOIN LBY_ERP.dbo.bwzl bwzl ON xxzls.BWBH = bwzl.bwdh " +
                "LEFT JOIN LBY_ERP.dbo.clzl clzl ON xxzls.CLBH = clzl.cldh " +
                "WHERE xxzl.ARTICLE = :ARTICLE " +
                "AND xxzls.CLBH LIKE 'J%'";

        map = new HashMap<>();
        map.put("ARTICLE", ARTICLE);
        List<XXZLSERP> getXXZLSErp = lbyddJdbcTemplate.query(sqlgetXXZLSErp, map, new XXZLSERPRowMapper());

        if (getXXZLSErp.size() > 0) {
            return getXXZLSErp;
        } else {
            return null;
        }
    }

    // 取得底廠部位資料
    @Override
    public List<XXZLS> getXXZLS(String ARTICLE) {
        String sqlgetXXZLS = "SELECT XXZLS.XieXing, XXZLS.SheHao, XXZLS.CQDH, XXZLS.xh, XXZLS.cldh, XXZLS.YSSM, XXZLS.USERID, XXZLS.USERDATE, XXZLS.LB, XXZLS.HZS, XXZLS.DZ, XXZLS.RS  " +
                "FROM XXZLS XXZLS,XXZL XXZL " +
                "WHERE XXZLS.XieXing+XXZLS.SheHao = XXZL.XieXing+XXZL.SheHao " +
                "AND XXZL.ARTICLE = :ARTICLE " +
                "ORDER BY xh ";

        map = new HashMap<>();
        map.put("ARTICLE", ARTICLE);
        List<XXZLS> getXXZLS = lbyddJdbcTemplate.query(sqlgetXXZLS, map, new XXZLSRowMapper());

        if (getXXZLS.size() > 0) {
            return getXXZLS;
        } else {
            return null;
        }
    }

    // 取得底廠部位Size & Gram資料
    @Override
    public List<XXZLS1> getXXZLS1(String XS) {
        String sqlgetXXZLS1 = "SELECT XieXing, SheHao, CQDH, CC, G01, G02, G03, G04, G05, G06, G07, G08, G09, G10, G11, G12, G13, G14, G15, mjsl, USERID, USERDATE " +
                "FROM XXZLS1 " +
                "WHERE XieXing+SheHao = :XS " +
                "ORDER BY CC ";

        map = new HashMap<>();
        map.put("XS", XS);
        List<XXZLS1> getXXZLS1 = lbyddJdbcTemplate.query(sqlgetXXZLS1, map, new XXZLS1RowMapper());

        if (getXXZLS1.size() > 0) {
            return getXXZLS1;
        } else {
            return null;
        }
    }

    // 取得鞋廠降碼對照表
    @Override
    public List<xxgjs> getxxgjs(String XieXing) {
        String sqlgetxxgjs = "SELECT XieXing, GJLB, LineNum, XXCC, GJCC, USERID, USERDATE, YN " +
                "FROM LBY_ERP.dbo.xxgjs " +
                "WHERE XieXing = :XieXing " +
                "AND GJLB = '101' ";

        map = new HashMap<>();
        map.put("XieXing", XieXing);
        List<xxgjs> getxxgjs = lbyddJdbcTemplate.query(sqlgetxxgjs, map, new xxgjsRowMapper());

        if (getxxgjs.size() > 0) {
            return getxxgjs;
        } else {
            return null;
        }
    }


    // 錯誤訊息如何回傳？？
}
