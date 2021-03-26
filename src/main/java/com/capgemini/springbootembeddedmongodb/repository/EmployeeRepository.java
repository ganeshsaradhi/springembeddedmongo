package com.capgemini.springbootembeddedmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.springbootembeddedmongodb.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer>{

}
