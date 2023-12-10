package org.leombprojects.frontend.controllers;

import lombok.RequiredArgsConstructor;
import org.leombprojects.frontend.models.feign.Product;
import org.leombprojects.frontend.services.ContextService;
import org.leombprojects.frontend.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ContextService contextService;

    @GetMapping("/products")
    public String init(Model model) {

        List<Product> mainsList = productService.listProduct("MAINS");
        List<Product> startersList = productService.listProduct("STARTERS");
        List<Product> drinksList = productService.listProduct("DRINKS");

        model.addAttribute("mainsList", mainsList);
        model.addAttribute("startersList", startersList);
        model.addAttribute("drinksList", drinksList);

        contextService.setStartersList(startersList);
        contextService.setMainsList(mainsList);
        contextService.setDrinksList(drinksList);

        return "products";
    }

    @RequestMapping(value = "selectProducts", method = RequestMethod.POST)
    public String selectProducts(@RequestParam("main") Optional<String> main, @RequestParam("entry") Optional<String> entry, @RequestParam("drink") Optional<String> drink, Model model) {

        if(!StringUtils.hasText(entry.orElse(""))){
            model.addAttribute("message1", "Select one ENTRY option!");
        }

        if(!StringUtils.hasText(main.orElse(""))){
            model.addAttribute("message2", "Select one MAIN COURSE option!");
        }
        if(!StringUtils.hasText(drink.orElse(""))){
            model.addAttribute("message3", "Select one DRINK option!");
        }

        if(model.asMap().isEmpty()){
            List<Product> selectedProd = new ArrayList<>();

            Optional<Product> starterSelected = contextService.getStartersList().stream().filter(product -> product.getCode().equals(entry.orElse(null))).findFirst();
            Optional<Product> mainSelected = contextService.getMainsList().stream().filter(product -> product.getCode().equals(main.orElse(null))).findFirst();
            Optional<Product> drinkSelected = contextService.getDrinksList().stream().filter(product -> product.getCode().equals(drink.orElse(null))).findFirst();

            selectedProd.add(starterSelected.get());
            selectedProd.add(mainSelected.get());
            selectedProd.add(drinkSelected.get());

            contextService.setSelectedProductList(selectedProd);
        }

        return "redirect:/order";
    }
}
