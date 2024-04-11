package com.patients.demo.service;

import com.patients.demo.domain.TreatmentPlanDomain;
import com.patients.demo.repository.TreatmentPlanRepository;
import com.patients.demo.util.EntityToDomainConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TreatmentPlanService {

    private final EntityToDomainConverter entityToDomainConverter;

    private final TreatmentPlanRepository treatmentPlanRepository;

    public TreatmentPlanService(EntityToDomainConverter entityToDomainConverter, TreatmentPlanRepository treatmentPlanRepository) {
        this.entityToDomainConverter = entityToDomainConverter;
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    public List<TreatmentPlanDomain> getAllActivePlans() {
        return treatmentPlanRepository.findAll().stream()
                .filter(plan -> plan.getEndDateTime().isAfter(LocalDateTime.now()))
                .map(entityToDomainConverter::treatmentPlanDto)
                .toList();
    }

}
