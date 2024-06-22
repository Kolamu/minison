package org.minison.core.node;

import java.nio.ByteBuffer;

/**
 * 字典类型的Node，主要处理Key不为Object类型的Map
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
public class MapNode implements MinisonNode {
    private MinisonNode keyNode;
    private MinisonNode valueNode;

    @Override
    public void setValue(Object value) {

    }

    @Override
    public Object getValue() {
        return null;
    }
}
