package com.driverexam.repository;


import com.driverexam.entry.teacher.TeacherEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeaRepository extends JpaRepository<TeacherEntry, Integer> {

    TeacherEntry findByToken(String token);

    TeacherEntry findByAccountAndPassword(String account, String password);


}
