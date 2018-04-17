package com.libedi.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 * @author Sangjun, Park
 *
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}
