package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.Order;
import com.is.ElectroCom.services.InterfaceOrderService;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

// clasa OrderAdminController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class OrderAdminController {
    InterfaceUserService userService;
    InterfaceOrderService orderService;

    public OrderAdminController(InterfaceUserService userService, InterfaceOrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa acceseze pagina de administrare a comenzilor
    @RequestMapping(value = {"/orderAdmin"},method = RequestMethod.GET)
    public String getPage(Model model){
        model.addAttribute("actualUser",userService.getActualUser());
        List<Order> orders = new ArrayList<>();
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        orders = orderService.getAllOrders();
        if(!orders.isEmpty())
            model.addAttribute("orders", orders);
        return "orderAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa stearga o comanda a unui user
    @RequestMapping(value = "/orderAdmin/{orderId}/deleteOrder",method = RequestMethod.POST)
    public String deleteOrder(@PathVariable("orderId")String id){

        Integer orderId = Integer.parseInt(id);
        orderService.deleteOrder(orderId);

        return "redirect:/orderAdmin";
    }
}
