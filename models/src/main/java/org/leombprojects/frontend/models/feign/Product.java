package org.leombprojects.frontend.models.feign;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private String name;
    private String code;
    private String desc;
    private Integer calories;
    private String course;
    private String image;
}
