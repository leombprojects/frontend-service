package org.leombprojects.frontend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leombprojects.frontend.clients.SecurityFeignClient;
import org.leombprojects.frontend.clients.UserFeignClient;
import org.leombprojects.frontend.models.feign.SecurityRequest;
import org.leombprojects.frontend.models.feign.SecurityResponse;
import org.leombprojects.frontend.models.feign.UserRequest;
import org.leombprojects.frontend.models.feign.UserResponse;
import org.leombprojects.frontend.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final SecurityFeignClient securityFeignClient;
    private final UserFeignClient userFeignClient;

    @Value("${clients.auth.client-id}")
    private String clientId;
    @Value("${clients.auth.client-secret}")
    private String clientSecret;
    @Value("${clients.auth.scope}")
    private String scope;
    @Value("${clients.auth.grant-type}")
    private String grantType;

    @Override
    public boolean validateAccess(String user, String password) {

        SecurityRequest securityRequest = SecurityRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scope(scope)
                .grantType(grantType)
                .build();

        SecurityResponse securityResponse = securityFeignClient.securityGenerateToken(securityRequest);

        UserRequest userRequest = UserRequest.builder()
                .user(user)
                .password(password)
                .build();

        UserResponse userResponse = userFeignClient.validateUser("Bearer " + securityResponse.getAccess_token(), userRequest);

        return userResponse.isValidUser();
    }
}
