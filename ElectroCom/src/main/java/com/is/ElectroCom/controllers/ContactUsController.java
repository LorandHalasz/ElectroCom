package com.is.ElectroCom.controllers;

import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// clasa ContactUsController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class ContactUsController{
    InterfaceUserService userService;
    public ContactUsController(InterfaceUserService userService) {
        this.userService = userService;
    }

    // Metoda care se utilizeaza in momentul in care clientul doreste sa acceseze pagina de contactUs
    @RequestMapping(value = {"","/contactUs"}, method = RequestMethod.GET)
    public String getPage(Model model)
    {
        model.addAttribute("actualUser",userService.getActualUser());
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        return "contactUs";
    }

    // Metoda care se utilizeaza in momentul in care clientul doreste sa trimita un mesaj catre admini
    @RequestMapping(value = {"","/contactReceiveMsg"}, method = RequestMethod.POST)
    public String getMsg(@ModelAttribute(name = "name")String name,  @ModelAttribute(name = "email")String email,  @ModelAttribute(name = "phone")String phone,  @ModelAttribute(name = "message")String message)
    {
        System.out.println("Message From: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Message: " + message);
        // se apeleaza metoda construita conform design pattern-ului Observer care are rolul de a notifica observerii prin trimiterea mesajului de la client catre acestia
        userService.notifyObserver(name, email, phone, message);
        return "redirect:/contactUs";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa acceseze pagina care contine mesajele primite de la utilizatori
    @RequestMapping(value = {"","/messageForAdmins"}, method = RequestMethod.GET)
    public String getMessagePage(Model model)
    {
        System.out.println("GetMessagePage " + userService.getAllMessages());
        model.addAttribute("actualUser", userService.getActualUser());
        model.addAttribute("messages", userService.getAllMessages());
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        return "messageForAdmins";
    }
}
