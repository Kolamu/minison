package org.minison.core.util;

import org.minison.core.node.*;

import java.lang.reflect.Proxy;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        Class<?> clazz = inst.getClass();
        MinisonNode node = null;
        if(clazz.isArray() || Collection.class.isAssignableFrom(clazz)) {
            node = new ArrayNode();
        } else if(Map.class.isAssignableFrom(clazz)) {
            node = newMapNode((Map)inst);
        } else if(isType(clazz, String_Type_List)) {
            node = new StringNode();
        } else if(isType(clazz, Number_Type_Lis)) {
            node = new NumberNode();
        } else {
            node = new ObjectNode();
        }
        node.setValue(Collection.class.isAssignableFrom(clazz) ? ((Collection)inst).toArray() : inst);
        return node;
    }

    private static List<Class> String_Type_List = Arrays.asList(
            StringBuffer.class,
            Character.class,
            char.class,
            StringBuilder.class,
            String.class,
            Date.class,
            java.util.Date.class,
            Time.class,
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

    private static MinisonNode newMapNode(Map inst) {
        if(inst.isEmpty()) {
            return new MapNode();
        }
        for(Object key : inst.keySet()) {
            if(!isType(key.getClass(), String_Type_List) && isType(key.getClass(), Number_Type_Lis)) {
                return new MapNode();
            }
        }
        return new ObjectNode();
    }
}
