package org.minison.core.node;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义了Minison中数据的类型名字和字段名字，合法的类名和字段名都应该是字母/数字/下划线/.
 * 这些符号都是ASCII码的范围，因此每个字符串的最后一个字母的最高位设置为1，表示字段的结束
 *
 * @author: kolamu
 * @create: 2024/5/25 16:11
 */
public class NamePool {
    private List<String> namePool;
    public NamePool() {
        this.namePool = new ArrayList<>();
    }

    public int addName(String name) {
        if(name == null) {
            return -1;
        }
        if(namePool.contains(name)) {
            return namePool.indexOf(name);
        }
        this.namePool.add(name);
        return namePool.size() - 1;
    }

    public String get(int index) {
        return this.namePool.get(index);
    }

    public int getIndex(String name) {
        return namePool.indexOf(name);
    }

    public int from(ByteBuffer buf) {
        if(buf == null || buf.limit() == 0) {
            return 0;
        }

        int length = buf.limit();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < length; i++) {
            byte b = buf.get();
            if(b > 0) {
                out.write(b);
                continue;
            }
            out.write(b & 0x7F);
            addName(new String(out.toByteArray()));
            out.reset();
            if(i == length - 1) {
                return length;
            }
            byte next = buf.get(i+1);
            if(next <= 0) {
                return i + 1;
            }
        }
        return length;
    }

    public byte[] toBytes() throws IOException {
        if(this.namePool == null || this.namePool.size() == 0) {
            return new byte[0];
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for(String constant : this.namePool) {
            byte[] bytes = constant.getBytes();
            byte last = bytes[bytes.length-1];
            bytes[bytes.length-1] = (byte) (last | 0x80);
            out.write(bytes);
        }
        return out.toByteArray();
    }
}
