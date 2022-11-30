package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.bwBuildDao;
import com.cxy.weberpby.mapper.bwBuild2RowMapper;
import com.cxy.weberpby.mapper.bwBuildRowMapper;
import com.cxy.weberpby.model.bwBuild;
import com.cxy.weberpby.model.bwBuild2;
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
 * @version Create Time: 2022/5/6
 * @Description 部位规格建立
 * <p>
 * List<bwBuild> getAllXB(String bwlb, String lbdh); // 取得有同部位所有資料(XXZLS1_B)
 * List<bwBuild2> getAllXBB(String NO); // 取得配方&重量資料(XXZLS1_BB)
 * List<bwBuild> getBMC(String NO); // 部位B模具M顏色C對照
 * List<bwBuild2> getP(String NO); // 配方P
 * List<bwBuild2> getSG(String NO, String cldh, String YSSM); // Size,Gram
 * List<bwBuild> getList(String NO);    // 標準配方組成明細
 * List<String> getVersion(String NO);    // 標準部位項次號
 * void insertXXZLS1_B(List<bwBuild> insertBwBuild); //insertXXZLS1_B
 * void insertXXZLS1_BB(List<bwBuild2> insertXXZLS1_BB); //insertXXZLS1_BB
 * void deleteXXZLS1_B(String NO); //deleteXXZLS1_B
 * void deleteXXZLS1_BB(bwBuild2 deleteXXZLS1_BB);  // Delete XXZLS1_BB
 * void updateXXZLS1_B(bwBuild updateBwBuild); // update XXZLS1_B
 */
@Component
public class bwBuildDaoImpl implements bwBuildDao {
    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得有同部位所有資料(XXZLS1_B)
    @Override
    public List<bwBuild> getAllXB(String bwlb, String lbdh) {
        String sqlgetAllXB = "SELECT NO, bwlb, lbdh, mjbh, hw, width, thickness, YSSM " +
                "FROM XXZLS1_B " +
                "WHERE bwlb = :bwlb AND lbdh = :lbdh ";

        map = new HashMap<>();
        map.put("bwlb", bwlb);
        map.put("lbdh", lbdh);
        List<bwBuild> getAllXB = lbyddJdbcTemplate.query(sqlgetAllXB, map, new bwBuildRowMapper());

        if (getAllXB.size() > 0) {
            return getAllXB;
        } else {
            return null;
        }
    }

    // 取得配方&重量資料(XXZLS1_BB)
    @Override
    public List<bwBuild2> getAllXBB(String NO) {
        String sqlgetAllXBB = "SELECT NO, YSSM, cldh, CC, G01, G02 " +
                "FROM XXZLS1_BB " +
                "WHERE NO = :NO " +
                "ORDER BY CC";

        map = new HashMap<>();
        map.put("NO", NO);
        List<bwBuild2> getAllXBB = lbyddJdbcTemplate.query(sqlgetAllXBB, map, new bwBuild2RowMapper());

        if (getAllXBB.size() > 0) {
            return getAllXBB;
        } else {
            return null;
        }
    }

    // 部位B模具M顏色C對照
    @Override
    public List<bwBuild> getBMC(String NO) {
        String sqlgetBMC = "SELECT NO, NULL as bwlb, NULL as lbdh, mjbh, hw, width, thickness, YSSM " +
                "FROM XXZLS1_B WHERE No LIKE :NO " +
                "GROUP BY NO, mjbh, hw, width, thickness, YSSM";
        map = new HashMap<>();
        map.put("NO", NO);
        List<bwBuild> getList = lbyddJdbcTemplate.query(sqlgetBMC, map, new bwBuildRowMapper());

        if (getList.size() > 0) {
            return getList;
        } else {
            return null;
        }
    }

    // 配方P
    @Override
    public List<bwBuild2> getP(String NO) {
        String sqlgetP = "SELECT NO, YSSM, cldh, NULL as CC , NULL as G01, NULL as G02 " +
                "FROM XXZLS1_BB WHERE No LIKE :NO " +
                "GROUP BY NO, YSSM, cldh";
        map = new HashMap<>();
        map.put("NO", NO);
        List<bwBuild2> getPList = lbyddJdbcTemplate.query(sqlgetP, map, new bwBuild2RowMapper());

        if (getPList.size() > 0) {
            return getPList;
        } else {
            return null;
        }
    }

    // Size,Gram
    @Override
    public List<bwBuild2> getSG(String NO, String cldh, String YSSM) {
        String sqlgetSG = "SELECT NO, YSSM, cldh, CC, G01, G02 " +
                "FROM XXZLS1_BB WHERE No LIKE :NO AND cldh = :cldh AND YSSM = :YSSM ORDER BY CC";
        map = new HashMap<>();
        map.put("NO", NO);
        map.put("cldh", cldh);
        map.put("YSSM", YSSM);
        List<bwBuild2> getSGList = lbyddJdbcTemplate.query(sqlgetSG, map, new bwBuild2RowMapper());

        if (getSGList.size() > 0) {
            return getSGList;
        } else {
            return null;
        }
    }

    // 標準配方組成明細
    @Override
    public List<bwBuild> getList(String NO) {
        String sqlgetList = "SELECT NO, bwlb, lbdh, mjbh, YSSM, CC, G01 " +
                "FROM XXZLS1_B WHERE No LIKE :NO " +
                "ORDER BY CC";
        map = new HashMap<>();
        map.put("NO", NO);

        List<bwBuild> getList = lbyddJdbcTemplate.query(sqlgetList, map, new bwBuildRowMapper());

        if (getList.size() > 0) {
            return getList;
        } else {
            return null;
        }
    }

    // 標準部位項次號
    @Override
    public List<String> getVersion(String NO) {
        String sqlgetVersion = "SELECT Top 1 NO " +
                "FROM XXZLS1_B WHERE No LIKE :NO ORDER BY NO DESC";

        map = new HashMap<>();
        map.put("NO", NO);

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("NO");
        return lbyddJdbcTemplate.query(sqlgetVersion, map, rowMapper);

        //JDBC Template
        // queryForObject方法默认返回值不为空，如果可以肯定结果集不为空可以不做处理，否则需要用 try……catch代码块进行异常的捕获与处理。不处理会报错
        // return jdbcTemplate.queryForObject(sqlgetVersion, new String.class);
    }

    //insertXXZLS1_B
    @Override
    public void insertXXZLS1_B(bwBuild insertBwBuild) {
        String sqlinserbw = "INSERT INTO XXZLS1_B " +
                "VALUES (:NO, :bwlb, :lbdh, :mjbh, :hw, :width, :thickness, :YSSM) ";
        map = new HashMap<>();
        map.put("NO", insertBwBuild.getNO());
        map.put("bwlb", insertBwBuild.getBwlb());
        map.put("lbdh", insertBwBuild.getLbdh());
        map.put("mjbh", insertBwBuild.getMjbh());
        map.put("hw", insertBwBuild.getHw());
        map.put("width", insertBwBuild.getWidth());
        map.put("thickness", insertBwBuild.getThickness());
        map.put("YSSM", insertBwBuild.getYSSM());

        lbyddJdbcTemplate.update(sqlinserbw, map);
    }

    //insertXXZLS1_BB
    @Override
    public void insertXXZLS1_BB(bwBuild2 insertXXZLS1BB) {
        String sqlinsersgl = "INSERT INTO XXZLS1_BB " +
                "VALUES (:NO, :YSSM, :cldh, :CC, :G01, :G02) ";
        map = new HashMap<>();
        map.put("NO", insertXXZLS1BB.getNO());
        map.put("YSSM", insertXXZLS1BB.getYSSM());
        map.put("cldh", insertXXZLS1BB.getCldh());
        map.put("CC", insertXXZLS1BB.getCC());
        map.put("G01", insertXXZLS1BB.getG01());
        map.put("G02", insertXXZLS1BB.getG02());

        lbyddJdbcTemplate.update(sqlinsersgl, map);
    }

    //deleteXXZLS1_B
    @Override
    public void deleteXXZLS1_B(String NO) {
        String sqldelbw = "DELETE FROM XXZLS1_B WHERE NO = :NO";
        map = new HashMap<>();
        map.put("NO", NO);

        lbyddJdbcTemplate.update(sqldelbw, map);
    }

    // Delete XXZLS1_BB
    @Override
    public void deleteXXZLS1_BB(bwBuild2 deleteXXZLS1_BB) {
        String sqldeleteXXZLS1_BB = "DELETE FROM XXZLS1_BB " +
                "WHERE 1=1 ";

        map = new HashMap<>();

        if (deleteXXZLS1_BB.getNO() != null) {
            sqldeleteXXZLS1_BB = sqldeleteXXZLS1_BB + " AND NO = :NO ";
            map.put("NO", deleteXXZLS1_BB.getNO());
        }
        if (deleteXXZLS1_BB.getYSSM() != null) {
            sqldeleteXXZLS1_BB = sqldeleteXXZLS1_BB + " AND YSSM = :YSSM ";
            map.put("YSSM", deleteXXZLS1_BB.getYSSM());
        }
        if (deleteXXZLS1_BB.getCldh() != null) {
            sqldeleteXXZLS1_BB = sqldeleteXXZLS1_BB + " AND cldh = :cldh ";
            map.put("cldh", deleteXXZLS1_BB.getCldh());
        }

        lbyddJdbcTemplate.update(sqldeleteXXZLS1_BB, map);
    }

    // update XXZLS1_B
    @Override
    public void updateXXZLS1_B(bwBuild updateBwBuild) {
        String sqlupdateXXZLS1_B = "UPDATE XXZLS1_B " +
                "SET mjbh = :mjbh, hw = :hw, width= :width, thickness = :thickness, YSSM = :YSSM " +
                "WHERE NO = :NO";
        map = new HashMap<>();
        map.put("NO", updateBwBuild.getNO());
        map.put("mjbh", updateBwBuild.getMjbh());
        map.put("hw", updateBwBuild.getHw());
        map.put("width", updateBwBuild.getWidth());
        map.put("thickness", updateBwBuild.getThickness());
        map.put("YSSM", updateBwBuild.getYSSM());

        lbyddJdbcTemplate.update(sqlupdateXXZLS1_B, map);


    }
}
