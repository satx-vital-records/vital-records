package com.satxvitalrecords.repositories;

import com.satxvitalrecords.models.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepo extends CrudRepository<Record, Long> {

}
