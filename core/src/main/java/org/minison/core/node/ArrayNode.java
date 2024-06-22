package org.minison.core.node;

import lombok.Data;

import java.nio.ByteBuffer;

/**
 * 数组类型节点
 *
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
@Data
public class ArrayNode implements MinisonNode {
    private int length;
    private MinisonNode type;

    @Override
    public void setValue(Object value) {

    }

    @Override
    public Object getValue() {
        return null;
    }
}
