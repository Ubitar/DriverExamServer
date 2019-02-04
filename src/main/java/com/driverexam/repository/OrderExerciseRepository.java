package com.driverexam.repository;

import com.driverexam.entry.student.OrderExerciseEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderExerciseRepository extends JpaRepository<OrderExerciseEntry, Integer> {
    OrderExerciseEntry findByUserId(int userId);
}
