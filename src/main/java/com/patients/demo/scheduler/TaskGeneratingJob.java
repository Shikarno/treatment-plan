package com.patients.demo.scheduler;

import com.patients.demo.domain.TreatmentPlanDomain;
import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.Status;
import com.patients.demo.service.TreatmentPlanService;
import com.patients.demo.service.TreatmentTaskService;
import com.patients.demo.util.DateTimeConverter;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TaskGeneratingJob {

    private final TreatmentPlanService treatmentPlanService;

    private final TreatmentTaskService treatmentTaskService;

    private final DateTimeConverter dateTimeConverter;

    public TaskGeneratingJob(TreatmentPlanService treatmentPlanService,
                             TreatmentTaskService treatmentTaskService,
                             DateTimeConverter dateTimeConverter) {
        this.treatmentPlanService = treatmentPlanService;
        this.treatmentTaskService = treatmentTaskService;
        this.dateTimeConverter = dateTimeConverter;
    }

    public void generateTasks() {
        List<TreatmentPlanDomain> activePlans = treatmentPlanService.getAllActivePlans();
        activePlans.forEach(
                treatmentPlan -> {
                    List<Date> parse = new PrettyTimeParser().parse(treatmentPlan.getRecurrencePattern());
                    parse.stream().map(dateTimeConverter::convertToLocalDateTime).forEach(
                            date -> {
                                TreatmentTaskDomain task = TreatmentTaskDomain.builder()
                                        .treatmentAction(treatmentPlan.getTreatmentAction())
                                        .subjectPatient(treatmentPlan.getSubjectPatient())
                                        .status(Status.ACTIVE)
                                        .startDateTime(date)
                                        .build();
                                treatmentTaskService.create(task);
                            }
                    );
                }
        );
    }
}
