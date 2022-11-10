package com.nt.rookies.posts.services;

import com.nt.rookies.posts.dtos.Author;
import com.nt.rookies.posts.dtos.MyUserDetails;
import com.nt.rookies.posts.entities.AuthorEntity;
import com.nt.rookies.posts.exceptions.NotFoundException;
import com.nt.rookies.posts.mappers.AuthorMapper;
import com.nt.rookies.posts.mappers.PostMapper;
import com.nt.rookies.posts.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements AuthorServiceImpl, UserDetailsService {


    private AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthorEntity user = authorRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Không tìm thấy tài khoản : " + username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new MyUserDetails(user.getUsername(), user.getPassword(), true, true,
                true, true , authorities, user);
    }
    @Override
    public List<Author> getAll() {
        return AuthorMapper
                .toDtoList(authorRepository.findAll());
    }
    @Override
    public Author getByUsername(String username) {
        return AuthorMapper
                .toDto(authorRepository
                        .findById(username)
                        .orElseThrow(() ->
                                new NotFoundException(String.format("Author username=%s not found", username))));
    }
    @Override
    public Author Login(String username,String password) {
        return AuthorMapper.toDto(authorRepository.login(username,password));
    }
    @Override
    public Author create(Author author) {
        AuthorEntity entity = AuthorMapper.toEntity(author);
        return AuthorMapper
                .toDto(authorRepository.save(entity));
    }
    @Override
    public Author update(String username, Author author) {
        if (!username.equals(author.getUsername())) {
            throw new RuntimeException("Cannot change username of author");
        }
        AuthorEntity newAuthor = AuthorMapper.toEntity(author);
        return AuthorMapper.toDto(authorRepository.save(newAuthor));
    }
    @Override
    public void delete(Author author) {
        AuthorEntity entity = AuthorMapper.toEntity(author);
        authorRepository.delete(entity);
    }
}