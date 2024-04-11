package com.patients.demo.util;

import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.TreatmentTask;
import org.springframework.stereotype.Component;

@Component
public class DomainToEntityConverter {

    public TreatmentTask treatmentTask(TreatmentTaskDomain treatmentTaskDomain) {
        return TreatmentTask.builder()
                .id(treatmentTaskDomain.getId())
                .treatmentAction(treatmentTaskDomain.getTreatmentAction())
                .startDateTime(treatmentTaskDomain.getStartDateTime())
                .subjectPatient(treatmentTaskDomain.getSubjectPatient())
                .status(treatmentTaskDomain.getStatus())
                .build();
    }
}
