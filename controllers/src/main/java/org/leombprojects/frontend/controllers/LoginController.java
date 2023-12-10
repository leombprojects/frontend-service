package org.leombprojects.frontend.controllers;

import lombok.RequiredArgsConstructor;
import org.leombprojects.frontend.services.ContextService;
import org.leombprojects.frontend.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final ContextService contextService;

    @RequestMapping(value = { "/", "/logout" })
    public String init(Model model) {
        contextService.clear();
        model.addAttribute("message", "");
        return "login";
    }

    @RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
    public String userLoginValidate(@RequestParam("username") String user, @RequestParam("password") String password, Model model) {

        boolean validUser = userService.validateAccess(user, password);

        if (validUser) {
            contextService.setUsername(user);
            return "redirect:/products";
        }

        model.addAttribute("message", "Invalid Username or Password");
        return "login";

    }

}