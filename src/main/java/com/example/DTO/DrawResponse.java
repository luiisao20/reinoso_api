package com.example.DTO;

import java.time.OffsetDateTime;
import java.util.List;

import com.example.entity.Info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawResponse {
  private Long id;

  private OffsetDateTime createdAt;

  private List<Info> infos;
}
