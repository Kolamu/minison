package org.minison.core.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射工具类
 *
 * @author: kolamu
 * @create: 2024/6/15 15:35
 */
public class ReflectionUtils {
    private static final Map<Class, Map<String, Field>> FIELD_CACHE = new ConcurrentHashMap<Class, Map<String, Field>>();

    public static Field getField(Class clazz, String fieldName) {
        return getFields(clazz).get(fieldName);
    }

    public static Map<String, Field> getFields(Class clazz) {
        return FIELD_CACHE.computeIfAbsent(clazz, ReflectionUtils::cacheFields);
    }

    public static <T> T getStaticField(Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static Map<String, Field> cacheFields(Class clazz) {
        Map<String, Field> map = new HashMap<String, Field>();
        Arrays.stream(clazz.getDeclaredFields())
                .forEach(f -> map.put(f.getName(), f));
        Class superClass = clazz.getSuperclass();
        if(superClass != null && clazz != Object.class) {
            Map<String, Field> superMap = getFields(superClass);
            if(superMap != null) {
                map.putAll(superMap);
            }
        }
        if(map.isEmpty()) {
            return null;
        }
        return map;
    }
}
