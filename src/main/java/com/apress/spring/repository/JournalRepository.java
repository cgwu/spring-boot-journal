package com.apress.spring.repository;

import com.apress.spring.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal,Long> {

}
