package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.DDZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/9
 * @Description 訂單資料
 */
public class DDZLRowMapper implements RowMapper<DDZL> {
    @Override
    public DDZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        DDZL DDZL = new DDZL();
        DDZL.setDDBH(rs.getString("DDBH"));    // 訂單編號
        DDZL.setCQDH(rs.getString("CQDH"));    // 廠區別
        DDZL.setZLBH1(rs.getString("ZLBH1"));
        DDZL.setDDLB(rs.getString("DDLB"));    // 訂單類別(N=正式訂單.S=樣品訂單.B=補單.O=其他)
        DDZL.setXieXing(rs.getString("XieXing")); // 鞋型
        DDZL.setSheHao(rs.getString("SheHao"));  // 色號
        DDZL.setARTICLE(rs.getString("ARTICLE")); // ARTICLE
        DDZL.setKHDH(rs.getString("KHDH"));    // 客戶代號
        DDZL.setKHPO(rs.getString("KHPO"));    // 客戶PO
        DDZL.setDDRQ(rs.getString("DDRQ"));    // 接單日期
        DDZL.setSCRQ(rs.getString("SCRQ"));    // 生產上線日
        DDZL.setDDJQ(rs.getString("DDJQ"));    // 訂單交期
        DDZL.setPairs(rs.getInt("Pairs"));  // 雙數
        DDZL.setQtySC(rs.getInt("QtySC"));  // 生產雙數
        DDZL.setQtyCH(rs.getInt("QtyCH"));  // 出貨雙數
        DDZL.setTotals(rs.getDouble("Totals"));  // 總金額
        DDZL.setPJZL(rs.getDouble("PJZL"));    // 平均重量
        DDZL.setACCNO(rs.getString("ACCNO"));   // 結帳單號(與ACCZL相關)
        DDZL.setPGNO(rs.getString("PGNO"));    // 派工單號
        DDZL.setZLBH(rs.getString("ZLBH"));    // 鞋型合併製令、預設15個Z
        DDZL.setDDZT(rs.getString("DDZT"));    // 訂單狀態(S=拆單.C=取消.Y=正常.T=轉單)
        DDZL.setYN(rs.getString("YN"));  // 財務訂單狀態(1=未結單.9=已結單.8=取消單.2=在倉庫.4=已報價.5=出貨.6=部份結單)
        DDZL.setCQDH1(rs.getString("CQDH1"));   // 打料廠區
        DDZL.setUSERID(rs.getString("USERID"));
        DDZL.setUSERDATE(rs.getString("USERDATE"));
        DDZL.setDDBZ(rs.getString("DDBZ"));    // 備註
        DDZL.setQKBL(rs.getDouble("QKBL"));    // 請款%
        DDZL.setQtyQk(rs.getInt("QtyQk"));
        DDZL.setZLBHA(rs.getString("ZLBHA"));   // 粗坯用量製令、預設15個Z
        DDZL.setCHRQ(rs.getString("CHRQ"));    // 生產日期(與DDS系列相關)

        return DDZL;
    }


}
