package com.driverexam.repository;

import com.driverexam.entry.question.A1_A3_B1_1_AnalysisEntry;
import com.driverexam.entry.question.A1_A3_B1_1_SelectionEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface A1_A3_B1_1_Analysis_Repository extends JpaRepository<A1_A3_B1_1_AnalysisEntry, Integer> {

}
