package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Info;
import com.example.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public Info save(Info product) {
    return productRepository.save(product);
  }

  public List<Info> getAll(String name) {
    if (name == null || name.isEmpty()) {
      return productRepository.findAllByOrderByIdDesc();
    } else {
      return productRepository.findByFullNameContaining(name);
    }
  }

  public Optional<Info> getById(Long id) {
    return productRepository.findById(id);
  }

  public Info updateWinnerInfo(Long id, Boolean winner) {
    Info oldProduct = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    oldProduct.setWinner(winner);
    return productRepository.save(oldProduct);
  }

  public Info update(Long id, Info product) {
    Info oldProduct = getById(id)
        .orElseThrow(() -> new RuntimeException("Info not found"));

    oldProduct.setPhone(product.getPhone());
    oldProduct.setName(product.getName());
    oldProduct.setReasons(product.getReasons());
    oldProduct.setActivity(product.getActivity());

    return productRepository.save(oldProduct);
  }

  public void deleteProduct(Long id) {
    Info oldProduct = getById(id)
        .orElseThrow(() -> new RuntimeException("Info not found"));

    productRepository.delete(oldProduct);
  }

  public void deleteAll() {
    productRepository.deleteAll();
  }

}
