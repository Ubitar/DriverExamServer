package com.driverexam.repository;

import com.driverexam.entry.student.ErrorBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ErrorBookRepository extends JpaRepository<ErrorBookEntry, Integer> , JpaSpecificationExecutor<ErrorBookEntry> {

    List<ErrorBookEntry> findAllByUserId(int userId);

    List<ErrorBookEntry> findAllByTypeAndUserId(String type,int userId);

    List<ErrorBookEntry> findAllByLevelAndUserId(int level,int userId);

    List<ErrorBookEntry> findAllByTypeAndLevelAndUserId(String type,int level,int userId);

}
