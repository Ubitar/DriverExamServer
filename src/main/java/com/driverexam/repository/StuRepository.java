package com.driverexam.repository;


import com.driverexam.entry.student.StudentEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StuRepository extends JpaRepository<StudentEntry, Integer> {

    StudentEntry findByToken(String token);

    StudentEntry findByAccountAndPassword(String account, String password);

    Page findByNameLike(String name, Pageable pageable);

    List<StudentEntry> findAllByNameLike(String name);

}
