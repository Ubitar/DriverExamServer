package com.driverexam.repository;

import com.driverexam.entry.question.A1_A3_B1_1_SelectionEntry;
import com.driverexam.entry.question.B2_A2_1_Entry;
import com.driverexam.entry.question.B2_A2_1_SelectionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface B2_A2_1_Selection_Repository extends JpaRepository<B2_A2_1_SelectionEntry, Integer> {

}
