package com.nt.rookies.posts.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {
  @NotNull
  private String username;
  @NotNull
  private String password;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  private String email;
  @NotNull
  private LocalDate birthDate;
  private LocalDateTime createdAt;
}
