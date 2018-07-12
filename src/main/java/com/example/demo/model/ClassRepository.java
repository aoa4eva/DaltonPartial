package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<AcademicClass, Long> {
    Iterable <AcademicClass> findAllByClassNameContainingIgnoreCaseOrSectionCodeContainingIgnoreCase(String search,String otherSearch);
    Iterable <AcademicClass> findAllByDaltonusersNotContaining(Daltonuser currentUser);
    Iterable <AcademicClass> findAllByDaltonusersContaining(Daltonuser currentUser);

}
