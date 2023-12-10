package org.leombprojects.frontend.models.feign;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ProductResponse {
    List<Product> productList;
}
