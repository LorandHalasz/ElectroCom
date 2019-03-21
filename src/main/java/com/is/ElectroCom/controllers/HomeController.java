package com.is.ElectroCom.controllers;

import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// clasa HomeController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class HomeController {
    private InterfaceUserService userService;

    public HomeController(InterfaceUserService userService) {
        this.userService = userService;
    }

    // Metoda care se utilizeaza in momentul in care un utilizator doreste sa acceseze pagina de home a aplicatiei
    @RequestMapping(value = {"", "/home"},method = RequestMethod.GET)
    public String setActualUser(Model model){
        model.addAttribute("actualUser",userService.getActualUser());
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        return "home";
    }

    // Metoda care se utilizeaza in momentul in care un utilizator doreste sa se delogheze din aplicatie
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logout(){
        userService.logout();
        return "redirect:/login";
    }
}