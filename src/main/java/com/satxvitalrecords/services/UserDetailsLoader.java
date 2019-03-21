package com.satxvitalrecords.services;

import com.satxvitalrecords.models.User;
import com.satxvitalrecords.models.UserWithRoles;
import com.satxvitalrecords.repositories.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
  private final Users users;

  public UserDetailsLoader(Users users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = users.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user found for " + username);
    }

    return new UserWithRoles(user);
  }
}
