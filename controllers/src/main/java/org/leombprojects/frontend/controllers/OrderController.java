package org.leombprojects.frontend.controllers;

import lombok.RequiredArgsConstructor;
import org.leombprojects.frontend.models.feign.OrderResponse;
import org.leombprojects.frontend.models.feign.Product;
import org.leombprojects.frontend.services.ContextService;
import org.leombprojects.frontend.services.OrderService;
import org.leombprojects.frontend.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ContextService contextService;
    @GetMapping("/order")
    public String init(Model model) {

        OrderResponse orderResponse;

        if(contextService.getPlacedOrder() == null) {
            orderResponse = orderService.createOrder(contextService.getSelectedProductList(), contextService.getUsername());
            contextService.setPlacedOrder(orderResponse);
        }
        else{
            orderResponse = orderService.updateOrder(contextService.getSelectedProductList(), contextService.getUsername(), contextService.getPlacedOrder().getOrder());
            contextService.setPlacedOrder(orderResponse);
        }
        model.addAttribute("order", orderResponse);

        return "order";
    }
    @RequestMapping(value = "changeOrder", method = RequestMethod.POST)
    public String changeOrder(Model model) {
        return "redirect:/products";
    }


}
