package org.leombprojects.frontend.clients;

import org.leombprojects.frontend.models.feign.UserRequest;
import org.leombprojects.frontend.models.feign.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "user-service", url = "${clients.user-service.base-url}")
public interface UserFeignClient {
  @PostMapping(value = "${clients.user-service.validate-path}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  UserResponse validateUser(@RequestHeader("Authorization") String token,
                            @RequestBody UserRequest userRequest);
}