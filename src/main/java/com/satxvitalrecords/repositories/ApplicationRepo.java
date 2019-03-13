package com.satxvitalrecords.repositories;

import com.satxvitalrecords.models.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepo extends CrudRepository<Application, Long> {
}
