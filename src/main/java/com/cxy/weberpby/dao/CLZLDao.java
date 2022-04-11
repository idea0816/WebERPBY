package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.CLZL;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 *
 * List<CLZL> getAllVofCLZL();   // Get All V of CLZL
 */

public interface CLZLDao {

    // Get All V of CLZL // 传回的值要考虑 NULL 的状态吗？
    List<CLZL> getAllVofCLZL();
}
