package org.leombprojects.frontend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leombprojects.frontend.clients.OrderFeignClient;
import org.leombprojects.frontend.clients.SecurityFeignClient;
import org.leombprojects.frontend.models.feign.*;
import org.leombprojects.frontend.services.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final SecurityFeignClient securityFeignClient;
    private final OrderFeignClient orderFeignClient;

    @Value("${clients.auth.client-id}")
    private String clientId;
    @Value("${clients.auth.client-secret}")
    private String clientSecret;
    @Value("${clients.auth.scope}")
    private String scope;
    @Value("${clients.auth.grant-type}")
    private String grantType;
    @Override
    public OrderResponse createOrder(List<Product> productList, String username) {

        SecurityResponse securityResponse = getSecurityResponse();

        OrderRequest orderRequest = getOrderRequest(productList, username, null);

        return orderFeignClient.postCreateOrder("Bearer " + securityResponse.getAccess_token(), orderRequest);
    }

    @Override
    public OrderResponse updateOrder(List<Product> productList, String username, Integer order) {

        SecurityResponse securityResponse = getSecurityResponse();

        OrderRequest orderRequest = getOrderRequest(productList, username, order);

        return orderFeignClient.postUpdateOrder("Bearer " + securityResponse.getAccess_token(), orderRequest);

    }

    private SecurityResponse getSecurityResponse(){
        SecurityRequest securityRequest = SecurityRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scope(scope)
                .grantType(grantType)
                .build();

        return securityFeignClient.securityGenerateToken(securityRequest);

    }

    private OrderRequest getOrderRequest(List<Product> productList, String username, Integer order){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrder(order);
        orderRequest.setEmployee(new Employee());
        orderRequest.getEmployee().setUsername(username);
        orderRequest.setItemList(new ArrayList<>());
        productList.forEach(product -> {
            ItemRequest itemRequest = new ItemRequest();
            itemRequest.setCode(product.getCode());
            orderRequest.getItemList().add(itemRequest);
        });
        return orderRequest;
    }
}
