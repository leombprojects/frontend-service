package org.leombprojects.frontend.models.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {
    private String code;
    private String name;
    private String desc;
    private String course;
    private String calories;

}
