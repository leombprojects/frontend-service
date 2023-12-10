package org.leombprojects.frontend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leombprojects.frontend.clients.ProductFeignClient;
import org.leombprojects.frontend.clients.SecurityFeignClient;
import org.leombprojects.frontend.models.feign.*;
import org.leombprojects.frontend.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final SecurityFeignClient securityFeignClient;
    private final ProductFeignClient productFeignClient;

    @Value("${clients.auth.client-id}")
    private String clientId;
    @Value("${clients.auth.client-secret}")
    private String clientSecret;
    @Value("${clients.auth.scope}")
    private String scope;
    @Value("${clients.auth.grant-type}")
    private String grantType;

    @Override
    public List<Product> listProduct(String course) {

        SecurityRequest securityRequest = SecurityRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scope(scope)
                .grantType(grantType)
                .build();

        SecurityResponse securityResponse = securityFeignClient.securityGenerateToken(securityRequest);

        ProductResponse productResponse = productFeignClient.listProduct("Bearer " + securityResponse.getAccess_token(), course);

        return productResponse.getProductList();
    }
}
