package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Draw;

@Repository
public interface DrawRepository extends JpaRepository<Draw, Long> {
  List<Draw> findAllByOrderByCreatedAtDesc();

}
