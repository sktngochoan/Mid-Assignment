package com.nt.rookies.posts.dtos;

import com.nt.rookies.posts.entities.AuthorEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class MyUserDetails extends org.springframework.security.core.userdetails.User {

    private AuthorEntity user;

    public MyUserDetails(String username,
                         String password,
                         boolean enabled,
                         boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities,
                         AuthorEntity user) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }
}
