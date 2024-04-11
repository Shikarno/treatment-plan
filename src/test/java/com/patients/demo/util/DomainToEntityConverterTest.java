package com.patients.demo.util;

import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.Status;
import com.patients.demo.entity.TreatmentAction;
import com.patients.demo.entity.TreatmentTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainToEntityConverterTest {

    @Test
    public void testTreatmentTaskConversion() {
        TreatmentTaskDomain taskDto = TreatmentTaskDomain.builder()
                .id(1L)
                .treatmentAction(TreatmentAction.ACTION_A)
                .startDateTime(LocalDateTime.now())
                .subjectPatient("Patient")
                .status(Status.ACTIVE)
                .build();

        DomainToEntityConverter converter = new DomainToEntityConverter();

        TreatmentTask taskEntity = converter.treatmentTask(taskDto);

        assertEquals(taskDto.getId(), taskEntity.getId());
        assertEquals(taskDto.getTreatmentAction(), taskEntity.getTreatmentAction());
        assertEquals(taskDto.getStartDateTime(), taskEntity.getStartDateTime());
        assertEquals(taskDto.getSubjectPatient(), taskEntity.getSubjectPatient());
        assertEquals(taskDto.getStatus(), taskEntity.getStatus());
    }
}
