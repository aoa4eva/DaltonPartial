package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;

public interface DaltonUserRepository extends CrudRepository<Daltonuser, Long> {
    Daltonuser findByUsername(String username);
}
