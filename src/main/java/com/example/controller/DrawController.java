package com.example.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.DrawResponse;
import com.example.entity.Draw;
import com.example.entity.Info;
import com.example.service.DrawService;
import com.example.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/draws")
public class DrawController {
  @Autowired
  private DrawService drawService;

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<DrawResponse> getAll() {
    List<DrawResponse> drawResponse = new ArrayList<>();
    List<Draw> draws = drawService.getAll();

    for (Draw draw : draws) {
      List<Info> infos = draw.getWinners().stream()
          .map(productId -> productService.getById(productId)
              .orElseThrow(() -> new RuntimeException("Info not found")))
          .toList();
      drawResponse.add(new DrawResponse(draw.getCreatedAt(), infos));
    }
    return drawResponse;
  }

  @GetMapping("/{id}")
  public DrawResponse getDraw(@PathVariable Long id) {
    Draw draw = drawService.getById(id)
        .orElseThrow(() -> new RuntimeException("Draw not found"));

    List<Info> infos = draw.getWinners().stream()
        .map(productId -> productService.getById(productId)
            .orElseThrow(() -> new RuntimeException("Info not found")))
        .toList();
    return new DrawResponse(draw.getCreatedAt(), infos);
  }

  @PostMapping("/save")
  public Draw save(@RequestBody Draw draw) {
    return drawService.save(draw);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    drawService.delete(id);
  }

}
