package com.patients.demo.scheduler;

import com.patients.demo.domain.TreatmentPlanDomain;
import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.Status;
import com.patients.demo.entity.TreatmentAction;
import com.patients.demo.service.TreatmentPlanService;
import com.patients.demo.service.TreatmentTaskService;
import com.patients.demo.util.DateTimeConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskGeneratingJobTest {

    private static final String RECURRENCE_PATTERN = "every day at 12:00";
    private static final String SUBJECT_PATIENT = "Patient1";
    @Mock
    private TreatmentPlanService treatmentPlanService;

    @Mock
    private TreatmentTaskService treatmentTaskService;

    @Mock
    private DateTimeConverter dateTimeConverter;

    @InjectMocks
    private TaskGeneratingJob taskGeneratingJob;

    @Test
    public void testGenerateTasks() {
        LocalDateTime now = LocalDateTime.now();

        TreatmentPlanDomain plan1 = TreatmentPlanDomain.builder()
                .endDateTime(now.plusDays(1))
                .subjectPatient(SUBJECT_PATIENT)
                .treatmentAction(TreatmentAction.ACTION_A)
                .recurrencePattern(RECURRENCE_PATTERN)
                .build();

        TreatmentTaskDomain task1 = TreatmentTaskDomain.builder()
                .treatmentAction(TreatmentAction.ACTION_A)
                .subjectPatient(SUBJECT_PATIENT)
                .status(Status.ACTIVE)
                .startDateTime(now)
                .build();

        List<TreatmentPlanDomain> plans = Collections.singletonList(plan1);

        when(treatmentPlanService.getAllActivePlans()).thenReturn(plans);
        when(dateTimeConverter.convertToLocalDateTime(any(Date.class))).thenReturn(now);

        taskGeneratingJob.generateTasks();

        verify(dateTimeConverter, times(1)).convertToLocalDateTime(any(Date.class));
        verify(treatmentPlanService, times(1)).getAllActivePlans();
        verify(treatmentTaskService, times(1)).create(task1);
    }

    @Test
    public void testGenerateTasks_NoPlans() {
        List<TreatmentPlanDomain> emptyPlans = new ArrayList<>();
        when(treatmentPlanService.getAllActivePlans()).thenReturn(emptyPlans);

        taskGeneratingJob.generateTasks();

        verify(treatmentTaskService, never()).create(any(TreatmentTaskDomain.class));
    }
}
