package com.cxy.weberpby.service;

import java.util.Date;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 時間控制
 * <p>
 * String now();    // 現在年月日
 * String nowYM()；   // 現在年月
 * String lastNowYM();  // 現在年月 -1
 * String forWebDate(String webDate); // 轉成Web顯示的日期
 * String date2String(String date2String)  // Date轉String
 */

public interface timeService {

    // 現在年月日
    String now();

    // 現在年月
    String nowYM();

    // 現在年月 -1
    String lastNowYM();

    // 轉成Web顯示的日期
    String forWebDate(String webDate);

    // Date轉String
    String date2String(String date);
}
