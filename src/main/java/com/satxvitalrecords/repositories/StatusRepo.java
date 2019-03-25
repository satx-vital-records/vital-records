package com.satxvitalrecords.repositories;

import com.satxvitalrecords.models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepo extends CrudRepository<Status, Long> {
}
