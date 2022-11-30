package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.lbzlsDao;
import com.cxy.weberpby.mapper.bwRowMapper;
import com.cxy.weberpby.mapper.lbzlRowMapper;
import com.cxy.weberpby.mapper.lbzlsRowMapper;
import com.cxy.weberpby.model.lbzl;
import com.cxy.weberpby.model.lbzls;
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
 * @Description 代号类别明细资料 - 上一层是 lbzl
 * <p>
 * List<lbzls> get2BW(); // 取得鞋廠部位(編號、名稱)
 * List<lbzls> get2BWbw(String lb); // 取得底廠部位(編號、名稱)
 * List<lbzls> getBW(String bwbh); // 取得鞋廠部位資料
 * List<lbzls> getlbzlList();   // 取得类别lbzl資料
 * List<lbzls> getlbzlsList();  // 取得类别明细lbzls明细资料
 * void insertlbzl(lbzl lbzl);   //新增lbzl資料
 * void updatelbzl(lbzl lbzl); //修改lbzl資料
 * void deletelbzl(String lb);   // 刪除lbzl資料
 * void insertlbzls(lbzls lbzls);   //新增lbzls資料
 * void deletelbzls(String lb);   // 刪除lbzls資料
 */

@Component
public class lbzlsDaoImpl implements lbzlsDao {
    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得鞋廠部位(編號、名稱)
    @Override
    public List<lbzls> get2BW() {
        String sqlget2BW = "SELECT xxzls.BWBH as lbdh, bwzl.zwsm FROM LBY_ERP.dbo.xxzls xxzls, LBY_ERP.dbo.bwzl bwzl " +
                "WHERE xxzls.CSBH = 'BAO' AND xxzls.CLBH LIKE 'J%' AND xxzls.BWBH = bwzl.bwdh " +
                "GROUP BY xxzls.BWBH, bwzl.zwsm ORDER BY bwzl.zwsm ";
        map = new HashMap<>();
        List<lbzls> getl2BWList = lbyddJdbcTemplate.query(sqlget2BW, map, new bwRowMapper());

        if (getl2BWList.size() > 0) {
            return getl2BWList;
        } else {
            return null;
        }
    }

    // 取得底廠部位(編號、名稱)
    @Override
    public List<lbzls> get2BWbw(String lb) {
        String sqlget2BWbw = "SELECT XB.lbdh, bwzl.zwsm FROM " +
                "XXZLS1_B XB, LBY_ERP.dbo.bwzl bwzl " +
                "WHERE XB.bwlb = :lb AND XB.lbdh = bwzl.bwdh " +
                "GROUP BY XB.lbdh, bwzl.zwsm";
        map = new HashMap<>();
        map.put("lb", lb);
        List<lbzls> getl2BWbw = lbyddJdbcTemplate.query(sqlget2BWbw, map, new bwRowMapper());

        if (getl2BWbw.size() > 0) {
            return getl2BWbw;
        } else {
            return null;
        }
    }

    // 取得鞋廠部位資料
    @Override
    public List<lbzls> getBW(String bwbh) {
        String sqlgetBW = "SELECT TOP 1 'lb' as lb, BWBH as lbdh, bwzl.zwsm, bwzl.ywsm, xxzls.CCQQ as bz, xxzls.CCQZ as bz1, 'USERID' as USERID, 'USERDATE' as USERDATE " +
                "FROM LBY_ERP.dbo.xxzls xxzls, LBY_ERP.dbo.bwzl bwzl " +
                "WHERE xxzls.bwbh = :bwbh AND xxzls.BWBH = bwzl.bwdh";
        map = new HashMap<>();
        map.put("bwbh", bwbh);

        List<lbzls> getlBWList = lbyddJdbcTemplate.query(sqlgetBW, map, new lbzlsRowMapper());

        if (getlBWList.size() > 0) {
            return getlBWList;
        } else {
            return null;
        }
    }

    // 取得类别lbzl資料
    @Override
    public List<lbzl> getlbzlList() {
        String sqlgetlbzl = "SELECT lb, zwsm, ywsm, bz, USERID, USERDATE " +
                "FROM lbzl ";
        map = new HashMap<>();

        List<lbzl> getlbzlList = lbyddJdbcTemplate.query(sqlgetlbzl, map, new lbzlRowMapper());

        if (getlbzlList.size() > 0) {
            return getlbzlList;
        } else {
            return null;
        }
    }

    // 取得类别明细lbzls资料
    @Override
    public List<lbzls> getlbzlsList(String lb) {
        String sqllbzlsList = "SELECT lb, lbdh, zwsm, ywsm, bz, bz1, USERID, USERDATE " +
                "FROM lbzls " +
                "WHERE lb = :lb ";
        map = new HashMap<>();
        map.put("lb", lb);

        List<lbzls> getlbzlsList = lbyddJdbcTemplate.query(sqllbzlsList, map, new lbzlsRowMapper());

        if (getlbzlsList.size() > 0) {
            return getlbzlsList;
        } else {
            return null;
        }
    }

    //新增lbzl資料
    @Override
    public void insertlbzl(lbzl lbzl) {
        String sqlinsertlbzl = "INSERT INTO lbzl " +
                "VALUES (:lb, :zwsm, :ywsm, :bz, :USERID, :USERDATE) ";
        map = new HashMap<>();
        map.put("lb", lbzl.getLb());
        map.put("zwsm", lbzl.getZwsm());
        map.put("ywsm", lbzl.getYwsm());
        map.put("bz", lbzl.getBz());
        map.put("USERID", lbzl.getUSERID());
        map.put("USERDATE", lbzl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinsertlbzl, map);
    }

    //修改lbzl資料
    @Override
    public void updatelbzl(lbzl lbzl) {
        String sqlupdatelbzl = "UPDATE lbzl " +
                "SET zwsm = :zwsm, bz= :bz,USERID = :USERID, USERDATE = :USERDATE " +
                "WHERE lb = :lb";
        map = new HashMap<>();
        map.put("lb", lbzl.getLb());
        map.put("zwsm", lbzl.getZwsm());
        map.put("bz", lbzl.getBz());
        map.put("USERID", lbzl.getUSERID());
        map.put("USERDATE", lbzl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlupdatelbzl, map);
    }

    // 刪除lbzl資料
    @Override
    public void deletelbzl(String lb) {
        String sqldellbzl = "DELETE FROM lbzl WHERE lb = :lb";
        map = new HashMap<>();
        map.put("lb", lb);

        lbyddJdbcTemplate.update(sqldellbzl, map);
    }

    //新增lbzls資料
    @Override
    public void insertlbzls(lbzls lbzls) {
        String sqlinsertlbzls = "INSERT INTO lbzls " +
                "VALUES (:lb, :lbdh, :zwsm, :ywsm, :bz, :bz1, :USERID, :USERDATE) ";
        map = new HashMap<>();
        map.put("lb", lbzls.getLb());
        map.put("lbdh", lbzls.getLbdh());
        map.put("zwsm", lbzls.getZwsm());
        map.put("ywsm", lbzls.getYwsm());
        map.put("bz", lbzls.getBz());
        map.put("bz1", lbzls.getBz1());
        map.put("USERID", lbzls.getUSERID());
        map.put("USERDATE", lbzls.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinsertlbzls, map);
    }

    // 刪除lbzls資料
    @Override
    public void deletelbzls(String lb) {
        String sqldellbzls = "DELETE FROM lbzls WHERE lb = :lb";
        map = new HashMap<>();
        map.put("lb", lb);

        lbyddJdbcTemplate.update(sqldellbzls, map);
    }
}
