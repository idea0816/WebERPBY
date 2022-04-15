package com.cxy.weberpby.service;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 時間控制
 * <p>
 * String now();    // 現在年月日
 * String nowYM()；   // 現在年月
 * String lastNowYM();  // 現在年月 -1
 */

public interface timeService {

    // 現在年月日
    String now();

    // 現在年月
    String nowYM();

    // 現在年月 -1
    String lastNowYM();

}
