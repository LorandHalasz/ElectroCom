package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.User;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// clasa LoginController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class LoginController {

    public InterfaceUserService userService;
    boolean onlyFirst = true;
    public LoginController(InterfaceUserService userService) {
        this.userService = userService;
    }

    // Metoda care se utilizeaza in momentul in care un utilizator doreste sa acceseze pagina de login a aplicatiei
    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String getPage(){
        if(onlyFirst){
            onlyFirst = false;
            userService.createObserverList();
        }
        return "login";
    }

    // Metoda care se utilizeaza in momentul in care un utilizator doreste sa se logheze in aplicatie
    @RequestMapping(value = {"/","/login"}, method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginUser")User user, Model model){
        String email = user.getEmail();
        String password = user.getPassword();

        System.out.println(email + password);
        model.addAttribute("email",email);
        model.addAttribute("passW",password);

        if(userService.findByEmail(email) != null && userService.findByEmail(email).getPassword().equals(password))
        {
            userService.setActualUser(userService.findByEmail(email));
            return "redirect:/home";
        }
        model.addAttribute("wrongEmailOrPass",true);
        return "login";
    }
}