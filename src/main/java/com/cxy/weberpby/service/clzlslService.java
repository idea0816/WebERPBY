package com.cxy.weberpby.service;

import com.cxy.weberpby.model.clzlsl;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成資料(clzlsl & clzlsz 共用)
 * <p>
 * List<clzlsl> getclzlsl(String cldh);  // Get Data of clzlsl
 * void insertClzlsl(clzlsl clzlsl);   // Insert clzlsl
 * List<clzlsl> getclzlsz(String cldh);  // Get Data of clzlsz
 * List<clzlsl> getRealclzlsz(String cldh) {}   // Get Real Data of clzlsz
 * void deleteclzlsz(String cldh);  // Delete clzlsz
 */
public interface clzlslService {

    // Get Data of clzlsl
    List<clzlsl> getclzlsl(String cldh);

    // Insert CLZL
    void insertClzlsl(clzlsl clzlsl);

    // Get Data of clzlsz
    List<clzlsl> getclzlsz(String cldh);

    // Get Real Data of clzlsz
    List<clzlsl> getRealclzlsz(String cldh);

    // Delete clzlsz
    void deleteclzlsz(String cldh);
}
