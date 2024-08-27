package org.minison.core.node;

import org.minison.core.pool.MinisonType;
import org.minison.core.pool.NamePool;
import org.minison.core.pool.TypePool;

import java.util.Map;

/**
 * 字典类型的Node，主要处理Key不为Object类型的Map
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
public class MapNodeProcessor extends MinisonNodeProcessor<Map> {
    @Override
    public MinisonType getType(Map inst, NamePool names, TypePool types) {
        return null;
    }

//    public MapNode(Map inst) {
//        super(inst);
//        keyNode = new LinkedHashMap<Class, MinisonNode>();
//        valueNode = new HashMap<Class, MinisonNode>();
//        for (Object key : inst.keySet()) {
//            Object val = inst.get(key);
//            if(Objects.isNull(val)) {
//                continue;
//            }
//            keyNode.computeIfAbsent(key.getClass(), k -> MinisonNodeUtils.getMinisonNode(key));
//            valueNode.computeIfAbsent(val.getClass(), k -> MinisonNodeUtils.getMinisonNode(val));
//        }
//    }
}
