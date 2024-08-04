package org.minison.core.pool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.minison.core.node.*;

/**
 * 基础数据类型
 * @author: kolamu
 * @create: 2024/7/17 22:29
 */
@Getter
@AllArgsConstructor
public enum MinisonBaseType {
    String(1, "字符串类型", new StringNodeProcessor()),
    Number(2, "数字类型", new NumberNodeProcessor()),
    Array(3, "列表", new ArrayNodeProcessor()),
    Map(4, "复杂Map类型", new MapNodeProcessor()),
    Object(5, "对象类型", new ObjectNodeProcessor());

    private int code;
    private String desc;
    private MinisonNodeProcessor processor;
}
