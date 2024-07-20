package org.minison.core.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * 数字类型的序列化反序列化算法
 *
 * @author: kolamu
 * @create: 2024/7/20 18:09
 */
public class NumberCodec {
    public static void variant(long value, OutputStream os) throws IOException {
        if(Objects.isNull(os)) {
            return;
        }
        while (true) {
            if ((value & ~0x7F) == 0) {
                os.write((byte)(value | 0x80));
                break;
            } else {
                os.write((byte)((value & 0x7F) | 0x80));
                value >>>= 7;
            }
        }
    }

    public static long variant(InputStream is) throws IOException {
        if(Objects.isNull(is)) {
            return 0;
        }
        long value = 0;
        int i = 0;
        int b = is.read();
        while ((b & 0x80) != 0) {
            value |= (b & 0x7F) << (7 * i);
            b = is.read();
            i++;
        }
        return value;
    }

    public static void zigzag(long value, OutputStream os) throws IOException {
        variant((value << 1) ^ (value >> 63), os);
    }

    public static long zigzag(InputStream is) throws IOException {
        long value = variant(is);
        return (value >>> 1) ^ (value & 1) * -1;
    }
}
