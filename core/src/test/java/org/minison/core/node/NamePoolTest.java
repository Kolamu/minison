package org.minison.core.node;

import org.testng.annotations.Test;

import java.nio.ByteBuffer;

public class NamePoolTest {
    @Test
    public void testToBytes() throws Exception {
        NamePool pool = new NamePool();
        pool.addConstant("test");
        pool.addConstant("test2");
        byte[] bytes = pool.toBytes();
        NamePool pool2 = new NamePool();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 2);
        buffer.put(bytes);
        pool2.from(ByteBuffer.wrap(bytes));
        System.out.println(pool2);
    }
}