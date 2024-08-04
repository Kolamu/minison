package org.minison.core.util;

import org.minison.core.MinisonNode;
import org.minison.core.model.MiniItemModel;
import org.minison.core.model.MiniModel;
import org.minison.core.node.MinisonNodeProcessor;
import org.testng.annotations.Test;

public class MinisonNodeProcessorUtilsTest {
    @Test
    public void testItem() throws Exception {
        MiniItemModel itemModel = MiniItemModel.builder()
                .id(1)
                .name("n")
                .build();

        MiniModel model = MiniModel.builder()
                .id(1)
                .name("name")
                .boolData(true)
                .longData(20L)
                .item(itemModel)
                .build();

        MinisonNode node = MinisonNodeUtils.getMinisonNode(model);
        System.out.println(node);
    }
}