package org.minison.core.node;

import lombok.Data;
import org.minison.core.util.MinisonNodeUtils;
import org.minison.core.util.ReflectionUtils;
import org.minison.core.util.UnsafeUtils;

import javax.management.ObjectInstance;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Object 节点
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
@Data
public class ObjectNode implements MinisonNode {
    private String name;
    private Map<String, MinisonNode> fields;
    private Map<String, Object> data;

    @Override
    public void setValue(Object inst) {
        this.data = new LinkedHashMap<>();
        this.fields = new LinkedHashMap<>();
        if(inst instanceof Map) {
            buildMap((Map)inst);
        }
        else {
            buildObject(inst);
        }
    }

    @Override
    public Object read(ByteBuffer input) {

        return null;
    }

    @Override
    public void write(OutputStream output, Object value) {

    }

    private void buildObject(Object inst) {
        Map<String, Field> map = ReflectionUtils.getFields(inst.getClass());
        for(String key : map.keySet()) {
            Object value = UnsafeUtils.getValue(inst, key);
            if(Objects.isNull(value)) {
                continue;
            }
            fields.put(key.toString(), MinisonNodeUtils.getMinisonNode(value));
            data.put(key.toString(), value);
        }
    }

    private void buildMap(Map inst) {
        for(Object key : inst.keySet()) {
            Object value = inst.get(key);
            fields.put(key.toString(), MinisonNodeUtils.getMinisonNode(value));
            data.put(key.toString(), value);
        }
    }
}
