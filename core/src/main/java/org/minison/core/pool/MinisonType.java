package org.minison.core.pool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * 类型对象
 * @author: kolamu
 * @create: 2024/7/20 21:58
 */
@Data
public class MinisonType {
    private int index;
    private String name;
    /**
     * int[3] { nameIndex, typeCode, subType }
     */
    private int[] items;

    public MinisonType(int index) {
        this.index = index;
    }

    public MinisonType(int[] items) {
        this(null, items);
    }

    public MinisonType(String name, int[] items) {
        this.name = name;
        this.items = items;
    }

    /**
     * 获取字段名称
     * @param index
     * @return
     */
    public int getName(int index) {
        if(index < 0 || index >= items.length / 3) {
            throw new IndexOutOfBoundsException();
        }
        return items[index * 3];
    }

    /**
     * 获取字段的基础类型
     * @param index
     * @return
     */
    public int getType(int index) {
        if(index < 0 || index >= items.length / 3) {
            throw new IndexOutOfBoundsException();
        }
        return items[index * 3 + 1];
    }

    /**
     * 获取字段的类型
     * @param index
     * @return
     */
    public int getMode(int index) {
        if(index < 0 || index >= items.length / 3) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return items[index * 3 + 2];
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof MinisonType)) {
            return false;
        }
        MinisonType minisonType = (MinisonType) obj;
        if(minisonType.items == this.items) {
            return true;
        }
        if(minisonType.items == null || this.items == null) {
            return false;
        }

        if(minisonType.items.length != this.items.length) {
            return false;
        }
        for(int i = 0; i < minisonType.items.length / 3; i++) {
            int idx = i * 3;
            if(this.items[idx] != minisonType.items[idx]) {
                return false;
            }
            if(this.items[idx+1] != minisonType.items[idx+1]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if(items == null || items.length == 0){
            return 0;
        }
        return Arrays.hashCode(items);
    }
}
