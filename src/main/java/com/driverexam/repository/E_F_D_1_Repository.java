package com.driverexam.repository;

import com.driverexam.entry.question.C1_C2_C3_1_Entry;
import com.driverexam.entry.question.E_F_D_1_Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface E_F_D_1_Repository extends JpaRepository<E_F_D_1_Entry, Integer> {
    @Query(value = "select id from E_F_D_1_Entry")
    List<Integer> findAllId();

    @Query(value = "select id from E_F_D_1_Entry entry where entry.libType=1")
    List<Integer> findAllIdWithExam();
    Page<E_F_D_1_Entry> findAllByQuestionLike(String question, Pageable pageable);


}
