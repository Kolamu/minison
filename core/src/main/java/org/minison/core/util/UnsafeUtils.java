package org.minison.core.util;

import sun.misc.Unsafe;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.minison.core.util.ReflectionUtils.getField;

/**
 * Unsafe 实例
 * @author: kolamu
 * @create: 2024/6/16 12:29
 */
public class UnsafeUtils {
    private static final Unsafe UNSAFE = ReflectionUtils.getStaticField(Unsafe.class, "theUnsafe");
    private static Map<String, Long> OFFSETS = new ConcurrentHashMap<>();
    private static final String KEY_TEMPLATE = "%s$%s";

    public static <T> T getValue(Object inst, String name) {
        if(Objects.isNull(inst)) {
            return null;
        }
        Class clazz = inst.getClass();
        String key = String.format(KEY_TEMPLATE, clazz.getName(), name);
        long offset = OFFSETS.computeIfAbsent(key, K -> UNSAFE.objectFieldOffset(getField(clazz, name)));
        if(offset == 0L) {
            return null;
        }
        return (T) UNSAFE.getObject(inst, offset);
    }
}
