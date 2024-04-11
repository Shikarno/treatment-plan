package com.patients.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TreatmentPlan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TreatmentAction treatmentAction;

    private String subjectPatient;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String recurrencePattern;

}
