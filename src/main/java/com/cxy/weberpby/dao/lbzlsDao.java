package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.lbzls;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 代号类别明细资料 - 上一层是 lbzl
 *
 * Integer checklbzls(String BWBH);    // 检查类别明细资料是否存在
 * String lbzlsInsert();    // 写入类别明细资料
 */

public interface lbzlsDao {

    // 检查类别明细资料是否存在
    Integer checklbzls(String BWBH);

    // 写入类别资料
    String lbzlsInsert(lbzls lbzls);
}
