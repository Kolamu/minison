package org.minison.core.pool;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数字类型的子类型
 * @author: kolamu
 * @create: 2024/8/3 17:53
 */
@Getter
@AllArgsConstructor
public enum MinisonNumberMode implements MinisonMode {
    Variant(1, "变长整形"),
    Zigzag(2, "Zigzag编码整形"),
    Bool(3, "布尔值"),
    Float(4, "单精度浮点型"),
    Double(5, "双精度浮点型"),
    Long(6, "长整型");

    private int code;
    private String desc;
}
