package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService = new ProductServiceImpl();

    @GetMapping("")
    public String index(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Product product) {
        product.setId((int) (Math.random() * 100));
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) {
//        @PathVariable int id lấy id của product từ đường dẫn rồi gán vào biến id.
        model.addAttribute("product",productService.findById(id));
        return "/edit";
    }
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product.getId(),product);
        return "redirect:/product";
    }
    @GetMapping("{id}/delete")
    public String delete(@PathVariable int id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect){
        productService.delete(product.getId());
        redirect.addFlashAttribute("success","Removed customer successfully!");
        return "redirect:/product";
    }
    @GetMapping("{id}/view")
    public  String view(@PathVariable int id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "/view";

    }
}

