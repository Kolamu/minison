package org.minison.core.pool;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字符串类型的子类型
 * @author: kolamu
 * @create: 2024/8/3 18:32
 */
@Getter
@AllArgsConstructor
public enum MinisonStringMode implements MinisonMode {
    UTF8(1, "UTF8编码的字符串"),
    Enum(2, "枚举类型"),
    Date(3, "日期"),
    Time(4, "时间"),
    DateTime(5, "日期和时间"),
    Calendar(6, "枚举类型"),
    LocalDateTime(7, "LocalDateTime");

    private int code;
    private String desc;
}
