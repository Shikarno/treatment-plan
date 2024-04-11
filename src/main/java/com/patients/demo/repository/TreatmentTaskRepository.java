package com.patients.demo.repository;

import com.patients.demo.entity.TreatmentTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentTaskRepository extends JpaRepository<TreatmentTask, Long> {
}
