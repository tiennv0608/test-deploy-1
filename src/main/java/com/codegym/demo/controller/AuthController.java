package com.codegym.demo.controller;

import com.codegym.demo.model.Product;
import com.codegym.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/list")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private IProductService productService;

    @GetMapping("/hello")
    public ModelAndView showList(){
        return new ModelAndView("/product/test");
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        List<Product> list = (List<Product>) productService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
