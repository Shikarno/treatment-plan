package com.patients.demo.util;

import com.patients.demo.domain.TreatmentPlanDomain;
import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.TreatmentAction;
import com.patients.demo.entity.TreatmentPlan;
import com.patients.demo.entity.TreatmentTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityToDomainConverterTest {

    private final EntityToDomainConverter converter = new EntityToDomainConverter();

    @Test
    public void testTreatmentPlanDtoConversion() {
        TreatmentPlan treatmentPlan = TreatmentPlan.builder()
                .id(1L)
                .treatmentAction(TreatmentAction.ACTION_B)
                .subjectPatient("Test Patient")
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusDays(1))
                .recurrencePattern("Test Pattern")
                .build();

        TreatmentPlanDomain planDomain = converter.treatmentPlanDto(treatmentPlan);

        assertEquals(treatmentPlan.getId(), planDomain.getId());
        assertEquals(treatmentPlan.getTreatmentAction(), planDomain.getTreatmentAction());
        assertEquals(treatmentPlan.getSubjectPatient(), planDomain.getSubjectPatient());
        assertEquals(treatmentPlan.getStartDateTime(), planDomain.getStartDateTime());
        assertEquals(treatmentPlan.getEndDateTime(), planDomain.getEndDateTime());
        assertEquals(treatmentPlan.getRecurrencePattern(), planDomain.getRecurrencePattern());
    }

    @Test
    public void testTreatmentTaskDtoConversion() {
        TreatmentTask treatmentTask = TreatmentTask.builder()
                .id(1L)
                .treatmentAction(TreatmentAction.ACTION_B)
                .subjectPatient("Test Patient")
                .startDateTime(LocalDateTime.now())
                .build();

        TreatmentTaskDomain taskDomain = converter.treatmentTaskDto(treatmentTask);

        assertEquals(treatmentTask.getId(), taskDomain.getId());
        assertEquals(treatmentTask.getTreatmentAction(), taskDomain.getTreatmentAction());
        assertEquals(treatmentTask.getSubjectPatient(), taskDomain.getSubjectPatient());
        assertEquals(treatmentTask.getStartDateTime(), taskDomain.getStartDateTime());
    }
}
