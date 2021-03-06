package com.driverexam.repository;

import com.driverexam.entry.question.E_F_D_4_Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface E_F_D_4_Repository extends JpaRepository<E_F_D_4_Entry, Integer> {
    @Query(value = "select id from E_F_D_4_Entry")
    List<Integer> findAllId();

    @Query(value = "select id from E_F_D_4_Entry entry where entry.libType=1")
    List<Integer> findAllIdWithExam();

    Page<E_F_D_4_Entry> findAllByQuestionLike(String question, Pageable pageable);

}
