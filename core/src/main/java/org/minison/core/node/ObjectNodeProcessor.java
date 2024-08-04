package org.minison.core.node;

import lombok.Getter;
import org.minison.core.pool.MinisonType;
import org.minison.core.pool.NamePool;
import org.minison.core.pool.TypePool;
import org.minison.core.util.ReflectionUtils;
import org.minison.core.util.UnsafeUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Object 节点
 *
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
@Getter
public class ObjectNodeProcessor extends MinisonNodeProcessor {
    @Override
    public MinisonType getType(Object inst, NamePool names, TypePool types) {
        if (inst instanceof Map) {
            return buildMap((Map) inst, names, types);
        } else {
            return buildObject(inst, names, types);
        }
    }

    private MinisonType buildObject(Object inst, NamePool names, TypePool types) {
        Map<String, Field> map = ReflectionUtils.getFields(inst.getClass());
        Map<String, Object> instMap = new HashMap<>();
        for (String key : map.keySet()) {
            Object value = UnsafeUtils.getValue(inst, key);
            if (Objects.isNull(value)) {
                continue;
            }
            instMap.put(key, value);
        }
        return new MinisonType(inst.getClass().getName(), getItems(instMap, names, types));
    }

    private MinisonType buildMap(Map inst, NamePool names, TypePool types) {
        Map<String, Object> instMap = new HashMap<>();
        for (Object key : inst.keySet()) {
            Object value = inst.get(key);
            instMap.put(key.toString(), value);
        }

        return new MinisonType(inst.getClass().getName(), getItems(instMap, names, types));
    }
}
