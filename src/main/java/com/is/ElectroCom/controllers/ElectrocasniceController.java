package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.Order;
import com.is.ElectroCom.model.Product;
import com.is.ElectroCom.services.InterfaceOrderService;
import com.is.ElectroCom.services.InterfaceProductService;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

// clasa ElectrocasniceController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class ElectrocasniceController {

    InterfaceUserService userService;
    InterfaceProductService productService;
    InterfaceOrderService orderService;

    public ElectrocasniceController(InterfaceUserService userService, InterfaceProductService productService, InterfaceOrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // Metoda care se utilizeaza in momentul in care un client doreste sa acceseze pagina de electrocasniceAdmin, de unde se poate sa comande electrocasnice
    @RequestMapping(value = {"/electrocasnice"},method = RequestMethod.GET)
    public String getPage(Model model){
        model.addAttribute("actualUser",userService.getActualUser());
        List<Product> electrocasnice = productService.getProducts("Electrocasnice");
        model.addAttribute("electrocasnice",electrocasnice);
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        return "electrocasnice";
     }

    // Metoda care se utilizeaza in momentul in care clientul doreste sa comande un produs
    @RequestMapping(value = {"/electrocasnice/{name}"},method = RequestMethod.POST)
    public String addOrder(Model model,  @PathVariable("name")String name){
        Integer orderId = orderService.getAllOrders().size() + 1;
        String email = userService.getActualUser().getEmail();
        String orderDate = String.valueOf(LocalDate.now());
        System.out.println(orderId + " " + email + " " + name + " " + orderDate);
        Product product = productService.findByName(name);
        if(product.getQty() > 0){
            product.setQty(product.getQty() - 1);
            productService.addNewProduct(product);
            orderService.addNewOrder(new Order(orderId, email, name, orderDate));
        }
        else
        {
            model.addAttribute("productUnavailable", true);
            model.addAttribute("actualUser",userService.getActualUser());

            return "redirect:/electrocasnice";
        }
        return "redirect:/orders";
    }
}
