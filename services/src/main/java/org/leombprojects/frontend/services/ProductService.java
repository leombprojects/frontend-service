package org.leombprojects.frontend.services;

import org.leombprojects.frontend.models.feign.Product;

import java.util.List;

public interface ProductService {
    List<Product> listProduct(String course);
}
