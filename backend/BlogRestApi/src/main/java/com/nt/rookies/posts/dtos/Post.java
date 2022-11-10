package com.nt.rookies.posts.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
public class Post {
  private Integer id;

  @NotEmpty(message = "title is required")
  private String title;

  private String description;

  private String content;
  private Author author;
  private LocalDateTime createdAt;
}
