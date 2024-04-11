package com.patients.demo.service;

import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.TreatmentTask;
import com.patients.demo.repository.TreatmentTaskRepository;
import com.patients.demo.util.DomainToEntityConverter;
import com.patients.demo.util.EntityToDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class TreatmentTaskService {

    private final EntityToDomainConverter entityToDomainConverter;

    private final DomainToEntityConverter domainToEntityConverter;

    private final TreatmentTaskRepository treatmentTaskRepository;

    public TreatmentTaskService(EntityToDomainConverter entityToDomainConverter,
                                DomainToEntityConverter domainToEntityConverter,
                                TreatmentTaskRepository treatmentTaskRepository) {
        this.entityToDomainConverter = entityToDomainConverter;
        this.domainToEntityConverter = domainToEntityConverter;
        this.treatmentTaskRepository = treatmentTaskRepository;
    }

    public TreatmentTaskDomain create(TreatmentTaskDomain treatmentTask) {
        TreatmentTask entity = domainToEntityConverter.treatmentTask(treatmentTask);
        return entityToDomainConverter.treatmentTaskDto(treatmentTaskRepository.save(entity));
    }

}
