package com.woowacourse.edd.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getFormedDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        return now.format(formatter);
    }
}
