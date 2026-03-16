package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Info;

@Repository
public interface ProductRepository extends JpaRepository<Info, Long> {
  List<Info> findAllByOrderByIdDesc();

  List<Info> findByNameContainingIgnoreCase(String name);
}
