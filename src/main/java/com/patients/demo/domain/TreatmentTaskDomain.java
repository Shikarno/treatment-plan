package com.patients.demo.domain;

import com.patients.demo.entity.Status;
import com.patients.demo.entity.TreatmentAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class TreatmentTaskDomain {

    private Long id;

    private TreatmentAction treatmentAction;

    private String subjectPatient;

    private LocalDateTime startDateTime;

    private Status status;
}
