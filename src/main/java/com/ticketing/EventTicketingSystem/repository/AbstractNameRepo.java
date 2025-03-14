package com.ticketing.EventTicketingSystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface AbstractNameRepo <T, ID extends Serializable> extends AbstractRepo<T, ID> {

    public List<T> findByActiveAndNameLike(Boolean active, String name);
    public T findByActiveAndName(Boolean active, String name);
    public List<T> findByActiveOrderByNameAsc(Boolean active);
    public Boolean existsByNameIgnoreCaseAndActive(String name, Boolean active);

}
