package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.clzlslDao;
import com.cxy.weberpby.mapper.clzlslRowMapper;
import com.cxy.weberpby.model.clzlsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成資料(clzlsl & clzlsz 共用)
 * <p>
 * List<clzlsl> getclzlsl(String cldh); // Get Data of clzlsl
 * List<clzlsl> getclzlsz(String cldh); // Get Data of clzlsz
 * void insertclzlsz(clzlsl clzlsl);    // Insert Data to clzlsz
 * void deleteclzlsz(String cldh);  // Delete clzlsz
 */

@Component
public class clzlslDaoImpl implements clzlslDao {
    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map = new HashMap<>();

    // Get Data of clzlsl
    @Override
    public List<clzlsl> getclzlsl(String cldh) {
        String sqlgetclzlsl = "SELECT cldh, lb, cldhz, clyl, phr, cldj, USERID, USERDATE " +
                "FROM clzlsl  " +
                "WHERE cldh = :cldh ORDER BY lb, cldhz ";

        map.put("cldh", cldh);
        List<clzlsl> getclzlslList = lbyddJdbcTemplate.query(sqlgetclzlsl, map, new clzlslRowMapper());
        if (getclzlslList.size() > 0) {
            return getclzlslList;
        } else {
            return null;
        }
    }

    // Get Data of clzlsz
    @Override
    public List<clzlsl> getclzlsz(String cldh) {
        String sqlgetclzlsz = "SELECT cldh, lb, cldhz, clyl, phr, cldj, USERID, USERDATE " +
                "FROM clzlsz  " +
                "WHERE cldh = :cldh ORDER BY lb, cldhz ";

        map.put("cldh", cldh);
        List<clzlsl> getclzlszList = lbyddJdbcTemplate.query(sqlgetclzlsz, map, new clzlslRowMapper());
        if (getclzlszList.size() > 0) {
            return getclzlszList;
        } else {
            return null;
        }
    }

    // Insert Data to clzlsz

    @Override
    public void insertclzlsz(clzlsl clzlsl) {
        String sqlinsertclzlsz = "INSERT INTO clzlsz " +
                "VALUES (:cldh, :lb, :cldhz, :clyl, :phr, :cldj, :USERID, :USERDATE)";
        map = new HashMap<>();
        map.put("cldh", clzlsl.getCldh());
        map.put("lb", clzlsl.getLb());
        map.put("cldhz", clzlsl.getCldhz());
        map.put("clyl", clzlsl.getClyl());
        map.put("phr", clzlsl.getPhr());
        map.put("cldj", clzlsl.getCldj());
        map.put("USERID", clzlsl.getUSERID());
        map.put("USERDATE", clzlsl.getUSERDATE());

        lbyddJdbcTemplate.update(sqlinsertclzlsz, map);
    }

    // Delete clzlsz
    @Override
    public void deleteclzlsz(String cldh) {
        String sqldeleteclzlsz = "DELETE FROM clzlsz WHERE cldh = :cldh";
        map = new HashMap<>();
        map.put("cldh", cldh);

        lbyddJdbcTemplate.update(sqldeleteclzlsz, map);
    }
}
