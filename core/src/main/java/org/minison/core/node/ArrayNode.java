package org.minison.core.node;

import lombok.Data;
import org.minison.core.util.MinisonNodeUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 数组类型节点
 *
 * @author: kolamu
 * @create: 2024/5/25 17:24
 */
@Data
public class ArrayNode extends MinisonNode<Object[]> {
    private int length;
    private Map<Class, MinisonNode> itemNode;
    private Object[] inst;

    public ArrayNode(Object[] inst) {
        super(inst);
        itemNode = new LinkedHashMap<>();
        for(int i = 0; i < inst.length; i++){
            Object item = inst[i];
            if(Objects.isNull(item) || itemNode.containsKey(item.getClass())) {
                continue;
            }
            itemNode.put(item.getClass(), MinisonNodeUtils.getMinisonNode(item));
        }
    }
}
