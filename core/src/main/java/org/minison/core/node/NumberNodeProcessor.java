package org.minison.core.node;

import org.minison.core.pool.*;

/**
 * @description: TODO
 * @author: kolamu
 * @create: 2024/5/25 17:29
 */
public class NumberNodeProcessor extends MinisonNodeProcessor {
    @Override
    public MinisonType getType(Object inst, NamePool names, TypePool types) {
        MinisonNumberMode mode = MinisonNumberMode.Variant;
        Class<?> clazz = inst.getClass();
        if (clazz == Double.class || clazz == double.class) {
            mode = MinisonNumberMode.Double;
        }
        else if (clazz == Float.class || clazz == float.class) {
            mode = MinisonNumberMode.Float;
        }
        else if (clazz == Boolean.class || clazz == boolean.class) {
            mode = MinisonNumberMode.Bool;
        }
        else if (((long)inst) < 0 ) {
            mode = MinisonNumberMode.Zigzag;
        }
        return new MinisonType((byte)(mode.getCode() | MinisonBaseType.Number.getCode()));
    }
}
