package com.nt.rookies.posts.services;

import com.nt.rookies.posts.dtos.Author;
import com.nt.rookies.posts.dtos.MyUserDetails;
import com.nt.rookies.posts.entities.AuthorEntity;
import com.nt.rookies.posts.exceptions.NotFoundException;
import com.nt.rookies.posts.mappers.AuthorMapper;
import com.nt.rookies.posts.repositories.AuthorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface AuthorServiceImpl {
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;

    public List<Author> getAll();

    public Author getByUsername(String username);

    public Author Login(String username,String password) ;

    public Author create(Author author) ;

    public Author update(String username, Author author) ;

    public void delete(Author author) ;
}
