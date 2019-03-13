package com.satxvitalrecords.repositories;

import com.satxvitalrecords.models.MailingAddress;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<MailingAddress, Long> {
}