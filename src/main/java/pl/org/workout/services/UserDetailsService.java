package pl.org.workout.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserDetailsService {
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Collection<? extends GrantedAuthority> getAuthorities(pl.org.workout.enitities.User user);
}
