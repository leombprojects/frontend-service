package org.leombprojects.frontend.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.leombprojects.frontend.models.feign.OrderResponse;
import org.leombprojects.frontend.models.feign.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ContextService {
    private String username;
    private List<Product> startersList;
    private List<Product> mainsList;
    private List<Product> drinksList;
    private List<Product> selectedProductList;
    private OrderResponse placedOrder;

    public void clear(){
        username = null;
        startersList = null;
        mainsList = null;
        drinksList = null;
        selectedProductList = null;
        placedOrder = null;
    }
}
