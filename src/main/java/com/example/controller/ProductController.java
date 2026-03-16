package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Info;
import com.example.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Info> getAll(@RequestParam(defaultValue = "") String name) {
    return productService.getAll(name);
  }

  @GetMapping("/{id}")
  public Optional<Info> getById(@PathVariable Long id) {
    return productService.getById(id);
  }

  @PostMapping("/save")
  public Info save(@RequestBody Info product) {
    return productService.save(product);
  }

  @PutMapping("/update/{id}")
  public Info update(@PathVariable Long id, @RequestBody Info product) {

    return productService.update(id, product);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    productService.deleteProduct(id);
  }

  @DeleteMapping("/delete/all")
  public void deleteAll() {
    productService.deleteAll();
  }

}
