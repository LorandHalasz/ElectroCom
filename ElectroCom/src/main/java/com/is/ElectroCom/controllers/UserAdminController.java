package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.User;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

// clasa UserAdminController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class UserAdminController{

    InterfaceUserService userService;

    public UserAdminController(InterfaceUserService userService) {
        this.userService = userService;
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa acceseze pagina de userAdmin, unde se pot realiza operatiile de tip CRUD asupra utilizatorilor
    @RequestMapping(value = {"/userAdmin"}, method = RequestMethod.GET)
    public String getUserPage(Model model){
        model.addAttribute("actualUser", userService.getActualUser());
        List<User> users = userService.getUsers();
        model.addAttribute("user", users);
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", false);
        return "userAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin se rasgandeste asupra operatiilor facute
    @RequestMapping(value = {"/CancelButtonUsers"},method = RequestMethod.GET)
    public String getUserPageCancel(Model model){
        model.addAttribute("actualUser", userService.getActualUser());
        List<User> users = userService.getUsers();
        model.addAttribute("user", users);
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", false);
        return "userAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa adauge un client
    @RequestMapping(value = {"/userAdmin/{email}/addUser"},method = RequestMethod.POST)
    public String addUser(@ModelAttribute(name = "user")User user){
        user.setMessages(new ArrayList<String>());
        userService.addNewUser(user);
        return "redirect:/userAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa realizeze anumite modificari
    @RequestMapping(value = "/userAdmin/{email}/editUser",method = RequestMethod.POST)
    public String editUser(Model model, @PathVariable("email")String email){
        model.addAttribute("UserEmail1", email);
        System.out.println(email);
        return "redirect:/userAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa acceseze pagina pentru modificarea datelor unui utilizator
    @RequestMapping(value = {"/editUser/{email}"},method = RequestMethod.GET)
    public String getEditPage(Model model, @PathVariable("email")String email){
        User user = userService.findByEmail(email);

        model.addAttribute("email", email);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("role", user.getRole());
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", false);
        return "editUser";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa modifice datele unui client
    @RequestMapping(value = {"/editUser"},method = RequestMethod.POST)
    public String getEditPageUser(@ModelAttribute(name = "email")String email, @ModelAttribute(name = "firstName")String firstName, @ModelAttribute(name = "lastName")String lastName, @ModelAttribute(name = "address")String address, @ModelAttribute(name = "phoneNumber")String phoneNumber, @ModelAttribute(name = "password")String password, @ModelAttribute(name = "role")String role){
        System.out.println(email + " -- " + firstName + " -- " + lastName + "--" + address + "--" + phoneNumber + "--" + password + "--" + role);
        if(firstName.isEmpty())
            firstName = userService.findByEmail(email).getFirstName();
        if(lastName.isEmpty())
            lastName = userService.findByEmail(email).getLastName();
        if(address.isEmpty())
            address = userService.findByEmail(email).getAddress();
        if(phoneNumber.isEmpty())
            phoneNumber = userService.findByEmail(email).getPhoneNumber();
        if(password.isEmpty())
            password = userService.findByEmail(email).getPassword();
        if(role.isEmpty())
            role = userService.findByEmail(email).getRole();
        userService.editAdminUser(email, firstName, lastName, address, phoneNumber, password, role);
        return "redirect:/userAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa stearga un client
    @RequestMapping(value = "/userAdmin/{email}/deleteUser",method = RequestMethod.POST)
    public String delUser(@PathVariable("email")String email){

        userService.deleteUser(email);

        return "redirect:/userAdmin";
    }
}
