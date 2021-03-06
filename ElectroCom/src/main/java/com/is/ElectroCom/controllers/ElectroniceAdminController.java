package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.Product;
import com.is.ElectroCom.services.InterfaceProductService;
import com.is.ElectroCom.services.InterfaceUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// clasa ElectroniceAdminController se utilizeaza pentru a rezolva request-urile de tip GET si POST

@Controller
public class ElectroniceAdminController {

    InterfaceUserService userService;
    InterfaceProductService productService;

    public ElectroniceAdminController(InterfaceUserService userService, InterfaceProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa acceseze pagina de electroniceAdmin, unde se pot realiza operatiile de tip CRUD pentru produsele de tip electronice
    @RequestMapping(value = {"/electroniceAdmin"},method = RequestMethod.GET)
    public String getPage(Model model){
        model.addAttribute("actualUser",userService.getActualUser());
        List<Product> electronice = productService.getProducts("Electronice");
        model.addAttribute("electronice",electronice);
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        return "electroniceAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin se rasgandeste asupra operatiilor facute
    @RequestMapping(value = {"/CancelButtonEl"},method = RequestMethod.GET)
    public String getPageCancel(Model model){
        model.addAttribute("actualUser",userService.getActualUser());
        List<Product> electronice = productService.getProducts("Electronice");
        model.addAttribute("electronice",electronice);
        if(userService.getActualUser().getRole().equals("Admin"))
            model.addAttribute("admin", true);
        else
            model.addAttribute("notAdmin", true);
        return "electroniceAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa adauge un nou produs
    @RequestMapping(value = {"/electroniceAdmin"},method = RequestMethod.POST)
    public String addProduct(@ModelAttribute(name = "product")Product product, @ModelAttribute(name = "price")String price, @ModelAttribute(name = "qty")String qty, @ModelAttribute(name = "rating")String rating){
        String name = product.getName();
        String shortDescription = product.getShortDescription();
        String imageUrl = product.getImageUrl();
        String type = product.getType();

        Double actPrice = Double.parseDouble(price);
        Integer actQty = Integer.parseInt(qty);
        Double actRating = Double.parseDouble(rating);

        System.out.println(name + " -- " + shortDescription + " -- " + imageUrl + " -- " + type + " -- " + actPrice + " -- " + actQty + " -- " + actRating);
        productService.addNewProduct(new Product(name,shortDescription,imageUrl,actPrice,actQty,actRating,type));

        return "redirect:/electroniceAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa realizeze anumite modificari
    @RequestMapping(value = "/electroniceAdmin/{name}/editElectronice",method = RequestMethod.POST)
    public String editProduct(Model model, @PathVariable("name")String name){
        model.addAttribute("productName1", name);
        System.out.println(name);
        return "redirect:/electroniceAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa acceseze pagina pentru modificarea datelor unui produs
    @RequestMapping(value = {"/editElectronice/{name}"},method = RequestMethod.GET)
    public String getEditPage(Model model, @PathVariable("name")String name){
        Product product = productService.findByName(name);

        model.addAttribute("name", name);
        model.addAttribute("shortDescription", product.getShortDescription());
        model.addAttribute("imageUrl", product.getImageUrl());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("qty", product.getQty());
        model.addAttribute("rating", product.getRating());
        return "editElectronice";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa modifice un produs
    @RequestMapping(value = {"/editElectronice"},method = RequestMethod.POST)
    public String getEditPage(@ModelAttribute(name = "name")String name, @ModelAttribute(name = "shortDescription")String shortDescription, @ModelAttribute(name = "imageUrl")String imageUrl, @ModelAttribute(name = "price")String price, @ModelAttribute(name = "qty")String qty, @ModelAttribute(name = "rating")String rating){
        System.out.println(name + " -- " + shortDescription + " -- " + imageUrl + " -- " + price + " -- " + qty + " -- " + rating);

        Double newPrice;
        Integer newQty;
        Double newRating;
        if(shortDescription.isEmpty())
            shortDescription = productService.findByName(name).getShortDescription();
        if(imageUrl.isEmpty())
            imageUrl = productService.findByName(name).getImageUrl();
        if(!price.isEmpty())
            newPrice = Double.parseDouble(price);
        else
            newPrice = productService.findByName(name).getPrice();
        if(!qty.isEmpty())
            newQty = Integer.parseInt(qty);
        else
            newQty = productService.findByName(name).getQty();
        if(!rating.isEmpty())
            newRating = Double.parseDouble(rating);
        else
            newRating = productService.findByName(name).getRating();
        productService.editProduct(name, shortDescription, imageUrl, newPrice, newQty, newRating);

        return "redirect:/electroniceAdmin";
    }

    // Metoda care se utilizeaza in momentul in care un admin doreste sa stearga un produs
    @RequestMapping(value = "/electroniceAdmin/{productName}/deleteProduct",method = RequestMethod.POST)
    public String delProduct(@PathVariable("productName")String name){

        productService.deleteProduct(name);

        return "redirect:/electroniceAdmin";
    }
}
