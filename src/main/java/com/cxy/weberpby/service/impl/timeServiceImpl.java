package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.service.timeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description JDK8 時間控制
 * <p>
 * String now();   // 現在年月日
 * String nowMonth()；   // 現在年月
 * String lastNowYM();  // 現在年月 -1
 */

@Component
public class timeServiceImpl implements timeService {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate nowDate = LocalDate.now();

    // 現在年月日
    @Override
    public String now() {
        return nowDate.format(dateFormat);
    }

    // 現在年月
    @Override
    public String nowYM() {
        return nowDate.format(dateFormat).substring(0, 6);
    }

    // 現在年月 -1
    @Override
    public String lastNowYM() {
        return (nowDate.minusMonths(1)).format(dateFormat).substring(0, 6);
    }
}
