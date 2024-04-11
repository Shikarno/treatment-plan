package com.patients.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TreatmentTask {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TreatmentAction treatmentAction;

    private String subjectPatient;

    private LocalDateTime startDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;
}
