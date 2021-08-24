package com.codegym.demo.controller;

import com.codegym.demo.model.Product;
import com.codegym.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        return new ModelAndView("/product/create");
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("message", "New product was created");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ModelAndView("/error.404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("message", "Product was updated");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetails(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ModelAndView("/error.404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/product/view");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ModelAndView("/error.404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        productService.remove(id);
        return new ModelAndView("redirect:/products");
    }

}
