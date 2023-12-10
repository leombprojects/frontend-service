package org.leombprojects.frontend.models.feign;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderResponse {
    private Integer order;
    private Employee employee;

    private List<ItemResponse> itemList;
}
