package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.clzlsl;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成資料(clzlsl & clzlsz 共用)
 * <p>
 * List<clzlsl> getclzlsl(String cldh); // Get Data of clzlsl
 * void insertclzlsl(clzlsl clzlsl);    // Insert Data to clzlsl
 * List<clzlsl> getclzlsz(String cldh); // Get Data of clzlsz
 * void insertclzlsz(clzlsl clzlsl);    // Insert Data to clzlsz
 * void deleteclzlsz(String cldh);  // Delete clzlsz
 */
public interface clzlslDao {

    // Get Data of clzlsl
    List<clzlsl> getclzlsl(String cldh);

    // Insert Data to clzlsl
    void insertclzlsl(clzlsl clzlsl);

    // Get Data of clzlsz
    List<clzlsl> getclzlsz(String cldh);

    // Insert Data to clzlsz
    void insertclzlsz(clzlsl clzlsl);

    // Delete clzlsz
    void deleteclzlsz(String cldh);

}
