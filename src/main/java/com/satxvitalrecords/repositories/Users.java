package com.satxvitalrecords.repositories;

import com.satxvitalrecords.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
  User findByUsername(String username);

}
