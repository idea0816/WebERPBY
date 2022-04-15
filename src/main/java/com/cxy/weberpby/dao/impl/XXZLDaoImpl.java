package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.XXZLDao;
import com.cxy.weberpby.mapper.XXZL3RowsMapper;
import com.cxy.weberpby.mapper.XXZLSERPRowMapper;
import com.cxy.weberpby.model.XXZL;
import com.cxy.weberpby.model.XXZLS;
import com.cxy.weberpby.model.XXZLS1;
import com.cxy.weberpby.model.XXZLSERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体資料
 *
 * List<XXZL2Rows> getERPXiexing();  // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
 * List<XXZLSERP> getErpXxzls();    // 取得鞋厂型体部位资料
 * List<XXZL2Rows> getDdXiexing();   // 取得底厂的型体资料(部份)
 * String XXZLInsert(XXZL xxzl);    // 写入型体资料
 * String XXZLS1Insert(XXZLS1 xxzls1);  // 写入 Size & 重量 资料
 * String XXZLSInsert(XXZLS xxzls); // 写入部位 & 配方资料
 */

@Component
public class XXZLDaoImpl implements XXZLDao {
    // LYS_ERP
    // @Autowired
    // @Qualifier("lyserpJdbcTemplate")
    // private NamedParameterJdbcTemplate lyserpJdbcTemplate;

    // LIY_DD
    // @Autowired
    // @Qualifier("liyddJdbcTemplate")
    // private NamedParameterJdbcTemplate liyddJdbcTemplate;

    // LBY_ERP
    @Autowired
    @Qualifier("lbyerpJdbcTemplate")
    private NamedParameterJdbcTemplate lbyerpJdbcTemplate;

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
    @Override
    public List<XXZL> getErpXiexing() {
        String sqlErpXieXing = "SELECT XieXing, SheHao, ARTICLE " +
                "FROM DDZL " +
                "WHERE GSBH = 'B07U' " +
                "AND DDZL.ShipDate > GETDATE() - 30 " +
                "GROUP BY XieXing, SheHao, ARTICLE";

        map = new HashMap<>();
        List<XXZL> getERPXiexing = lbyerpJdbcTemplate.query(sqlErpXieXing, map, new XXZL3RowsMapper());

        if (getERPXiexing.size() > 0) {
            return getERPXiexing;
        } else {
            return null;
        }
    }

    // 取得鞋厂型体部位资料
    @Override
    public List<XXZLSERP> getErpXxzls(XXZL xxzl) {
        String sqlErpXxzls = "SELECT XieXing, SheHao, BWBH, CLBH, CSBH, CCQQ, CCQZ  " +
                "FROM LBY_ERP.dbo.xxzls " +
                "WHERE XieXing = :XieXing " +
                "AND SheHao = :SheHao " +
                "AND CSBH = 'BAO' " +
                // 这里只取材料代号为 J 开头的、需要再验证
                "AND CLBH like 'J%'";
        map = new HashMap<>();
        map.put("XieXing", xxzl.getXieXing());
        map.put("SheHao", xxzl.getSheHao());
        List<XXZLSERP> getErpXxzls = lbyerpJdbcTemplate.query(sqlErpXxzls, map, new XXZLSERPRowMapper());

        if (getErpXxzls.size() > 0) {
            return getErpXxzls;
        } else {
            return null;
        }
    }

    // 取得底厂的型体资料(部份)
    @Override
    public List<XXZL> getDdXiexing() {
        String sqlDdXiexing = "SELECT XieXing, SheHao, ARTICLE " +
                "FROM XXZL " +
                "WHERE CQDH = 'B7U' " +
                "GROUP BY XieXing, SheHao, ARTICLE";

        map = new HashMap<>();
        List<XXZL> getDdXiexing = lbyddJdbcTemplate.query(sqlDdXiexing, map, new XXZL3RowsMapper());

        if (getDdXiexing.size() > 0) {
            return getDdXiexing;
        } else {
            return null;
        }
    }

    // 写入型体资料
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

    // 写入 Size & 重量 资料
    @Override
    public String XXZLS1Insert(XXZLS1 xxzls1) {
        String XXZLS1Insert = "INSERT INTO XXZLS1 (XieXing, SheHao, CQDH, CC, USERID, USERDATE) " +
                "(SELECT xxzls3.XieXing, :SheHao, 'B7U', xxzls3.US_Size, 'SUPER', :USERDATE FROM LBY_ERP.dbo.xxzls3 xxzls3 where xxzls3.XieXing = :XieXing )";

        map = new HashMap<>();
        map.put("XieXing", xxzls1.getXieXing());
        map.put("SheHao", xxzls1.getSheHao());
        map.put("USERDATE", xxzls1.getUSERDATE());

        lbyddJdbcTemplate.update(XXZLS1Insert, map);
        return null;
    }

    // 写入部位 & 配方资料
    @Override
    public String XXZLSInsert(XXZLS xxzls) {
        String XXZLSInsert = "INSERT INTO XXZLS (XieXing, SheHao, CQDH, xh, YSSM, USERID, USERDATE, LB) " +
                "VALUES (:XieXing, :SheHao, 'B7U', :xh, (select zwsm from lbzls where lbdh = :YSSM), 'SUPER', :USERDATE, :LB)";

        map = new HashMap<>();
        map.put("XieXing", xxzls.getXieXing());
        map.put("SheHao", xxzls.getSheHao());
        map.put("xh", xxzls.getXh());
        map.put("YSSM", xxzls.getYSSM());
        map.put("USERDATE", xxzls.getUSERDATE());
        map.put("LB", xxzls.getLB());
        lbyddJdbcTemplate.update(XXZLSInsert, map);
        return null;
    }

    // 錯誤訊息如何回傳？？
}
