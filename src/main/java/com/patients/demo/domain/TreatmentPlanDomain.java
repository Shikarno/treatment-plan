package com.patients.demo.domain;

import com.patients.demo.entity.TreatmentAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TreatmentPlanDomain {

    private Long id;

    private TreatmentAction treatmentAction;

    private String subjectPatient;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String recurrencePattern;

}
