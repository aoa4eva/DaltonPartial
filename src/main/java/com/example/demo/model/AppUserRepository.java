package com.example.demo.model;


import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<Daltonuser, Long> {
    Daltonuser findByUsername(String s);
}
