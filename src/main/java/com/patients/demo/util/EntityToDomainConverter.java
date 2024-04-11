package com.patients.demo.util;

import com.patients.demo.domain.TreatmentPlanDomain;
import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.TreatmentPlan;
import com.patients.demo.entity.TreatmentTask;
import org.springframework.stereotype.Component;

@Component
public class EntityToDomainConverter {

    public TreatmentPlanDomain treatmentPlanDto(TreatmentPlan treatmentPlan) {
        return TreatmentPlanDomain.builder()
                .id(treatmentPlan.getId())
                .treatmentAction(treatmentPlan.getTreatmentAction())
                .subjectPatient(treatmentPlan.getSubjectPatient())
                .startDateTime(treatmentPlan.getStartDateTime())
                .endDateTime(treatmentPlan.getEndDateTime())
                .recurrencePattern(treatmentPlan.getRecurrencePattern())
                .build();
    }

    public TreatmentTaskDomain treatmentTaskDto(TreatmentTask treatmentTask) {
        return TreatmentTaskDomain.builder()
                .id(treatmentTask.getId())
                .treatmentAction(treatmentTask.getTreatmentAction())
                .subjectPatient(treatmentTask.getSubjectPatient())
                .startDateTime(treatmentTask.getStartDateTime())
                .build();
    }
}
