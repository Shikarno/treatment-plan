package com.patients.demo.repository;

import com.patients.demo.entity.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
}
