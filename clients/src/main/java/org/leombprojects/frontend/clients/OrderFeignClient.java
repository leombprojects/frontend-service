package org.leombprojects.frontend.clients;

import org.leombprojects.frontend.models.feign.OrderRequest;
import org.leombprojects.frontend.models.feign.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@FeignClient(value = "order-service", url = "${clients.order-service.base-url}")
public interface OrderFeignClient {
    @PostMapping(value = "${clients.order-service.create-path}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    OrderResponse postCreateOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest orderRequest);

    @PutMapping(value = "${clients.order-service.update-path}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    OrderResponse postUpdateOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest orderRequest);
}
