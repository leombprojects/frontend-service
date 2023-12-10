package org.leombprojects.frontend.clients;

import jakarta.websocket.server.PathParam;
import org.leombprojects.frontend.models.feign.Product;
import org.leombprojects.frontend.models.feign.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "product-service", url = "${clients.product-service.base-url}")
public interface ProductFeignClient {
    @GetMapping(value = "${clients.product-service.list-path}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    ProductResponse listProduct(@RequestHeader("Authorization") String token,
                                @PathVariable String course);
}
