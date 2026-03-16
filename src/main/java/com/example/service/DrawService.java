package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Draw;
import com.example.repository.DrawRepository;

@Service
public class DrawService {
  @Autowired
  private DrawRepository drawRepository;

  public Draw save(Draw draw) {
    return drawRepository.save(draw);
  }

  public List<Draw> getAll() {
    return drawRepository.findAllByOrderByCreatedAtDesc();
  }

  public Optional<Draw> getById(Long id) {
    return drawRepository.findById(id);
  }

  public void delete(Long id) {
    Draw oldDraw = getById(id)
        .orElseThrow(() -> new RuntimeException("Draw not found"));

    drawRepository.delete(oldDraw);
  }
}
