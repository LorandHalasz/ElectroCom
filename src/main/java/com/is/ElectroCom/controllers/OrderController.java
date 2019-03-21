package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.Order;
import com.is.ElectroCom.services.InterfaceOrderService;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

// clasa OrderController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class OrderController {
    InterfaceUserService userService;
    InterfaceOrderService orderService;

    public OrderController(InterfaceUserService userService, InterfaceOrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    // Metoda care se utilizeaza in momentul in care un client doreste sa acceseze pagina care contine comenzile acestuia
    @RequestMapping(value = {"/orders"},method = RequestMethod.GET)
    public String getPage(Model model){
        model.addAttribute("actualUser",userService.getActualUser());
        List<Order> orders = new ArrayList<>();
        if(userService.getActualUser().getRole().equals("Admin")){
            model.addAttribute("admin", true);
            orders = orderService.getAllOrders();
        }
        else {
            model.addAttribute("notAdmin", true);
            orders = orderService.getOrders(userService.getActualUser().getEmail());
        }
        if(!orders.isEmpty())
            model.addAttribute("orders", orders);
        return "orders";
    }
}
