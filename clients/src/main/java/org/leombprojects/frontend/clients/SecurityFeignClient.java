package org.leombprojects.frontend.clients;


import feign.Headers;
import org.leombprojects.frontend.models.feign.SecurityRequest;
import org.leombprojects.frontend.models.feign.SecurityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(value = "security-service", url = "${clients.auth.base-url}")
public interface SecurityFeignClient {
  @PostMapping(value = "${clients.auth.token-path}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_FORM_URLENCODED_VALUE)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  SecurityResponse securityGenerateToken(@RequestBody SecurityRequest securityRequest);

}
