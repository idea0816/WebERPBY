package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.DDZLDao;
import com.cxy.weberpby.mapper.*;
import com.cxy.weberpby.model.*;
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
 * @Description 订单資料
 * <p>
 * List<String> getDDBHerp();  // 取得鞋厂订单资料（订单号）(*注意、是已放行的订单*)
 * String DDZLInsert(DDZL ddzl);    // 写入底厂订单资料 From ERP
 * String DDZLSInsert(DDZL ddzl);    // 写入底厂 Size & 双数 资料 From ERP
 * void DDZLS1Insert(DDZLS1 ddzls1);    // 写入 部位 & 配方 资料
 * void DDZLS1Delete(String DDBH);  // 刪除 部位 & 配方 资料
 * List<DDZL> getDDZL(String StarDate, String EndDate);    // 取得訂單資料
 * List<DDZL> getDDZL(String DDBH);    // 取得訂單資料 by DDBH
 * List<DDZL> getDDZLbyXXZLS();  // 取得订单资料（依XXZLS有配方的且排除已派工的)
 * List<DDZLS> getDDZLS(String DDBH);    // 取得訂單資料(尺寸、數量)
 * List<DDZLS1> getDDZLS1(String DDBH);    // 取得訂單配方
 * List<DDZLS1> getDDZLS1();    // 取得訂單配方(有Group by)
 *
 * List<SMDD> getSMDD(String DDBH); // 取得鞋廠生管派工
 * List<SMDDS> getSMDDS(String DDBH, String Round);   // 導入鞋廠輪次明細資料
 */

@Component
public class DDZLDaoImpl implements DDZLDao {

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得鞋厂订单资料（订单号）(*注意、是已放行的订单*)
    @Override
    public List<String> getDDBHerp(String StarDate, String EndDate) {
        String sqlgetDDBHerp = "SELECT DDZLerp.DDBH " +
                "FROM LBY_ERP.dbo.DDZL DDZLerp " +
                "WHERE DDZLerp.GSBH = 'B07U' " +
                "AND DDZLerp.DDZT = 'Y' " +
                "AND DDZLerp.DDBH NOT IN(SELECT DDBH FROM DDZL) " +
                "AND DDZLerp.DDRQ BETWEEN  :StarDate AND :EndDate ";

        map = new HashMap<>();
        map.put("StarDate", StarDate);
        map.put("EndDate", EndDate);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DDBH");
        List<String> getDDBHerpList = lbyddJdbcTemplate.query(sqlgetDDBHerp, map, rowMapper);

        if (getDDBHerpList.size() > 0) {
            return getDDBHerpList;
        } else {
            return null;
        }
    }

    // 写入订单资料 From ERP
    @Override
    public String DDZLInsert(DDZL ddzl) {
        // 寫入底廠時、把鞋廠的訂單交期改成鞋廠的成型預定上線日(SCZL.PlanDate and SCZL.GXLB = 'A')
        String sqlDDZLInsert = "INSERT INTO DDZL " +
                "(DDBH, CQDH, ZLBH1, DDLB, XieXing, SheHao, ARTICLE, KHDH, KHPO, DDRQ, SCRQ, DDJQ, Pairs, ACCNO, ZLBH, DDZT, YN, CQDH1, USERID, USERDATE, QKBL, ZLBHA) " +
                "(SELECT DDZLerp.DDBH, 'B7U', :DDBH, 'N', DDZLerp.XieXing, DDZLerp.SheHao, DDZLerp.ARTICLE, 'B07U', DDZLerp.KHPO, CONVERT(varchar, DDZLerp.DDRQ, 112), '', CONVERT(varchar, DDZLerp.ShipDate, 112), DDZLerp.Pairs, 'ZZZZZZZZZZ', 'ZZZZZZZZZZZZZZZ', 'Y', '1', 'B7U', 'SUPER', :USERDATE, 100.0, 'ZZZZZZZZZZZZZZZ' " +
                "FROM LBY_ERP.dbo.DDZL DDZLerp " +
                "WHERE DDZLerp.DDBH = :DDBH )";

        map = new HashMap<>();
        map.put("DDBH", ddzl.getDDBH());
        map.put("USERDATE", ddzl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlDDZLInsert, map);
        return null;
    }

    // 写入 Size & 双数 资料 From ERP
    @Override
    public String DDZLSInsert(DDZL ddzl) {
        String sqlDDLSInsert = "INSERT INTO DDZLS " +
                "(DDBH, CQDH, CC, Qty, USERID, USERDATE, xh) " +
                "(SELECT DDZLserp.DDBH, 'B7U', DDZLserp.CC, DDZLserp.Quantity, 'SUPER', :USERDATE, 'T' " +
                "FROM LBY_ERP.dbo.DDZLs DDZLserp " +
                "WHERE DDZLserp.DDBH = :DDBH)";

        map = new HashMap<>();
        map.put("DDBH", ddzl.getDDBH());
        map.put("USERDATE", ddzl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlDDLSInsert, map);
        return null;
    }

    // 写入 部位 & 配方 资料
    @Override
    public void DDZLS1Insert(DDZLS1 ddzls1) {
        String sqlDDLS1Insert = "INSERT INTO DDZLS1 (DDBH, CQDH, xh, cldh, YSSM, KGS, LOSS, USERID, USERDATE, LB) " +
                "VALUES (:DDBH, :CQDH, :xh, :cldh, :YSSM, :KGS, :LOSS, :USERID, :USERDATE, :LB)";

        map = new HashMap<>();
        map.put("DDBH", ddzls1.getDDBH());
        map.put("CQDH", ddzls1.getCQDH());
        map.put("xh", ddzls1.getXh());
        map.put("cldh", ddzls1.getCldh());
        map.put("YSSM", ddzls1.getYSSM());
        map.put("KGS", ddzls1.getKGS());
        map.put("LOSS", ddzls1.getLOSS());
        map.put("USERID", ddzls1.getUSERID());
        map.put("USERDATE", ddzls1.getUSERDATE());
        map.put("LB", ddzls1.getLB());

        lbyddJdbcTemplate.update(sqlDDLS1Insert, map);
    }

    // 刪除 部位 & 配方 资料
    @Override
    public void DDZLS1Delete(String DDBH) {
        String sqlDDZLS1Delete = "DELETE FROM DDZLS1 " +
                "WHERE DDBH = :DDBH ";
        map = new HashMap<>();
        map.put("DDBH", DDBH);

        lbyddJdbcTemplate.update(sqlDDZLS1Delete, map);
    }

    // 取得訂單資料
    @Override
    public List<DDZL> getDDZL(String StarDate, String EndDate) {
        String sqlgetDDZL = "SELECT DDBH, XieXing, SheHao, ARTICLE, DDRQ, SCRQ, DDJQ, Pairs, USERID, USERDATE " +
                "FROM DDZL  " +
                "WHERE CQDH = 'B7U' AND YN = '1' " +
                "AND DDRQ BETWEEN  :StarDate AND :EndDate ";
        map = new HashMap<>();
        map.put("StarDate", StarDate);
        map.put("EndDate", EndDate);

        List<DDZL> getDDZLList = lbyddJdbcTemplate.query(sqlgetDDZL, map, new DDZL2RowMapper());
        if (getDDZLList.size() > 0) {
            return getDDZLList;
        } else {
            return null;
        }
    }
    // 取得訂單資料 by DDBH
    @Override
    public List<DDZL> getDDZL(String DDBH) {
        String sqlgetDDZL = "SELECT DDBH, XieXing, SheHao, ARTICLE, DDRQ, SCRQ, DDJQ, Pairs, USERID, USERDATE " +
                "FROM DDZL  " +
                "WHERE CQDH = 'B7U' AND YN = '1' " +
                "AND DDBH = :DDBH";
        map = new HashMap<>();
        map.put("DDBH", DDBH);

        List<DDZL> getDDZLList = lbyddJdbcTemplate.query(sqlgetDDZL, map, new DDZL2RowMapper());
        if (getDDZLList.size() > 0) {
            return getDDZLList;
        } else {
            return null;
        }
    }

    // 取得订单资料（依XXZLS有配方的且排除已派工的)
    @Override
    public List<DDZL> getDDZLbyXXZLS() {
        String sqlgetDDBHbyXXZLS = "SELECT DDBH, XieXing, SheHao, ARTICLE, DDRQ, SCRQ, DDJQ, Pairs, USERID, USERDATE " +
                "FROM DDZL  " +
                "WHERE YN = '1' " +
                "AND XieXing+SheHao IN (SELECT XieXing+SheHao FROM XXZLS)";

        map = new HashMap<>();
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        // RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DDBH");
        // List<String> getDDBHbyXXZLS = lbyddJdbcTemplate.query(sqlgetDDBHbyXXZLS, map, rowMapper);
        List<DDZL> getDDBHbyXXZLS = lbyddJdbcTemplate.query(sqlgetDDBHbyXXZLS, map, new DDZL2RowMapper());

        if (getDDBHbyXXZLS.size() > 0) {
            return getDDBHbyXXZLS;
        } else {
            return null;
        }
    }

    // 取得訂單資料(尺寸、數量)
    @Override
    public List<DDZLS> getDDZLS(String DDBH) {
        String sqlgetDDZLS = "SELECT DDBH, CQDH, CC, Qty, Price, IPrice, QtySC, QtyCH, USERID, USERDATE, QtyLOSS, xh " +
                "FROM DDZLS " +
                "WHERE DDBH = :DDBH " +
                "ORDER BY CC ";

        map = new HashMap<>();
        map.put("DDBH", DDBH);

        List<DDZLS> getDDZLSList = lbyddJdbcTemplate.query(sqlgetDDZLS, map, new DDZLSRowMapper());
        if (getDDZLSList.size() > 0) {
            return getDDZLSList;
        } else {
            return null;
        }
    }

    // 取得訂單配方
    @Override
    public List<DDZLS1> getDDZLS1(String DDBH) {
        String sqlgetDDZLS1 = "SELECT DDBH, CQDH, xh, cldh, YSSM, KGS, LOSS, USERID, USERDATE, LB " +
                "FROM DDZLS1 " +
                "WHERE DDBH = :DDBH";

        map = new HashMap<>();
        map.put("DDBH", DDBH);

        List<DDZLS1> getDDZLS1List = lbyddJdbcTemplate.query(sqlgetDDZLS1, map, new DDZLS1RowMapper());
        if (getDDZLS1List.size() > 0) {
            return getDDZLS1List;
        } else {
            return null;
        }
    }

    // 取得訂單配方(有Group by)
    @Override
    public List<DDZLS1> getDDZLS1() {
        String sqlgetDDZLS1 = "SELECT 'DDBH' AS DDBH, CLZL.zwpm AS CQDH, 'xh' AS xh, DDZLS1.cldh, 'YSSM' AS YSSM, CLZL.TotKgs as KGS, 0.0 AS LOSS, 'USERID' AS USERID, 'USERDATE' AS USERDATE, 'LB' AS LB " +
                "FROM DDZLS1 " +
                "LEFT JOIN CLZL ON DDZLS1.cldh = CLZL.cldh " +
                "GROUP BY DDZLS1.cldh, CLZL.zwpm, CLZL.TotKgs";

        map = new HashMap<>();

        List<DDZLS1> getDDZLS1List = lbyddJdbcTemplate.query(sqlgetDDZLS1, map, new DDZLS1RowMapper());
        if (getDDZLS1List.size() > 0) {
            return getDDZLS1List;
        } else {
            return null;
        }
    }

    // 取得鞋廠生管派工
    @Override
    public List<SMDD> getSMDD(String DDBH, String dw) {
        // 成型投入(GXLB = B)
        String sqlgetSMDD = "SELECT DDZL.DDBH, SMDD.XH,SMDD.PlanDate, SMDD.Qty, SMDD.DepNO  FROM DDZL " +
                "LEFT JOIN LBY_ERP.dbo.SMDD SMDD on DDZL.DDBH = SMDD.YSBH " +
                "WHERE DDZL.DDBH = :DDBH " +
                "AND SMDD.GXLB = 'B' " +
                "AND SMDD.PlanDate IS NOT NULL ";

        if (dw == "D"){
            // SQL 補足3位數寫法
            sqlgetSMDD += "AND SMDD.YSBH+'-'+RIGHT('000' + CAST(xh AS NVARCHAR(50)),3) NOT IN " +
                    "(SELECT cldh+'-'+PGZLS.CQDH FROM PGZLS WHERE PGZLS.PGDATE LIKE 'D%') " +
                    "ORDER BY SMDD.PlanDate";
        }
        if (dw == "R"){
            sqlgetSMDD += "AND SMDD.YSBH+'-'+RIGHT('000' + CAST(xh AS NVARCHAR(50)),3) NOT IN " +
                    "(SELECT cldh+'-'+PGZLS.CQDH FROM PGZLS WHERE PGZLS.PGDATE LIKE 'R%') " +
                    "ORDER BY SMDD.PlanDate";
        }

        map = new HashMap<>();
        map.put("DDBH", DDBH);

        List<SMDD> getSMDD = lbyddJdbcTemplate.query(sqlgetSMDD, map, new SMDDRowMapper());
        if (getSMDD.size() > 0) {
            return getSMDD;
        } else {
            return null;
        }
    }

    // 導入鞋廠輪次明細資料
    @Override
    public List<SMDDS> getSMDDS(String DDBH, String Round) {
        // 成型投入(GXLB = B)
        String sqlgetSMDDS = "SELECT SMDDS.DDBH, SMDDS.XXCC, SMDDS.Qty, '' AS USERDate, '' AS USERID, '' AS YN " +
                "FROM LBY_ERP.dbo.SMDDS SMDDS " +
                "LEFT JOIN LBY_ERP.dbo.SMDD SMDD ON SMDD.DDBH = SMDDS.DDBH " +
                "WHERE SMDD.YSBH = :DDBH " +
                "AND SMDD.XH = :Round " +
                "AND SMDD.GXLB = 'B'";

        map = new HashMap<>();
        map.put("DDBH", DDBH);
        map.put("Round", Round);

        List<SMDDS> getSMDDS = lbyddJdbcTemplate.query(sqlgetSMDDS, map, new SMDDSRowMapper());
        if (getSMDDS.size() > 0) {
            return getSMDDS;
        } else {
            return null;
        }
    }
}
