package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long> {
      Grade findByStudentIsAndTheClass(Daltonuser theStudent,AcademicClass theClass);
      Iterable <Grade> findAllByStudentIsAndTheClass(Daltonuser theStudent, AcademicClass theClass);
}
