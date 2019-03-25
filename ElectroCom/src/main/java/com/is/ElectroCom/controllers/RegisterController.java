package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.User;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

// clasa RegisterController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class RegisterController {

    public InterfaceUserService userService;

    public RegisterController(InterfaceUserService userService) {
        this.userService = userService;
    }

    // Metoda care se utilizeaza in momentul in care un utilizator doreste sa acceseze pagina de register a aplicatiei
    @RequestMapping(value = {"","/register"}, method = RequestMethod.GET)
    public String getPage(){
        return "register";
    }

    // Metoda care se utilizeaza in momentul in care un utilizator doreste sa se inregistreze in aplicatie
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(name = "registerUser") User user, Model model){
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String address = user.getAddress();
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();
        String password= user.getPassword();
        String role = "Client";

        if(userService.findByEmail(email) != null){
            model.addAttribute("existingUser",true);
            return "register";
        }
        else{
            User newUser = new User(firstName, lastName, address, phoneNumber, email, password, role, new ArrayList<String>());
            userService.registerUser(newUser);
            return "redirect:/login";
        }

    }
}
