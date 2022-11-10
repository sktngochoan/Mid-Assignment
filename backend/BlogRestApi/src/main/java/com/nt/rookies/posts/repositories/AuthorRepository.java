package com.nt.rookies.posts.repositories;

import com.nt.rookies.posts.entities.AuthorEntity;
import com.nt.rookies.posts.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {
    @Query("select a from AuthorEntity a " +
            "where a.username like :username and " +
            " a.password like :password")
    AuthorEntity login(@Param("username") String username,@Param("password") String password);

    AuthorEntity findByUsername(String username);
}