package com.nt.rookies.posts.controllers;

import com.nt.rookies.posts.dtos.Author;
import com.nt.rookies.posts.dtos.LoginDto;
import com.nt.rookies.posts.dtos.MyUserDetails;
import com.nt.rookies.posts.services.AuthorService;
import com.nt.rookies.posts.utils.JwtUtils;
import com.nt.rookies.posts.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {
  @Autowired
  private AuthenticationManager authenticationManager;
  private AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @CrossOrigin(origins = "*")
  @GetMapping
  public ResponseEntity<List<Author>> getAll() {
    return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/{username}")
  public ResponseEntity<Author> gatByUsername(@PathVariable String username) {
    return new ResponseEntity<>(authorService.getByUsername(username), HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @GetMapping("/login")
  public ResponseObject Login(@RequestParam(required = true, name = "username") @Valid @Size(min = 3) String username,
                              @RequestParam(required = true, name = "password") @Valid @Size(min = 3) String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseObject.builder().message("Thông tin tài khoản không chính xác.").status(HttpStatus.OK).build();
    }

    MyUserDetails myUserDetails = (MyUserDetails) authorService.loadUserByUsername(username);
    String jwt = JwtUtils.generateToken(myUserDetails);

    return ResponseObject.builder().message("Đăng nhập thành công.").status(HttpStatus.OK).data(jwt).build();
//    return new ResponseEntity<>(authorService.Login(username,password), HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<String> create(@RequestBody @Valid Author author) {
    return new ResponseEntity(authorService.create(author), HttpStatus.CREATED);
  }
  @CrossOrigin(origins = "*")
  @PutMapping("/{username}")
  public ResponseEntity<String> update(@PathVariable String username,@RequestBody @Valid Author author) {
    return new ResponseEntity(authorService.update(username, author), HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @DeleteMapping("/{username}")
  public ResponseEntity<String> delete(@PathVariable String username) {
    authorService.delete(authorService.getByUsername(username));
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}