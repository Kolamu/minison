package org.minison.core.node;

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
    Float(3, "浮点型"),
    Array(4, "列表"),
    Map(5, "复杂Map类型"),
    Object(6, "对象类型");
    private int code;
    private String desc;
}
