package org.minison.core.node;

import lombok.Data;
import org.minison.core.pool.MinisonType;
import org.minison.core.pool.NamePool;
import org.minison.core.pool.TypePool;

/**
 * 数组类型节点
 *
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
@Data
public class ArrayNodeProcessor extends MinisonNodeProcessor<Object[]> {
    @Override
    public MinisonType getType(Object[] inst, NamePool names, TypePool types) {
        return null;
    }

//    public ArrayNode(Object[] inst) {
//        super(inst);
//        itemNode = new LinkedHashMap<>();
//        for(int i = 0; i < inst.length; i++){
//            Object item = inst[i];
//            if(Objects.isNull(item) || itemNode.containsKey(item.getClass())) {
//                continue;
//            }
//            itemNode.put(item.getClass(), MinisonNodeUtils.getMinisonNode(item));
//        }
//    }
}
