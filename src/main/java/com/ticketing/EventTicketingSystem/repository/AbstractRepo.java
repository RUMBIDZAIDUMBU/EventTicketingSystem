package com.ticketing.EventTicketingSystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public interface AbstractRepo <T, ID extends Serializable> extends MongoRepository<T, ID> {
}
