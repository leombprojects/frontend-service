package org.leombprojects.frontend.models.feign;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private String user;
    private String password;
}
