package com.apress.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apress.spring.domain.Testjson;

public interface TestjsonRepository extends JpaRepository<Testjson,Integer> {

}
