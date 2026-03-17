package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Info;

@Repository
public interface ProductRepository extends JpaRepository<Info, Long> {
  /*
   * ~~(class org.openrewrite.java.tree.J$Erroneous cannot be cast to class
   * org.openrewrite.java.tree.J$Assignment (org.openrewrite.java.tree.J$Erroneous
   * and org.openrewrite.java.tree.J$Assignment are in unnamed module of loader
   * 'app'))~~>
   */@Query("SELECT i FROM Info i ORDER BY i.id desc")
  List<Info> findAllByOrderByIdDesc();

  @Query("SELECT i FROM Info i WHERE LOWER(CONCAT(i.name, ' ', i.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
  List<Info> findByFullNameContaining(@Param("name") String name);
}
