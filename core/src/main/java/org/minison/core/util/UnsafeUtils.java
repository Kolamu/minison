package org.minison.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import static org.minison.core.util.ReflectionUtils.getField;

/**
 * Unsafe 实例
 * @author: kolamu
 * @create: 2024/6/16 12:29
 */
public class UnsafeUtils {
    private static final Unsafe UNSAFE = ReflectionUtils.getStaticField(Unsafe.class, "theUnsafe");
    private static Map<String, FieldOffset> OFFSETS = new ConcurrentHashMap<>();
    private static final String KEY_TEMPLATE = "%s$%s";

    public static <T> T getValue(Object inst, String name) {
        if(Objects.isNull(inst)) {
            return null;
        }
        Class clazz = inst.getClass();
        String key = String.format(KEY_TEMPLATE, clazz.getName(), name);
        FieldOffset offset = OFFSETS.computeIfAbsent(key, K -> new FieldOffset(getField(clazz, name)));
        if(offset == null || offset.getOffset() == 0L) {
            return null;
        }
        return offset.get(inst);
    }

    @Getter
    private static class FieldOffset {
        private UnsafeFieldType type;
        private long offset;

        public FieldOffset(Field field) {
            this.offset = UNSAFE.objectFieldOffset(field);
            this.type = UnsafeFieldType.getType(field.getType());
        }

        public <T> T get(Object obj) {
            return type.get(obj, offset);
        }
    }

    @Getter
    @AllArgsConstructor
    private enum UnsafeFieldType {
        Byte(byte.class, UNSAFE::getByte),
        Short(short.class, UNSAFE::getShort),
        Int(int.class, UNSAFE::getInt),
        Long(long.class, UNSAFE::getLong),
        Float(float.class, UNSAFE::getFloat),
        Double(double.class, UNSAFE::getDouble),
        Char(char.class, UNSAFE::getChar),
        Boolean(boolean.class, UNSAFE::getBoolean),
        Object(Object.class, UNSAFE::getObject);
        private Class<?> clazz;
        private BiFunction<Object, Long, Object> fun;
        private static UnsafeFieldType getType(Class<?> clazz) {
            return Arrays.stream(UnsafeFieldType.values())
                    .filter(t -> t.clazz == clazz)
                    .findFirst()
                    .orElse(UnsafeFieldType.Object);
        }

        public <T> T get(Object obj, long offset) {
            return (T) fun.apply(obj, offset);
        }
    }
}
