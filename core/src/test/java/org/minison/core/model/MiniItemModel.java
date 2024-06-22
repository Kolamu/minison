package org.minison.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: kolamu
 * @create: 2024/6/22 11:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiniItemModel {
    private Integer id;
    private String name;
}
