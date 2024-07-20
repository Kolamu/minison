package org.minison.core.pool;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 类型对象
 * @author: kolamu
 * @create: 2024/7/20 21:58
 */
@Data
public class MinisonType {
    private String name;
    private List<MinisonTypeItem> items;

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        if(items == null || items.isEmpty()){
            return 0;
        }
        int[] data = new int[items.size() * 2];
        for(int i = 0; i < items.size(); i++){
            data[i * 2] = items.get(i).getNameIndex();
            data[i * 2 + 1] = items.get(i).getTypeIndex();
        }
        return Arrays.hashCode(data);
    }
}
