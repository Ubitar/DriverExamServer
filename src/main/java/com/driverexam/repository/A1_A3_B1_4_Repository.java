package com.driverexam.repository;

import com.driverexam.entry.question.A1_A3_B1_4_Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface A1_A3_B1_4_Repository extends JpaRepository<A1_A3_B1_4_Entry, Integer> {
    @Query(value = "select id from A1_A3_B1_4_Entry")
    List<Integer> findAllId();

    @Query(value = "select id from A1_A3_B1_4_Entry entry where entry.libType=1")
    List<Integer> findAllIdWithExam();

    Page<A1_A3_B1_4_Entry> findAllByQuestionLike(String question, Pageable pageable);

    void deleteByIdIn(List<Integer> ids);

}
