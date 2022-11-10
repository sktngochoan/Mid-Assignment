package com.nt.rookies.posts.services;

import com.nt.rookies.posts.dtos.Author;
import com.nt.rookies.posts.dtos.Post;
import com.nt.rookies.posts.entities.AuthorEntity;
import com.nt.rookies.posts.entities.PostEntity;
import com.nt.rookies.posts.exceptions.BadRequestException;
import com.nt.rookies.posts.exceptions.NotFoundException;
import com.nt.rookies.posts.mappers.AuthorMapper;
import com.nt.rookies.posts.mappers.PostMapper;
import com.nt.rookies.posts.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {
  private PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  public List<Post> getAll() {
    return PostMapper.toDtoList(this.repository.findAll());
  }

  public Post getId(Integer id) {
    return PostMapper.toDto(repository.findById(id).orElseThrow(() -> new NotFoundException("Post Id : " + id + " Not Found")));
  }

  public Post save(Post post) {
    try {
      return PostMapper.toDto(repository.save(PostMapper.toEntity(post)));
    } catch (NullPointerException e) {
      throw Objects.nonNull(e.getMessage()) ? new BadRequestException(e.getMessage()) : new BadRequestException(e);
    }
  }

  public List<Post> getByTitle(String title) {
    System.out.println(this.getClass()+": "+title);
    return PostMapper.toDtoList(this.repository.findByTitleContains(title));
  }

  public Post update(int id,Post post) {
    post.setId(id);
    PostEntity newPost = PostMapper.toEntity(post);
    return PostMapper.toDto(repository.save(newPost));
  }

  public void delete(Post post) {
    PostEntity entity = PostMapper.toEntity(post);
    repository.delete(entity);
  }
}