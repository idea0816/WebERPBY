package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.lbzlsDao;
import com.cxy.weberpby.model.lbzls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 代号类别明细资料 - 上一层是 lbzl
 *
 * Integer checklbzls();    // 检查类别明细资料是否存在
 * String lbzlsInsert();    // 写入类别明细资料
 */

@Component
public class lbzlsDaoImpl implements lbzlsDao {
    // LBY_ERP
    @Autowired
    @Qualifier("lbyerpJdbcTemplate")
    private NamedParameterJdbcTemplate lbyerpJdbcTemplate;

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 检查类别明细资料是否存在
    @Override
    public Integer checklbzls(String BWBH) {
        String sqlchecklbzls = "SELECT COUNT(*) FROM lbzls WHERE lbdh = :BWBH";

        map = new HashMap<>();
        map.put("BWBH", BWBH);
        Integer getcounts = lbyddJdbcTemplate.queryForObject(sqlchecklbzls, map, Integer.class);
        return getcounts;
    }

    // 写入类别明细资料
    @Override
    public String lbzlsInsert(lbzls lbzls) {
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
}
