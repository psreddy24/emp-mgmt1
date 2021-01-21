package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
