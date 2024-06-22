package org.minison.core.util;

import org.minison.core.model.MiniItemModel;
import org.minison.core.model.MiniModel;
import org.minison.core.node.MinisonNode;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MinisonNodeUtilsTest {
    @Test
    public void testItem() throws Exception {
        MiniModel model = MiniModel.builder()
                .id(1)
                .name("name")
                .boolData(true)
                .longData(20L)
                .item(MiniItemModel.builder()
                        .build())
                .build();

        MinisonNode node = MinisonNodeUtils.getMinisonNode(model);
        System.out.println(node);
    }
}