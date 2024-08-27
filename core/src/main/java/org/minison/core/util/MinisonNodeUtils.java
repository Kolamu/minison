package org.minison.core.util;

import org.minison.core.MinisonNode;
import org.minison.core.node.*;
import org.minison.core.pool.*;

import java.lang.reflect.Proxy;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * 节点缓存
 * @author: kolamu
 * @create: 2024/6/15 15:45
 */
public class MinisonNodeUtils {
    public static MinisonNode getMinisonNode(Object inst) {
        if(inst == null || inst instanceof Proxy) {
            return null;
        }
        NamePool namePool = new NamePool();
        TypePool typePool = new TypePool();
        MinisonBaseType baseType = getMinisonBaseType(inst);
        MinisonType type = baseType.getProcessor().getType(inst, namePool, typePool);
        typePool.setRootType(type);
        return new MinisonNode(namePool, typePool);
    }

    public static MinisonBaseType getMinisonBaseType(Object inst) {
        Class<?> clazz = inst.getClass();
        if(clazz.isEnum()) {
            return MinisonBaseType.String;
        }
        else if(clazz.isArray()) {
            return MinisonBaseType.Array;
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return MinisonBaseType.Array;
        } else if(Map.class.isAssignableFrom(clazz)) {
            return getMapType((Map)inst);
        } else if(isType(clazz, String_Type_List)) {
            return MinisonBaseType.String;
        } else if(isType(clazz, Number_Type_Lis)) {
            return MinisonBaseType.Number;
        } else {
            return MinisonBaseType.Object;
        }
    }

    private static List<Class> String_Type_List = Arrays.asList(
            StringBuffer.class,
            Character.class,
            char.class,
            StringBuilder.class,
            String.class,
            Date.class,
            java.util.Date.class,
            LocalDate.class,
            Time.class,
            LocalTime.class,
            Calendar.class,
            LocalDateTime.class
    );

    private static List<Class> Number_Type_Lis = Arrays.asList(
            Number.class,
            int.class,
            long.class,
            byte.class,
            short.class,
            float.class,
            double.class,
            Boolean.class,
            boolean.class,
            Timestamp.class
    );

    private static boolean isType(Class<?> clazz, List<Class> types) {
        return types.stream().anyMatch(type -> type.isAssignableFrom(clazz));
    }

    private static MinisonBaseType getMapType(Map inst) {
        if(inst.isEmpty()) {
            return MinisonBaseType.Map;
        }
        for(Object key : inst.keySet()) {
            if(!isType(key.getClass(), String_Type_List) && !isType(key.getClass(), Number_Type_Lis)) {
                return MinisonBaseType.Map;
            }
        }
        return MinisonBaseType.Object;
    }
}
