package org.minison.core.pool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * 定义了Minison中的复杂类型字段，类型下面主要包含字段列表，字段的格式包括名称和类型，全部通过index表示
 * 类型包含基本类型和子类型2种
 * 基本类型定义在了<code>MinisonBaseType</code>中
 * 包含字符串、数字、数组、对象以及Map 5种类型，每种类型又会根据实际数据来设置子类型
 * 子类型主要用来区分序列化和反序列化时所使用的算法
 * 在数据序列化时，根据数据的类型来确定子类型进而确定序列化算法
 * 在数据反序列化时，根据子类型确定反序列化算法进而能够正确读取数据
 * 多层JSON结构通过多个类型来表示，字段的基础类型均为对象类型，子类型为类型index
 *
 * 索引为0的类型为最外层的类型
 *
 * @author: kolamu
 * @create: 2024/7/20 17:40
 * @see MinisonBaseType
 */
public class TypePool {
    private List<MinisonType> types;

    public TypePool() {
        this.types = new ArrayList<>();
    }

    public int addType(MinisonType name) {
        if(name == null) {
            return -1;
        }
        if(types.contains(name)) {
            return types.indexOf(name);
        }
        this.types.add(name);
        return types.size() - 1;
    }

    public MinisonType get(int index) {
        return this.types.get(index);
    }

    public int getIndex(String name) {
        return types.indexOf(name);
    }

    public int from(ByteBuffer buf) {
//        if(buf == null || buf.limit() == 0) {
//            return 0;
//        }
//
//        int length = buf.limit();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        for (int i = 0; i < length; i++) {
//            byte b = buf.get();
//            if(b > 0) {
//                out.write(b);
//                continue;
//            }
//            out.write(b & 0x7F);
//            addName(new String(out.toByteArray()));
//            out.reset();
//            if(i == length - 1) {
//                return length;
//            }
//            byte next = buf.get(i+1);
//            if(next <= 0) {
//                return i + 1;
//            }
//        }
//        return length;
        return 0;
    }

    public byte[] toBytes() throws IOException {
//        if(this.namePool == null || this.namePool.size() == 0) {
//            return new byte[0];
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        for(String constant : this.namePool) {
//            byte[] bytes = constant.getBytes();
//            byte last = bytes[bytes.length-1];
//            bytes[bytes.length-1] = (byte) (last | 0x80);
//            out.write(bytes);
//        }
//        return out.toByteArray();
        return new byte[0];
    }
}
