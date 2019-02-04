package com.driverexam.repository;

import com.driverexam.entry.student.ExamEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExamRepository extends JpaRepository<ExamEntry, Integer>, JpaSpecificationExecutor<ExamEntry> {
    ExamEntry findByUserId(int userId);

}
