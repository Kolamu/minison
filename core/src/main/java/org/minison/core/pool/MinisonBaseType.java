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
    String((byte)0x90, "字符串类型", new StringNodeProcessor()),
    Number((byte)0xa0, "数字类型", new NumberNodeProcessor()),
    Array((byte)0xb0, "列表", new ArrayNodeProcessor()),
    Map((byte)0xc0, "复杂Map类型", new MapNodeProcessor()),
    Object((byte)0xd0, "对象类型", new ObjectNodeProcessor());

    private byte code;
    private String desc;
    private MinisonNodeProcessor processor;
}
