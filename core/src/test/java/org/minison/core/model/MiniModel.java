package org.minison.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: kolamu
 * @create: 2024/6/22 11:25
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiniModel {
    private String name;
    private int id;
    private long longData;
    private boolean boolData;
    private MiniItemModel item;
}
