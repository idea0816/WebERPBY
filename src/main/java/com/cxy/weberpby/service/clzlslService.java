package com.cxy.weberpby.service;

import com.cxy.weberpby.model.clzlsl;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成資料(clzlsl & clzlsz 共用)
 * <p>
 * List<clzlsl> getclzlsl(String cldh);  // Get Data of clzlsl
 * List<clzlsl> getclzlsz(String cldh);  // Get Data of clzlsz
 * void deleteclzlsz(String cldh);  // Delete clzlsz
 */
public interface clzlslService {

    // Get Data of clzlsl
    List<clzlsl> getclzlsl(String cldh);

    // Get Data of clzlsz
    List<clzlsl> getclzlsz(String cldh);

    // Delete clzlsz
    void deleteclzlsz(String cldh);
}
