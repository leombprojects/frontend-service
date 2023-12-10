package org.leombprojects.frontend.services;

import org.leombprojects.frontend.models.feign.OrderResponse;
import org.leombprojects.frontend.models.feign.Product;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(List<Product> productList, String username);
    OrderResponse updateOrder(List<Product> productList, String username, Integer order);
}
