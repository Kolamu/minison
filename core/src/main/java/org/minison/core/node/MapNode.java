package org.minison.core.node;

import org.minison.core.util.MinisonNodeUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 字典类型的Node，主要处理Key不为Object类型的Map
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
public class MapNode extends MinisonNode<Map> {
    private Map<Class, MinisonNode> keyNode;
    private Map<Class, MinisonNode> valueNode;
    private Map inst;

    public MapNode(Map inst) {
        super(inst);
        keyNode = new LinkedHashMap<Class, MinisonNode>();
        valueNode = new HashMap<Class, MinisonNode>();
        for (Object key : inst.keySet()) {
            Object val = inst.get(key);
            if(Objects.isNull(val)) {
                continue;
            }
            keyNode.computeIfAbsent(key.getClass(), k -> MinisonNodeUtils.getMinisonNode(key));
            valueNode.computeIfAbsent(val.getClass(), k -> MinisonNodeUtils.getMinisonNode(val));
        }
    }
}
