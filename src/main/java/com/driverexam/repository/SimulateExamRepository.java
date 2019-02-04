package com.driverexam.repository;

import com.driverexam.entry.student.SimulateExamEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulateExamRepository extends JpaRepository<SimulateExamEntry, Integer> {
    SimulateExamEntry findByUserId(int userId);
}
