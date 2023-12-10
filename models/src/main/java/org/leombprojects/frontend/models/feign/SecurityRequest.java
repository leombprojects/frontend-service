package org.leombprojects.frontend.models.feign;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SecurityRequest {

    @FormProperty("client_id")
    private String clientId;
    @FormProperty("client_secret")
    private String clientSecret;
    @FormProperty("scope")
    private String scope;
    @FormProperty("grant_type")
    private String grantType;

}
