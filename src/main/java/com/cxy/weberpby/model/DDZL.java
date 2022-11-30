package com.cxy.weberpby.model;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 訂單基本資料
 */

public class DDZL {
    String DDBH;    // 訂單編號
    String CQDH;    // 廠區別
    String ZLBH1;
    String DDLB;    // 訂單類別(N=正式訂單.S=樣品訂單.B=補單.O=其他)
    String XieXing; // 鞋型
    String SheHao;  // 色號
    String ARTICLE; // ARTICLE
    String KHDH;    // 客戶代號
    String KHPO;    // 客戶PO
    String DDRQ;    // 接單日期
    String SCRQ;    // 生產上線日
    String DDJQ;    // 訂單交期
    Integer Pairs;  // 雙數
    Integer QtySC;  // 生產雙數
    Integer QtyCH;  // 出貨雙數
    Double Totals;  // 總金額
    Double PJZL;    // 平均重量
    String ACCNO;   // 結帳單號(與ACCZL相關)
    String PGNO;    // 派工單號
    String ZLBH;    // 鞋型合併製令、預設15個Z
    String DDZT;    // 訂單狀態(S=拆單.C=取消.Y=正常.T=轉單)
    String YN;  // 財務訂單狀態(1=未結單.9=已結單.8=取消單.2=在倉庫.4=已報價.5=出貨.6=部份結單)
    String CQDH1;   // 打料廠區
    String USERID;
    String USERDATE;
    String DDBZ;    // 備註
    Double QKBL;    // 請款%
    Integer QtyQk;
    String ZLBHA;   // 粗坯用量製令、預設15個Z
    String CHRQ;    // 生產日期(與DDS系列相關)

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getZLBH1() {
        return ZLBH1;
    }

    public void setZLBH1(String ZLBH1) {
        this.ZLBH1 = ZLBH1;
    }

    public String getDDLB() {
        return DDLB;
    }

    public void setDDLB(String DDLB) {
        this.DDLB = DDLB;
    }

    public String getXieXing() {
        return XieXing;
    }

    public void setXieXing(String xieXing) {
        XieXing = xieXing;
    }

    public String getSheHao() {
        return SheHao;
    }

    public void setSheHao(String sheHao) {
        SheHao = sheHao;
    }

    public String getARTICLE() {
        return ARTICLE;
    }

    public void setARTICLE(String ARTICLE) {
        this.ARTICLE = ARTICLE;
    }

    public String getKHDH() {
        return KHDH;
    }

    public void setKHDH(String KHDH) {
        this.KHDH = KHDH;
    }

    public String getKHPO() {
        return KHPO;
    }

    public void setKHPO(String KHPO) {
        this.KHPO = KHPO;
    }

    public String getDDRQ() {
        return DDRQ;
    }

    public void setDDRQ(String DDRQ) {
        this.DDRQ = DDRQ;
    }

    public String getSCRQ() {
        return SCRQ;
    }

    public void setSCRQ(String SCRQ) {
        this.SCRQ = SCRQ;
    }

    public String getDDJQ() {
        return DDJQ;
    }

    public void setDDJQ(String DDJQ) {
        this.DDJQ = DDJQ;
    }

    public Integer getPairs() {
        return Pairs;
    }

    public void setPairs(Integer pairs) {
        Pairs = pairs;
    }

    public Integer getQtySC() {
        return QtySC;
    }

    public void setQtySC(Integer qtySC) {
        QtySC = qtySC;
    }

    public Integer getQtyCH() {
        return QtyCH;
    }

    public void setQtyCH(Integer qtyCH) {
        QtyCH = qtyCH;
    }

    public Double getTotals() {
        return Totals;
    }

    public void setTotals(Double totals) {
        Totals = totals;
    }

    public Double getPJZL() {
        return PJZL;
    }

    public void setPJZL(Double PJZL) {
        this.PJZL = PJZL;
    }

    public String getACCNO() {
        return ACCNO;
    }

    public void setACCNO(String ACCNO) {
        this.ACCNO = ACCNO;
    }

    public String getPGNO() {
        return PGNO;
    }

    public void setPGNO(String PGNO) {
        this.PGNO = PGNO;
    }

    public String getZLBH() {
        return ZLBH;
    }

    public void setZLBH(String ZLBH) {
        this.ZLBH = ZLBH;
    }

    public String getDDZT() {
        return DDZT;
    }

    public void setDDZT(String DDZT) {
        this.DDZT = DDZT;
    }

    public String getYN() {
        return YN;
    }

    public void setYN(String YN) {
        this.YN = YN;
    }

    public String getCQDH1() {
        return CQDH1;
    }

    public void setCQDH1(String CQDH1) {
        this.CQDH1 = CQDH1;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERDATE() {
        return USERDATE;
    }

    public void setUSERDATE(String USERDATE) {
        this.USERDATE = USERDATE;
    }

    public String getDDBZ() {
        return DDBZ;
    }

    public void setDDBZ(String DDBZ) {
        this.DDBZ = DDBZ;
    }

    public Double getQKBL() {
        return QKBL;
    }

    public void setQKBL(Double QKBL) {
        this.QKBL = QKBL;
    }

    public Integer getQtyQk() {
        return QtyQk;
    }

    public void setQtyQk(Integer qtyQk) {
        QtyQk = qtyQk;
    }

    public String getZLBHA() {
        return ZLBHA;
    }

    public void setZLBHA(String ZLBHA) {
        this.ZLBHA = ZLBHA;
    }

    public String getCHRQ() {
        return CHRQ;
    }

    public void setCHRQ(String CHRQ) {
        this.CHRQ = CHRQ;
    }
}
