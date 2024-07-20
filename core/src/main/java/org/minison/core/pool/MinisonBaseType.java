package org.minison.core.pool;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 基础数据类型
 * @author: kolamu
 * @create: 2024/7/17 22:29
 */
@Getter
@AllArgsConstructor
public enum MinisonBaseType {
    String(1, "字符串类型"),
    Number(2, "数字类型"),
    Array(3, "列表"),
    Map(4, "复杂Map类型"),
    Object(5, "对象类型");
    private int code;
    private String desc;
}
