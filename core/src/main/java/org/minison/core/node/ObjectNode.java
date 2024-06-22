package org.minison.core.node;

import lombok.Data;
import java.util.Map;

/**
 * Object 节点
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
@Data
public class ObjectNode implements MinisonNode {
    private String name;
    private Map<String, MinisonNode> fields;

    @Override
    public void setValue(Object inst) {

    }

    @Override
    public Object getValue() {
        return null;
    }
}
