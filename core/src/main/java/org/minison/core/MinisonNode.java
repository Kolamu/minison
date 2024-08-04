package org.minison.core;

import org.minison.core.pool.NamePool;
import org.minison.core.pool.TypePool;

/**
 * Minison 数据节点
 * @author: kolamu
 * @create: 2024/8/3 18:22
 */
public class MinisonNode {
    private NamePool names;
    private TypePool types;

    public MinisonNode(NamePool names, TypePool types) {
        this.names = names;
        this.types = types;
    }
}
