package com.satxvitalrecords.models;

import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
  User findByUsername(String username);

}
