package org.minison.core.node;

import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * 字符串类型的节点
 * @author: kolamu
 * @create: 2024/4/21 12:26
 */
public class StringNode implements MinisonNode {
    @Override
    public void setValue(Object value) {

    }

    @Override
    public Object read(ByteBuffer input) {
        
        return null;
    }

    @Override
    public void write(OutputStream output, Object value) {

    }

}
