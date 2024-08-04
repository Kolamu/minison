package org.minison.core.node;

import org.minison.core.pool.MinisonBaseType;
import org.minison.core.pool.MinisonType;
import org.minison.core.pool.NamePool;
import org.minison.core.pool.TypePool;
import org.minison.core.util.MinisonNodeUtils;

import java.util.Map;

/**
 * TODO
 *
 * @author: kolamu
 * @create: 2024/4/21 12:25
 */
public abstract class MinisonNodeProcessor<T> {
    public abstract MinisonType getType(T inst, NamePool names, TypePool types);

    protected int[] getItems(Map<String, Object> inst, NamePool names, TypePool types) {
        int[] items = new int[inst.size() * 3];
        int i = 0;
        for(String key : inst.keySet()) {
            Object value = inst.get(key);
            items[i*3] = names.addName(key);
            MinisonBaseType baseType = MinisonNodeUtils.getMinisonBaseType(value);
            items[i*3 + 1] = baseType.getCode();
            items[i*3 + 2] = types.addType(baseType.getProcessor().getType(value, names, types));
            i++;
        }
        return items;
    }
}
