package com.satxvitalrecords.repositories;

import com.satxvitalrecords.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
