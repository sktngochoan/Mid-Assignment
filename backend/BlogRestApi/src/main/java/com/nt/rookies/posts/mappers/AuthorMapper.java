package com.nt.rookies.posts.mappers;

import com.nt.rookies.posts.dtos.Author;
import com.nt.rookies.posts.dtos.Post;
import com.nt.rookies.posts.entities.AuthorEntity;
import com.nt.rookies.posts.entities.PostEntity;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorMapper {
  public static Author toDto(AuthorEntity entity) {
    Author author = new Author();
    author.setUsername(entity.getUsername());
    author.setEmail(entity.getEmail());
    author.setFirstName(entity.getFirstName());
    author.setLastName(entity.getLastName());
    author.setBirthDate(entity.getBirthDate());
    author.setCreatedAt(entity.getCreatedAt());
    return author;
  }

  public static AuthorEntity toEntity(Author author) {
    AuthorEntity entity = new AuthorEntity();
    entity.setUsername(author.getUsername());
    entity.setPassword(author.getPassword());
    entity.setEmail(author.getEmail());
    entity.setFirstName(author.getFirstName());
    entity.setLastName(author.getLastName());
    entity.setBirthDate((author.getBirthDate()));
    entity.setCreatedAt(Objects.isNull(author.getCreatedAt()) ? LocalDateTime.now() : author.getCreatedAt());

    return entity;
  }

  public static List<Author> toDtoList(List<AuthorEntity> entities) {
    return entities.stream().map(AuthorMapper::toDto).collect(Collectors.toList());
  }

  public static List<Author> toDtoList(Iterable<AuthorEntity> entities) {
    List<Author> authors = new LinkedList<>();
    entities.forEach( e -> authors.add(toDto(e)));
    return authors;
  }
}
