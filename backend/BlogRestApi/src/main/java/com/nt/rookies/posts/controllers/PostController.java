package com.nt.rookies.posts.controllers;

import com.nt.rookies.posts.dtos.Post;
import com.nt.rookies.posts.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("posts")
public class PostController {
  private PostService service;
  public PostController(PostService service) {
    this.service = service;
  }
  @CrossOrigin(origins = "*")
  @GetMapping
  public ResponseEntity<List<Post>> getAll(@RequestParam(required = false, name = "search") @Valid @Size(min = 3) String search) {
    List<Post> posts = (Objects.isNull(search) || search.isBlank()) ? service.getAll() : service.getByTitle(search);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @GetMapping("/{id}")
  public ResponseEntity<Post> getById(@PathVariable Integer id) {
    return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<Post> save(@RequestBody @Valid Post post) {
    return new ResponseEntity<>(service.save(post), HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @PutMapping("/{id}")
  public ResponseEntity<Post> update(@PathVariable Integer id,@RequestBody @Valid Post post) {
    return new ResponseEntity(service.update(id,post), HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @DeleteMapping("/{id}")
  public ResponseEntity<Post> delete(@PathVariable Integer id) {
    service.delete(service.getId(id));
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}