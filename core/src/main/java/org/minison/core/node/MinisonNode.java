package org.minison.core.node;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * TODO
 *
 * @author: kolamu
 * @create: 2024/4/21 12:25
 */
public interface MinisonNode {
    void setValue(Object value);
    Object read(ByteBuffer input);
    void write(OutputStream output, Object value);
}
