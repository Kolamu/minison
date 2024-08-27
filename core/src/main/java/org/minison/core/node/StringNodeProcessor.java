package org.minison.core.node;

import org.minison.core.pool.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串类型的节点
 * @author: kolamu
 * @create: 2024/4/21 12:26
 */
public class StringNodeProcessor extends MinisonNodeProcessor {
    @Override
    public MinisonType getType(Object inst, NamePool names, TypePool types) {
        MinisonStringMode mode;
        Class<?> clazz = inst.getClass();
        if(clazz.isEnum()) {
            mode = MinisonStringMode.Enum;
        }
        else if (clazz == Date.class || clazz == java.sql.Date.class || clazz == LocalDate.class) {
            mode = MinisonStringMode.Date;
        }
        else if (clazz == LocalDateTime.class) {
            mode = MinisonStringMode.DateTime;
        }
        else if (clazz == Time.class || clazz == LocalTime.class) {
            mode = MinisonStringMode.Time;
        }
        else if (clazz == Calendar.class) {
            mode = MinisonStringMode.Calendar;
        }
        else {
            mode = MinisonStringMode.UTF8;
        }
        return new MinisonType((byte)(mode.getCode() | MinisonBaseType.String.getCode()));
    }
}
