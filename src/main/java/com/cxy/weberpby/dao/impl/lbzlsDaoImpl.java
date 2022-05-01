package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.lbzlsDao;
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
 * Integer checkBwlbzls(String BWBH);    // 检查部位类别明细资料是否存在
 * String lbzlsBwInsert();    // 写入部位类别明细资料
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

    // 检查部位类别明细资料是否存在
    @Override
    public Integer checkBwlbzls(String BWBH) {
        String sqlchecklbzls = "SELECT COUNT(*) FROM lbzls WHERE lbdh = :BWBH";

        map = new HashMap<>();
        map.put("BWBH", BWBH);
        return lbyddJdbcTemplate.queryForObject(sqlchecklbzls, map, Integer.class);
    }

    // 写入部位类别明细资料
    @Override
    public String lbzlsBwInsert(lbzls lbzls) {
        String sqllbzlsInsert = "INSERT INTO lbzls (lb, lbdh, zwsm, ywsm, bz, bz1,USERID, USERDATE) " +
                "(SELECT :lb, bwzl.bwdh, bwzl.zwsm, bwzl.ywsm, :bz, :bz1, 'SUPER', :USERDATE " +
                "FROM LBY_ERP.dbo.bwzl bwzl " +
                "where bwzl.bwdh = :BWDH)";

        map = new HashMap<>();
        map.put("lb", lbzls.getLb());
        map.put("BWDH", lbzls.getLbdh());
        map.put("bz", lbzls.getBz());
        map.put("bz1", lbzls.getBz1());
        map.put("USERDATE", lbzls.getUSERDATE());

        lbyddJdbcTemplate.update(sqllbzlsInsert, map);
        return null;
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
                "VALUES (:lb,:lbdh, :zwsm, :ywsm, :bz, :bz1, :USERID, :USERDATE) ";
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
